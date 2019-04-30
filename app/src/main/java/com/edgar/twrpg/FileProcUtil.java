package com.edgar.twrpg;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class FileProcUtil {

    private static final String ASSET_ROOT_PATH = "file:///android_asset/";
    private static final String ASSET_DATA_PATH = ASSET_ROOT_PATH + "data/";
    private static final String ASSET_ICONS_PATH = ASSET_ROOT_PATH + "icons/";
    private static final String TAG = "=============" + FileProcUtil.class.getName();

    public static boolean checkUpdate(Context context) {

//        AssetManager assetManager = context.getAssets();
        String versionFilePath = ASSET_DATA_PATH + "version.txt";
        File file = new File(versionFilePath);
        if (file.exists() && file.isFile()) {

        } else {
            Log.d(TAG, "checkUpdate: file not exists");
        }

//
//        SharedPreferences sharedPreferences = context.getSharedPreferences("DATA_VERSION_CODE", Context.MODE_PRIVATE);
//        String versionStringFromStorage = sharedPreferences.getString("DATA_VERSION_STRING", "version=0.22u");
//        String versionStringFromAsset;
        return false;

    }

    public static String removeColorCodes(String oldString) {
        //Description=|c0040e0d0[药水]|r |n|CFFFFDEAD一种神奇的混合草药和治疗药水制作的|r |n|c00adff2f∴|r|c0080ff0020秒持续时间 生命值恢复 +800|r
        oldString = oldString.replaceAll("\\|[cC][a-zA-Z0-9]{8}", "");
        String newString = oldString.replaceAll("\\|[cC][a-zA-Z0-9]{6}", "");
        return newString;
    }

    public static String removeNewLinesAndQuotes(String oldString) {
        oldString = oldString.replaceAll("\\|r|\\|n", "");
        oldString = oldString.replaceAll("\\\"", "");
        return oldString;
    }

    public static String getIconFilePath(String artString) {
        String[] splitStrings = artString.split("\\\\");
        String iconFilePath = ASSET_ICONS_PATH + "default.bmp";
        if (splitStrings.length > 1) {
            iconFilePath = splitStrings[splitStrings.length - 1].toLowerCase();
            iconFilePath = iconFilePath.replace(".blp", ".bmp");
        }
        return iconFilePath;
    }

    public static String getItemName(String nameString) {
        nameString = removeColorCodes(nameString);
        nameString = removeNewLinesAndQuotes(nameString);
        nameString = nameString.replace("Name=", "");
        return nameString;
    }

    public static String getLevelString(String engDescriptionString) {
        String[] splitStrings = engDescriptionString.split("Lv\\.");
        String levelString = "Lv.0";
        if (splitStrings.length > 1) {
            levelString = "Lv.".concat(splitStrings[splitStrings.length - 1]);
        }
        return levelString;
    }

    public static String getItemQuality(String descriptionString) {
        descriptionString = removeColorCodes(descriptionString);
        descriptionString = removeNewLinesAndQuotes(descriptionString);
        String qualityString = "Default";

//        if (descriptionString.contains("Description=[Epic]")) {
//            String[] splitStrings = descriptionString.split("-");
//            if (splitStrings.length > 2) {
//                qualityString = splitStrings[1];
//                qualityString = qualityString.replace(" ", "");
//            }
//        }

        if (descriptionString.contains("Description=[史诗]")) {
            String[] splitStrings = descriptionString.split("- | -");
            if (splitStrings.length > 2) {
                qualityString = splitStrings[1];
                qualityString = qualityString.replace(" 品质", "");
            }
        } else {
            String[] splitStrings = descriptionString.split("[\\[\\]]");
            if (splitStrings.length > 2) {
                qualityString = splitStrings[1];
            }
        }
        return qualityString;
    }

}
