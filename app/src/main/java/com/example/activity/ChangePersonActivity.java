package com.example.activity;

import androidx.annotation.RequiresApi;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adapter.MinePagePersonAttrAdapter;
import com.example.entity.MinePagePerson;
import com.example.entity.MinePersonAttr;
import com.example.news.R;
import com.example.util.CustomDialog;
import com.service.MinePagePersonAttrService;
import com.service.MinePagePersonService;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChangePersonActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;
    MinePagePersonService minePagePersonService = MinePagePersonService.getInstance();
    public String finalDir;
    public static final int PHOTO_STATUS_CODE = 100;
    private Uri pictureUri;
    private String pictureName;
    private File pictureFile;
    public static final int CHOOSE_PHOTO = 2;
    Bitmap bitmap;

    List<MinePersonAttr> listInitAttr = new ArrayList<>();
    RecyclerView recyclerView;
    String imagePath;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_person);


        toolbar = findViewById(R.id.change_person_toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView = findViewById(R.id.mine_picture_second);

        //属性初始化
        recyclerView = findViewById(R.id.recycler_view_person_attr);
        RecyclerView.LayoutManager layoutManagerA = new LinearLayoutManager(ChangePersonActivity.this);
        recyclerView.setLayoutManager(layoutManagerA);
        MinePagePersonAttrService minePagePersonAttrService = MinePagePersonAttrService.getInstance();
        listInitAttr = minePagePersonAttrService.getList();
        MinePagePersonAttrAdapter minePagePersonAttrAdapter = new MinePagePersonAttrAdapter(this, listInitAttr);
        recyclerView.setAdapter(minePagePersonAttrAdapter);

        //属性赋值
        minePagePersonAttrService.setMinePagePersonAttrService(minePagePersonService.getMinePagePerson().getName(), minePagePersonService.getMinePagePerson().getSex(), minePagePersonService.getMinePagePerson().getBirthday());

        //头像
        if (minePagePersonService.getHeadshot() == null) {
            imageView.setImageResource(R.drawable.touxiang);
            return;
        }
        else
        {
            byte[] images =minePagePersonService.getHeadshot();
            Bitmap bitmap=BitmapFactory.decodeByteArray(images,0,images.length);
            imageView.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (minePagePersonService.getHeadshot() == null) {
            imageView.setImageResource(R.drawable.touxiang);
            return;
        }
        else
        {
            byte[] images =minePagePersonService.getHeadshot();
            Bitmap bitmap=BitmapFactory.decodeByteArray(images,0,images.length);
            imageView.setImageBitmap(bitmap);
        }
    }

    //图片转换为字节
    private byte[]img(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        return byteArray;
    }

    public void On_Change_picture(View view) {
        PermissionManager();
        final CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.create();
        builder.getAlbum().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
                builder.getDialog().hide();
            }
        });
        builder.getCamera().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
                builder.getDialog().hide();
            }
        });
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
                    CameraDisPlayAndSavePhoto();
                    break;
                }
                case CHOOSE_PHOTO:
                    if (resultCode == RESULT_OK) {
                            handleImageOnKitKat(data);
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
    private void CameraDisPlayAndSavePhoto() {
        if (finalDir != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(finalDir);
            byte[]images=img(bitmap);
            minePagePersonService.setHeadshot(images);

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
        AlbumDisPlayAndSavePhoto(imagePath); // 根据图片路径显示图片
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

    private void AlbumDisPlayAndSavePhoto(String imagePath) {
        if (imagePath != null) {
            bitmap = BitmapFactory.decodeFile(imagePath);
            byte[]images=img(bitmap);
            minePagePersonService.setHeadshot(images);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }

    }

    //权限
    private void PermissionManager() {
        if
        (
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.WAKE_LOCK, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }


}
