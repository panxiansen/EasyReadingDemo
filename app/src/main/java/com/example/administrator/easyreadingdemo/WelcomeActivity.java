package com.example.administrator.easyreadingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.easyreadingdemo.adapter.WelcomePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mWelcomeViewPager;
    private LinearLayout mWelcomePoints;
    private int[] mResIds = {R.mipmap.welcome_pic1,R.mipmap.welcome_pic2,R.mipmap.welcome_pic3};
    private WelcomePagerAdapter mPagerAdapter;
    private ImageButton mWelcomeButton;
//    private TextView mTextView;

    //all imageviews
    private List<ImageView> mImageView = new ArrayList<ImageView>();
    private int mSelectedPageNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initViews();



    }

    private void initViews(){
//        mTextView = (TextView) findViewById(R.id.activity_welcome_textview_skip);
//        mTextView.setOnClickListener(this);
        mWelcomeButton = (ImageButton) findViewById(R.id.activity_welcome_button);
        mWelcomeButton.setOnClickListener(this);
        mWelcomeViewPager = (ViewPager) findViewById(R.id.activity_welcome_viewpager);
        mWelcomeViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                mImageView.get(mSelectedPageNo).setSelected(false);
                mSelectedPageNo = position;
                mImageView.get(position).setSelected(true);

                //判断，当滑动到第三页，出现转到主页面的控件
                if (position == 2){
                    mWelcomeButton.setVisibility(View.VISIBLE);
                }else {
                    mWelcomeButton.setVisibility(View.INVISIBLE);
                }
            }


        });

        mWelcomePoints = (LinearLayout) findViewById(R.id.activity_welcome_points);
        mPagerAdapter = new WelcomePagerAdapter(getApplicationContext(), mResIds);
        mWelcomeViewPager.setAdapter(mPagerAdapter);

        addPoints();

    }

    private void addPoints(){
        ImageView imageView;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.rightMargin = 10;

        for (int i = 0; i<mResIds.length; i++){
            imageView = new ImageView(getApplication());
            imageView.setLayoutParams(params);

            //imageView.setImageResource(R.drawable.page)
            imageView.setImageResource(R.drawable.selector_welcome_points);
            imageView.setSelected(false);

            mImageView.add(imageView);
            mWelcomePoints.addView(imageView);
        }

        mImageView.get(0).setSelected(true);
        mSelectedPageNo = 0;

    }

    public void onClick(View view){
        if (view.getId() == R.id.activity_welcome_button){
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

//    public void doSkip(View view){
//        if (view.getId() == R.id.activity_welcome_textview_skip){
//            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }



}
