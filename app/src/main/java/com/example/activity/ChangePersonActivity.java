package com.example.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.MinePageInformationAdapter;
import com.example.adapter.MinePagePersonAttrAdapter;
import com.example.entity.MinePersonAttr;
import com.example.news.R;
import com.service.MinePageInformationService;
import com.service.MinePagePersonAttrService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChangePersonActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;

    public String finalDir;       //最终路径
    public static final int PHOTO_STATUS_CODE = 100;
    private Uri pictureUri;
    private String pictureName;
    private File pictureFile;        //图片文件
    public static final int CHOOSE_PHOTO = 2;

    private List<MinePersonAttr> list = new ArrayList<>();
    MinePagePersonAttrService minePagePersonAttrService;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_person);
        SetObj();
        ListenerManager();
        //Information
        minePagePersonAttrService = minePagePersonAttrService.getInstance();
        list = minePagePersonAttrService.getList();
        recyclerView =findViewById(R.id.recycler_view_person_attr);
        RecyclerView.LayoutManager layoutManagerA = new LinearLayoutManager(ChangePersonActivity.this);
        recyclerView.setLayoutManager(layoutManagerA);
        MinePagePersonAttrAdapter minePagePersonAttrAdapter = new MinePagePersonAttrAdapter(this,list);
        recyclerView.setAdapter(minePagePersonAttrAdapter);

    }
    private void ListenerManager()
    {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void SetObj()
    {
        toolbar   = findViewById(R.id.change_person_toolbar);
        imageView = findViewById(R.id.mine_picture_second);

    }

    public void On_Change_picture(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePersonActivity.this);
        PermissionManager();
        builder.setTitle("提示");
        builder.setMessage("请选择照片来源");
        //	第一个按钮
        builder.setPositiveButton("相册", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                //	提示信息
                openAlbum();
            }
        });
        //	中间的按钮
        builder.setNeutralButton("相机", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                startCamera();
            }
        });

        builder.create().show();

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        createImageFile();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pictureUri = FileProvider.getUriForFile(this, "com.example.news.fileprovider", pictureFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(intent, PHOTO_STATUS_CODE);
    }

    private void createImageFile() {
        pictureName = Calendar.getInstance().getTimeInMillis() + ".jpg";//以当前时间的毫秒值为名称
//        pictureFile =  new File (Environment.getExternalStorageDirectory().getAbsolutePath()+
////                "/"+SD_DIR_NAME+"/"+PHOTO_DIR_NAME+"/",pictureName);
        pictureFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/", pictureName);
        finalDir = pictureFile.getAbsolutePath();//图片的绝对路径后续要用
        pictureFile.getParentFile().mkdir();//按设置好的目录层级创建
        //不加这句会报Read-only警告。且无法写入SD
        pictureFile.setWritable(true);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_STATUS_CODE: {
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), pictureUri);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    savePhotoToSD(bitmap);
                    updateSystemGallery();
                    showPhoto();
                    break;
                }
                case CHOOSE_PHOTO:
                    if (resultCode == RESULT_OK) {
                        // 判断手机系统版本号
                        if (Build.VERSION.SDK_INT >= 19) {
                            // 4.4及以上系统使用这个方法处理图片
                            handleImageOnKitKat(data);
                        } else {
                            // 4.4以下系统使用这个方法处理图片
                            handleImageBeforeKitKat(data);
                        }
                    }
                    break;
                default:
                    break;

            }
        }
    }
    private void updateSystemGallery() {
        //把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    pictureFile.getAbsolutePath(), pictureName, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + finalDir)));

    }

    //显示照片
    private void showPhoto() {
        if (finalDir != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(finalDir);
            ImageView picture = findViewById(R.id.mine_picture_second);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePhotoToSD(Bitmap bitmap) {

        BufferedOutputStream os = null;
        try {
            //设置输出流
            os = new BufferedOutputStream(new FileOutputStream(pictureFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os); //100表示不压缩

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    //不论是否异常都要关闭流
                    os.flush();
                    os.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 相册
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ImageView picture = findViewById(R.id.mine_picture_second);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
    //权限
    private void PermissionManager() {
        if
        (
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        ||ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                        ||ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.WAKE_LOCK, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }



}
