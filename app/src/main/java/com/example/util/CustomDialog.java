package com.example.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;

import com.example.news.R;


public class CustomDialog extends Dialog {


    public CustomDialog(Activity activity) {
        super(activity);
    }

    public static class Builder   {
        private Activity activity;
        private ImageView camera;
        private ImageView album;
        public ImageView showimage;
        private CustomDialog dialog;
        public ImageView getCamera(){return  camera;}
        public ImageView getAlbum(){return album;}
        public  CustomDialog getDialog(){
            return  dialog;
        }

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new CustomDialog(activity);
            View layout = inflater.inflate(R.layout.picture_dialog, null);
            camera = layout.findViewById(R.id.camera);
            album = layout.findViewById(R.id.album);
            showimage = layout.findViewById(R.id.mine_picture_second);
            dialog.setContentView(layout);
            dialog.show();
            return dialog;
        }


    }
}
