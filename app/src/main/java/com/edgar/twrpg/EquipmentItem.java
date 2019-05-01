package com.edgar.twrpg;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipment_table")
public class EquipmentItem {

    private static final String TAG = "=========" + EquipmentItem.class.getName();
    @PrimaryKey()
    private int itemDatabaseId;

    private String itemId;
    private String iconFilePath;
    private String itemNameEng;
    private String itemNameChs;
    private String itemLevel;
    private String itemQuality;

    @Ignore
    private boolean isItemAcceptable = true;

    public EquipmentItem(String itemId, String iconFilePath, String itemNameEng, String itemNameChs, String itemLevel, String itemQuality) {
        this.itemId = itemId;
        this.iconFilePath = iconFilePath;
        this.itemNameEng = itemNameEng;
        this.itemNameChs = itemNameChs;
        this.itemLevel = itemLevel;
        this.itemQuality = itemQuality;
        this.isItemAcceptable = true;
        this.itemDatabaseId = itemId.hashCode();
        Log.d(TAG, "EquipmentItem: " + itemDatabaseId);
    }

    public int getItemDatabaseId() {
        return itemId.hashCode();
    }

    public void setItemDatabaseId(int itemDatabaseId) {
        this.itemDatabaseId = itemId.hashCode();
    }

    public boolean isItemAcceptable() {
        return isItemAcceptable;
    }

    public void setItemAcceptable(boolean itemAcceptable) {
        isItemAcceptable = itemAcceptable;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getIconFilePath() {
        return iconFilePath;
    }

    public void setIconFilePath(String iconFilePath) {
        this.iconFilePath = iconFilePath;
    }

    public String getItemNameEng() {
        return itemNameEng;
    }

    public void setItemNameEng(String itemNameEng) {
        this.itemNameEng = itemNameEng;
    }

    public String getItemNameChs() {
        return itemNameChs;
    }

    public void setItemNameChs(String itemNameChs) {
        this.itemNameChs = itemNameChs;
    }

    public String getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(String itemLevel) {
        this.itemLevel = itemLevel;
    }

    public String getItemQuality() {
        return itemQuality;
    }

    public void setItemQuality(String itemQuality) {
        this.itemQuality = itemQuality;
    }
}
