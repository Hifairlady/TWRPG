package com.edgar.twrpg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EquipmentsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<EquipmentItem> equipmentItems = new ArrayList<>();

    public EquipmentsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.equipment_item_layout, parent, false);
        return new NormalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NormalViewHolder viewHolder = (NormalViewHolder) holder;
        EquipmentItem item = equipmentItems.get(position);
        viewHolder.tvItemQuality.setText(item.getItemQuality());
        viewHolder.tvItemLevel.setText(item.getItemLevel());
        viewHolder.tvNameChs.setText(item.getItemNameChs());
        viewHolder.tvNameEng.setText(item.getItemNameEng());
        Glide.with(mContext)
                .load(item.getIconFilePath())
                .placeholder(R.drawable.loading)
                .error(R.drawable.default_icon)
                .into(viewHolder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return (equipmentItems == null ? 0 : equipmentItems.size());
    }

    public void addAllItems(ArrayList<EquipmentItem> items) {
        equipmentItems = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    private class NormalViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;
        private TextView tvNameEng;
        private TextView tvNameChs;
        private TextView tvItemLevel;
        private TextView tvItemQuality;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);

            ivIcon = itemView.findViewById(R.id.iv_item_icon);
            tvNameEng = itemView.findViewById(R.id.tv_item_name_eng);
            tvNameChs = itemView.findViewById(R.id.tv_item_name_chs);
            tvItemLevel = itemView.findViewById(R.id.tv_item_level);
            tvItemQuality = itemView.findViewById(R.id.tv_item_quality);

        }
    }

}
