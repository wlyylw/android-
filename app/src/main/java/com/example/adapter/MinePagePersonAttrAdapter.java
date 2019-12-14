package com.example.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entity.MinePagePerson;
import com.example.entity.MinePersonAttr;
import com.example.news.R;
import com.service.MinePagePersonService;


import java.util.Calendar;
import java.util.List;

public class MinePagePersonAttrAdapter extends  RecyclerView.Adapter<MinePagePersonAttrAdapter.ViewHolder>{

    private List<MinePersonAttr> list;
    Context context;
    private int mCurrentItem;
    private AlertDialog alertDialogRadio;
    static  class ViewHolder extends RecyclerView.ViewHolder{
        View ClickView;
        TextView detail;
        TextView name;
        public ViewHolder(View view)
        {
            super(view);
            name   = view.findViewById(R.id.mine_attr_name);
            detail = view.findViewById(R.id.mine_attr_detail);
            ClickView =view;
        }

    }
    public MinePagePersonAttrAdapter(Context context,List<MinePersonAttr> list){
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public MinePagePersonAttrAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item_person_attr,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ClickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                switch (position){

                    case 0: {
                            final EditText et = new EditText(context);
                            new AlertDialog.Builder(context).setTitle("请输入新昵称")
                                    .setView(et)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(context, "成功",Toast.LENGTH_LONG).show();
                                            MinePagePersonService minePagePersonService =MinePagePersonService.getInstance();
                                            MinePagePerson  minePagePerson = minePagePersonService.getMinePagePerson();
                                            minePagePerson.setName(et.getText().toString());
                                            minePagePersonService.setMinePagePerson(minePagePerson);
                                            minePagePerson.save();
                                            holder.detail.setText(et.getText().toString());
                                        }
                                    }).setNegativeButton("取消",null).show();
                    }
                        break;
                    case 1: {
                        final String[] items = {"男","女"};
                        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setTitle("选择性别");
                        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int index) {
                                mCurrentItem =index;
                            }
                        });
                        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
                                holder.detail.setText(items[mCurrentItem]);
                                MinePagePersonService minePagePersonService =MinePagePersonService.getInstance();
                                MinePagePerson  minePagePerson = minePagePersonService.getMinePagePerson();
                                minePagePerson.setSex(items[mCurrentItem]);
                                minePagePersonService.setMinePagePerson(minePagePerson);
                                minePagePerson.save();
                                alertDialogRadio.dismiss();
                            }
                        });
                        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                alertDialogRadio.dismiss();
                            }
                        });
                        alertDialogRadio = alertBuilder.create();
                        alertDialogRadio.show();
                    }
                        break;
                    case 2: {
                        Calendar calendar = Calendar.getInstance();
                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                MinePagePersonService minePagePersonService =MinePagePersonService.getInstance();
                                MinePagePerson  minePagePerson = minePagePersonService.getMinePagePerson();
                                minePagePerson.setBirthday(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                                minePagePersonService.setMinePagePerson(minePagePerson);
                                holder.detail.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                                minePagePerson.save();
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                    }
                        break;
                        default:
                            break;
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MinePersonAttr minePersonAttr = list.get(position);
        holder.detail.setText(minePersonAttr.getDetail());
        holder.name.setText(minePersonAttr.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}
