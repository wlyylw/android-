package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entity.MinePageInformation;
import com.example.news.R;


import java.util.List;

public class MinePageInformationAdapter extends  RecyclerView.Adapter<MinePageInformationAdapter.ViewHolder>{

    private List<MinePageInformation> list;

    static  class ViewHolder extends RecyclerView.ViewHolder{
        View ClickView;
        TextView information;
        TextView name;
        public ViewHolder(View view)
        {
            super(view);
            name   = view.findViewById(R.id.mine_name_a);
            information = view.findViewById(R.id.mine_information_a);
            ClickView =view;
        }

    }
    public MinePageInformationAdapter(List<MinePageInformation> list){
        this.list=list;
    }


    @NonNull
    @Override
    public MinePageInformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item_information,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ClickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MinePageInformation minePageInformation = list.get(position);
                Toast.makeText(v.getContext(), minePageInformation.getName()+"的细节没写",Toast.LENGTH_SHORT).show();;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MinePageInformationAdapter.ViewHolder holder, int position) {
        MinePageInformation MinePageA = list.get(position);
        holder.information.setText(MinePageA.getInformation());
        holder.name.setText(MinePageA.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
