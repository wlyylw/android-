package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entity.MineDynamic;
import com.example.news.R;


import java.util.List;

public class MinePageDynamicAdapter extends  RecyclerView.Adapter<MinePageDynamicAdapter.ViewHolder>{

    private List<MineDynamic> list;

    static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView number;
        TextView name;
        public ViewHolder(View view)
        {
            super(view);
            name   = view.findViewById(R.id.mine_item_dynamic_name);
            number = view.findViewById(R.id.mine_item_dynamic_number);
        }

    }
    public MinePageDynamicAdapter(List<MineDynamic> list){
        this.list=list;
    }


    @NonNull
    @Override
    public MinePageDynamicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item_dynamic,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MinePageDynamicAdapter.ViewHolder holder, int position) {
        MineDynamic minedynamic = list.get(position);
        holder.number.setText(String.valueOf(minedynamic.getNumber()));
        holder.name.setText(minedynamic.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
