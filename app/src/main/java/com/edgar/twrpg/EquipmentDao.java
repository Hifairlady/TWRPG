package com.edgar.twrpg;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EquipmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(EquipmentItem... equipmentItems);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EquipmentItem item);

    @Query("DELETE FROM equipment_table")
    void deleteAll();

    @Query("SELECT * FROM equipment_table ORDER BY itemId ASC")
    LiveData<List<EquipmentItem>> getAllEquipments();

}
