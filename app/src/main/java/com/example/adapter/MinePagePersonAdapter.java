package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entity.MinePagePerson;
import com.example.news.R;


import java.util.List;

public class MinePagePersonAdapter extends  RecyclerView.Adapter<MinePagePersonAdapter.ViewHolder>{

    private List<MinePagePerson> list;

    static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        public ViewHolder(View view)
        {
            super(view);
            name   = view.findViewById(R.id.mine_username);
        }

    }
    public MinePagePersonAdapter(List<MinePagePerson> list){
        this.list=list;
    }


    @NonNull
    @Override
    public MinePagePersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item_person,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MinePagePersonAdapter.ViewHolder holder, int position) {
        MinePagePerson MyPagePerson = list.get(position);
        holder.name.setText(MyPagePerson.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
