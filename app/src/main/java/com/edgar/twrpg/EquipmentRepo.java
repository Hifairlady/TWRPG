package com.edgar.twrpg;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EquipmentRepo {

    private EquipmentDao equipmentDao;
    private LiveData<List<EquipmentItem>> allItems;

    EquipmentRepo(Application application) {
        TWRPGDatabase database = TWRPGDatabase.getDatabase(application);
        equipmentDao = database.equipmentDao();
        allItems = equipmentDao.getAllEquipments();
    }

    public LiveData<List<EquipmentItem>> getAllItems() {
        return allItems;
    }

    public void insertEquipmentItems(EquipmentItem[] equipmentItems) {
        new InsertAsyncTask(equipmentDao).execute(equipmentItems);
    }

    private class InsertAsyncTask extends AsyncTask<EquipmentItem, Void, Void> {

        private EquipmentDao mAsyncTaskDao;

        public InsertAsyncTask(EquipmentDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(EquipmentItem... equipmentItems) {
//            mAsyncTaskDao.insert(equipmentItems[0]);
            mAsyncTaskDao.insertAll(equipmentItems);
            return null;
        }
    }
}
