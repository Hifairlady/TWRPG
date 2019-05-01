package com.edgar.twrpg;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileProcUtil {

    public static final String SP_DATA_VERSION_STORAGE = "SP_DATA_VERSION_STORAGE";
    public static final String SP_DATA_VERSION_STRING = "SP_DATA_VERSION_STRING";

    private static final String ASSET_ROOT_PATH = "file:///android_asset/";
    private static final String ASSET_DATA_PATH = ASSET_ROOT_PATH + "data/";
    private static final String ASSET_ICONS_PATH = ASSET_ROOT_PATH + "icons/";
    private static final String TAG = "=============" + FileProcUtil.class.getName();

    public static boolean shouldUpdate(Context context) {

//        AssetManager assetManager = context.getAssets();
        String versionFileName = "data/version.txt";
        String versionStringFromAsset = "version=0.22u";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(versionFileName), StandardCharsets.UTF_8));
            String lineString = reader.readLine();
            if (lineString != null) {
                versionStringFromAsset = lineString;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe2) {
                    ioe2.printStackTrace();
                }
            }
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_DATA_VERSION_STORAGE, Context.MODE_PRIVATE);
        String versionStringFromStorage = sharedPreferences.getString(SP_DATA_VERSION_STRING, "version=0.22u");
        return (!versionStringFromAsset.equals(versionStringFromStorage));
    }

    private static String removeColorCodes(String oldString) {
        //Description=|c0040e0d0[药水]|r |n|CFFFFDEAD一种神奇的混合草药和治疗药水制作的|r |n|c00adff2f∴|r|c0080ff0020秒持续时间 生命值恢复 +800|r
        oldString = oldString.replaceAll("\\|[cC][a-zA-Z0-9]{8}", "");
        String newString = oldString.replaceAll("\\|[cC][a-zA-Z0-9]{6}", "");
        return newString;
    }

    private static String removeNewLinesAndQuotes(String oldString) {
        oldString = oldString.replaceAll("\\|r|\\|n", "");
        oldString = oldString.replaceAll("\\\"", "");
        return oldString;
    }

    private static String getIconFilePath(String artString) {
        String[] splitStrings = artString.split("\\\\");
        String iconFilePath = ASSET_ICONS_PATH.concat("default.bmp");
        if (splitStrings.length >= 2) {
            iconFilePath = splitStrings[splitStrings.length - 1].toLowerCase();
            iconFilePath = iconFilePath.replace(".blp", ".bmp");
            iconFilePath = ASSET_ICONS_PATH.concat(iconFilePath);
        }
        return iconFilePath;
    }

    private static String getItemName(String nameString) {
        nameString = removeColorCodes(nameString);
        nameString = removeNewLinesAndQuotes(nameString);
        nameString = nameString.replace("Name=", "");
        return nameString;
    }

    private static String getLevelString(String descriptionString) {
        descriptionString = removeColorCodes(descriptionString);
        descriptionString = removeNewLinesAndQuotes(descriptionString);
        String levelString = "Lv.0";
        if (!descriptionString.contains("等级.") && !descriptionString.contains("Lv."))
            return levelString;
        String[] splitStrings = descriptionString.split("\\.");
        if (splitStrings.length >= 2) {
            levelString = "Lv.".concat(splitStrings[splitStrings.length - 1]);
        }
        return levelString;
    }

    private static String getItemQuality(String descriptionString) {
        descriptionString = removeColorCodes(descriptionString);
        descriptionString = removeNewLinesAndQuotes(descriptionString);
        String qualityString = "Default";

//        if (descriptionString.contains("Description=[Epic]")) {
//            String[] splitStrings = descriptionString.split("-");
//            if (splitStrings.length >= 3) {
//                qualityString = splitStrings[1];
//                qualityString = qualityString.replace(" ", "");
//            }
//        }

        if (descriptionString.contains("Description=[史诗]")) {
            String[] splitStrings = descriptionString.split("- | -");
            if (splitStrings.length >= 3) {
                qualityString = splitStrings[1];
                qualityString = qualityString.replace(" 品质", "");
            }
        } else {
            String[] splitStrings = descriptionString.split("[\\[\\]]");
            if (splitStrings.length >= 3) {
                qualityString = splitStrings[1];
            }
        }
        return qualityString;
    }

    private static String getIdString(String lineString) {
        String[] splitStrings = lineString.split("[\\[\\]]");
        String result = "I000";
        if (splitStrings.length >= 2) {
            result = splitStrings[0];
        }
        return result;
    }

    public static ArrayList<EquipmentItem> getEquipmentItems(Context context) {
        ArrayList<EquipmentItem> equipmentItems = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("data/item_names_chs.txt"), StandardCharsets.UTF_8));
            String lineString;

            boolean isValidItem = true;

            String itemId = "Default";
            String iconFilePath = "Default";
            String itemNameEng = "Default";
            String itemNameChs = "Default";
            String itemLevel = "Default";
            String itemQuality = "Default";
            while ((lineString = reader.readLine()) != null) {

                Log.d(TAG, "getEquipmentItems: " + lineString);

                if (lineString.startsWith("[")) {
                    isValidItem = true;
                    itemId = getIdString(lineString);
                    continue;
                }

                if (!isValidItem) continue;

                if (lineString.startsWith("Art=")) {
                    iconFilePath = getIconFilePath(lineString);
                    continue;
                }

                if (lineString.startsWith("Description=")) {
                    itemLevel = getLevelString(lineString);
                    itemQuality = getItemQuality(lineString);
                    continue;
                }

                if (lineString.startsWith("Name=")) {
                    itemNameChs = getItemName(lineString);
                    continue;
                }

                if (lineString.startsWith("Tip=")) {
                    isValidItem = false;
                    continue;
                }

                if (lineString.startsWith("Ubertip=") && isValidItem) {
                    if (itemNameChs.contains("徽章")) {
                        itemQuality = "收藏品";
                    }
                    if (itemQuality.equals("Default")) continue;
                    EquipmentItem item = new EquipmentItem(itemId, iconFilePath, itemNameEng, itemNameChs, itemLevel, itemQuality);
//                    Log.d(TAG, "getEquipmentItems: Item added: " + itemNameChs);
                    equipmentItems.add(item);
                    continue;
                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe2) {
                    ioe2.printStackTrace();
                }
            }
        }

        return equipmentItems;
    }

}
