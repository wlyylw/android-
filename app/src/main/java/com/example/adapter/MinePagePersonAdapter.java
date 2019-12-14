package com.example.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity.ChangePersonActivity;
import com.example.entity.MinePagePerson;
import com.example.news.R;
import com.service.MinePagePersonService;


import org.litepal.LitePal;

import java.util.List;

public class MinePagePersonAdapter extends  RecyclerView.Adapter<MinePagePersonAdapter.ViewHolder>{
    private List<MinePagePerson> list;
    static  class ViewHolder extends RecyclerView.ViewHolder{

        View ClickView;
        TextView name;
        ImageView imageView;
        public ViewHolder(View view)
        {
            super(view);
            name   = view.findViewById(R.id.mine_username);
            imageView = view.findViewById(R.id.mine_picture);
            ClickView =view;
        }

    }
    public MinePagePersonAdapter(List<MinePagePerson> list){
        this.list=list;
    }


    @NonNull
    @Override
    public MinePagePersonAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item_person,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ClickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(),ChangePersonActivity.class);
                parent.getContext().startActivity(intent);
            }
        });
        MinePagePersonService minePagePersonService =MinePagePersonService.getInstance();
            if(minePagePersonService.getHeadshot() ==null)
            {
                holder.imageView.setImageResource(R.drawable.touxiang);
            }
            else {
                byte[] images = minePagePersonService.getHeadshot();
                Bitmap bitmap = BitmapFactory.decodeByteArray(images, 0, images.length);
                holder.imageView.setImageBitmap(bitmap);
            }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MinePagePersonAdapter.ViewHolder holder, int position) {
        MinePagePerson MyPagePerson = list.get(position);
        holder.name.setText(MyPagePerson.getName());
        List<MinePagePerson> listLitePal;
        listLitePal =  LitePal.findAll(MinePagePerson.class);
        MinePagePersonService minePagePersonService =MinePagePersonService.getInstance();
        MinePagePerson minePagePerson= minePagePersonService.getMinePagePerson();
        for(MinePagePerson minePagePersonTemp: listLitePal) {
            if (minePagePersonTemp.getPhonenumber().equals(minePagePerson.getPhonenumber())) {
                if (minePagePersonTemp.getPassword().equals(minePagePerson.getPassword())) {
                    if(minePagePersonService.getHeadshot() ==null)
                    {
                        holder.imageView.setImageResource(R.drawable.touxiang);
                        return;
                    }
                    byte[]images=minePagePersonService.getHeadshot();
                    Bitmap bitmap= BitmapFactory.decodeByteArray(images,0,images.length);
                    holder.imageView.setImageBitmap(bitmap);
                }
            }
    }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }






}
