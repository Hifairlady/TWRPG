package com.edgar.twrpg;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EquipmentItem.class}, version = 1, exportSchema = false)
public abstract class TWRPGDatabase extends RoomDatabase {

    private static volatile TWRPGDatabase mInstance;

    static TWRPGDatabase getDatabase(final Context context) {
        if (mInstance == null) {
            synchronized (TWRPGDatabase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context.getApplicationContext(),
                            TWRPGDatabase.class, "twrpg_database")
                            .build();
                }
            }
        }
        return mInstance;
    }

    public abstract EquipmentDao equipmentDao();

}
