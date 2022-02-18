package com.example.adminpanel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminpanel.databinding.RvImageBinding;
import com.example.adminpanel.databinding.RvImageBinding;

import java.util.ArrayList;

public class RVImageAdapter extends RecyclerView.Adapter<RVImageAdapter.VH> {
    Activity activity;
    ArrayList<String> stringArrayList = new ArrayList<>();
    boolean isCancelVisible;

    public RVImageAdapter(Activity activity, ArrayList<String> stringArrayList, boolean isCancelVisible) {
        this.activity = activity;
        this.stringArrayList = stringArrayList;
        this.isCancelVisible = isCancelVisible;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvImageBinding binding = RvImageBinding.inflate(
                LayoutInflater.from(parent.getContext()));

        return new VH(binding);
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String s = stringArrayList.get(position);
        holder.itemView.setTag(position);

        Glide.with(activity)
                .load(s)
                .placeholder(Color.WHITE)
                .centerCrop()
                .into(holder.binding.ivID)
                .clearOnDetach();


    }

    @Override
    public int getItemCount() {
        return stringArrayList == null ? 0 : stringArrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        RvImageBinding binding;
        public VH(RvImageBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
        }
    }
}
