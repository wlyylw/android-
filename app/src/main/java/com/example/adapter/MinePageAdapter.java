package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entity.MinePageA;
import com.example.news.R;


import java.util.List;

public class MinePageAdapter extends  RecyclerView.Adapter<MinePageAdapter.ViewHolder>{

    private List<MinePageA> list;

    static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView information;
        TextView name;
        public ViewHolder(View view)
        {
            super(view);
            name   = view.findViewById(R.id.mine_name_a);
            information = view.findViewById(R.id.mine_information_a);
        }

    }
    @NonNull
    @Override
    public MinePageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item_information,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MinePageAdapter.ViewHolder holder, int position) {
        MinePageA MinePageA = list.get(position);
        holder.information.setText(MinePageA.getInformation());
        holder.name.setText(MinePageA.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
