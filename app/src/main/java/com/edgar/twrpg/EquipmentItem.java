package com.edgar.twrpg;

public class EquipmentItem {

    private String itemId;
    private String iconFilePath;
    private String itemNameEng;
    private String itemNameChs;
    private String itemLevel;
    private String itemQuality;

    public EquipmentItem(String itemId, String iconFilePath, String itemNameEng, String itemNameChs, String itemLevel, String itemQuality) {
        this.itemId = itemId;
        this.iconFilePath = iconFilePath;
        this.itemNameEng = itemNameEng;
        this.itemNameChs = itemNameChs;
        this.itemLevel = itemLevel;
        this.itemQuality = itemQuality;
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
