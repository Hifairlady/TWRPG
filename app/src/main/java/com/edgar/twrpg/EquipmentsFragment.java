package com.edgar.twrpg;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class EquipmentsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAB_TITLE = "TAB_TITLE";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mTabTitle;
    private String mParam2;

    private RecyclerView mRecyclerview;
    private EquipmentsAdapter adapter;
    private ArrayList<EquipmentItem> equipmentItems = new ArrayList<>();


    public EquipmentsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EquipmentsFragment newInstance(String param1, String param2) {
        EquipmentsFragment fragment = new EquipmentsFragment();
        Bundle args = new Bundle();
        args.putString(TAB_TITLE, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTabTitle = getArguments().getString(TAB_TITLE);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_equipments, container, false);
        mRecyclerview = rootView.findViewById(R.id.equipments_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);
        adapter = new EquipmentsAdapter(getActivity());
        mRecyclerview.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new EquipmentAsyncTask(getActivity()).execute();

    }

    private class EquipmentAsyncTask extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private ArrayList<EquipmentItem> equipmentItems;

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
            adapter.addAllItems(equipmentItems);
        }
    }

}
