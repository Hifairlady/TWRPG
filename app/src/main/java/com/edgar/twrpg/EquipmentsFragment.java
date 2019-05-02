package com.edgar.twrpg;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class EquipmentsFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private EquipmentsAdapter adapter;
    private ArrayList<EquipmentItem> equipmentItems = new ArrayList<>();

    private EquipmentViewModel mViewModel;

    public EquipmentsFragment() {
        // Required empty public constructor
    }


    public static EquipmentsFragment newInstance() {
        EquipmentsFragment fragment = new EquipmentsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_equipments, container, false);
        mRecyclerview = rootView.findViewById(R.id.equipments_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);
        adapter = new EquipmentsAdapter(getActivity());
        mRecyclerview.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(EquipmentViewModel.class);
        mViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<EquipmentItem>>() {
            @Override
            public void onChanged(List<EquipmentItem> equipmentItems) {
                adapter.addAllItems(equipmentItems);
                try {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FileProcUtil.SP_DATA_VERSION_STORAGE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(FileProcUtil.SP_DATA_VERSION_STRING, FileProcUtil.versionStringFromAsset);
                    editor.apply();
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
            }
        });
        if (mViewModel.getAllItems().getValue() == null || mViewModel.getAllItems().getValue().size() == 0 || FileProcUtil.shouldUpdate(getActivity())) {
            Snackbar.make(mRecyclerview, "Updating " + FileProcUtil.versionStringFromAsset, Snackbar.LENGTH_SHORT).show();
            new EquipmentAsyncTask(getActivity()).execute();
        }
    }

    private class EquipmentAsyncTask extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private EquipmentItem[] equipmentItems;

        public EquipmentAsyncTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            equipmentItems = FileProcUtil.getEquipmentItems(mContext);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mViewModel.insertEquipmentItems(equipmentItems);

        }
    }

}
