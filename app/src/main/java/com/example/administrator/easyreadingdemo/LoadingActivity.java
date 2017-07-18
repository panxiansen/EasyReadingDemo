package com.example.administrator.easyreadingdemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/7/17.
 */

public class LoadingActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //定义SharedPreference记录APP是否第一次开启
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.adminstrator.jiakaobaodian.start_time",0);
        final Boolean use_first = sharedPreferences.getBoolean("FIRST",true);

        imageView = (ImageView) findViewById(R.id.activity_loading_imageview);

        if (use_first){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    sharedPreferences.edit().putBoolean("FIRST",false).commit();
                    Intent mainIntent = new Intent(LoadingActivity.this, WelcomeActivity.class);
                    LoadingActivity.this.startActivity(mainIntent);
                    LoadingActivity.this.finish();
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }, 3000);

        }else{
            new Handler().postDelayed(new Runnable() {
                @TargetApi(Build.VERSION_CODES.ECLAIR)
                @Override
                public void run() {
                    Intent mainIntent = new Intent(LoadingActivity.this, MainActivity.class);
                    LoadingActivity.this.startActivity(mainIntent);
                    LoadingActivity.this.finish();
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                 }
            }, 3000);

        }
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_in);
        imageView.startAnimation(animation);
    }



}
