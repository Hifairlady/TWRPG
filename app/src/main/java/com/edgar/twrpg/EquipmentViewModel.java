package com.edgar.twrpg;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EquipmentViewModel extends AndroidViewModel {

    private EquipmentRepo equipmentRepo;
    private LiveData<List<EquipmentItem>> allItems;

    public EquipmentViewModel(@NonNull Application application) {
        super(application);
        equipmentRepo = new EquipmentRepo(application);
        allItems = equipmentRepo.getAllItems();
    }

    public LiveData<List<EquipmentItem>> getAllItems() {
        return allItems;
    }

    public void insertEquipmentItems(EquipmentItem[] equipmentItems) {
        equipmentRepo.insertEquipmentItems(equipmentItems);
    }

}
