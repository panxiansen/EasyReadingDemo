package com.example.administrator.easyreadingdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/10.
 */

public class PicBigActivity extends AppCompatActivity {

    private ImageView imageview;
    private Intent intent;
    private Bitmap bitmap;
    private String url;
    private static final int UPDATE_TEXT = 1;
    private File f;
    private File directory;
    private String picName;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    Toast.makeText(getApplicationContext(), "下载图片到/sdcard/EasyReading/pic/" + picName, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picbig_activity);
        init();
    }

    private void init(){
        imageview = (ImageView) findViewById(R.id.picbig_imageview);
        Bundle bundle = this.getIntent().getExtras();
        url = bundle.getString("url");

        Picasso.with(this).load(url).into(imageview);



        imageview.setLongClickable(true);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        imageview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

               try {
                    saveBitmap();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return true;
            }
        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        this.finish();
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
//
//        return super.onKeyLongPress(keyCode, event);
//    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void saveBitmap() throws IOException {

        directory = new File("/sdcard/EasyReading/pic/");
        if (!directory.exists()){
            directory.mkdirs();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bitmap = Picasso.with(getApplicationContext()).load(url).get();
                    picName = url.substring(43, url.length());

                    Log.e("TAGGGG", picName);
                    File f = new File(directory, picName);
                    FileOutputStream out = new FileOutputStream(f);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                    out.flush();
                    out.close();

                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();



    }

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            directory = new File("/sdcard/OnLineShop/pic/");
            if (!directory.exists()){
                directory.mkdirs();
            }

            File f = new File("/sdcard/EasyReading/pic/" + url + ".jpg");
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            try {
                out.flush();
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

}
