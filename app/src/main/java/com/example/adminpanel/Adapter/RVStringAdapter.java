package com.example.adminpanel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpanel.databinding.RvStudentLayoutBinding;

import java.util.ArrayList;

public class RVStringAdapter extends RecyclerView.Adapter<RVStringAdapter.VH> {
    Activity activity;
    ArrayList<String> stringArrayList = new ArrayList<>();
    boolean isCancelVisible;

    public RVStringAdapter(Activity activity, ArrayList<String> stringArrayList, boolean isCancelVisible) {
        this.activity = activity;
        this.stringArrayList = stringArrayList;
        this.isCancelVisible = isCancelVisible;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvStudentLayoutBinding binding = RvStudentLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()));

        return new VH(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String s = stringArrayList.get(position);
        holder.itemView.setTag(position);

        holder.binding.memberID.setText((position+1) +") " +  s + "");

        holder.binding.closeID.setVisibility(isCancelVisible ? View.VISIBLE : View.GONE);
        holder.binding.closeID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringArrayList.remove(position);
                notifyItemRemoved(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return stringArrayList == null ? 0 : stringArrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        RvStudentLayoutBinding binding;
        public VH(RvStudentLayoutBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
        }
    }
}
