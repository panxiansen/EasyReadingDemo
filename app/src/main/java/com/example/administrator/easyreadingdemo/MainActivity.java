package com.example.administrator.easyreadingdemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.easyreadingdemo.fragment.Music_Fragment;
import com.example.administrator.easyreadingdemo.fragment.News_Fragment;
import com.example.administrator.easyreadingdemo.fragment.Pic_Fragment;

import static com.example.administrator.easyreadingdemo.R.id.nav_pic;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private long firstTime = 0;

    private int itemCount = 1;

    private Toolbar toolbar;
    private News_Fragment news_fragment;
    private Pic_Fragment pic_fragment;
    private Music_Fragment music_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        setFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        int itemid = item.getItemId();



        switch (itemid){

            case nav_pic:
                if (pic_fragment != null){
                    toolbar.setTitle("图片浏览");
                    transaction.show(pic_fragment);
                    if (news_fragment != null){
                        transaction.hide(news_fragment);
                    }
                    if (music_fragment != null){
                        transaction.hide(music_fragment);
                    }
                    break;
                }else{
                    pic_fragment = new Pic_Fragment();
                    transaction.add(R.id.mainactivity_framelayout, pic_fragment);
                    if (news_fragment != null){
                        transaction.hide(news_fragment);
                    }
                    if (music_fragment != null){
                        transaction.hide(music_fragment);
                    }
                    toolbar.setTitle("图片浏览");
                    break;
            }


            case R.id.nav_read:
                transaction.show(news_fragment);
                toolbar.setTitle("随心阅读");
                if (news_fragment != null){
                    if (pic_fragment != null){
                        transaction.hide(pic_fragment);
                    }
                    if (music_fragment != null){
                        transaction.hide(music_fragment);
                    }
                    break;
                }else{
                    news_fragment = new News_Fragment();
                    transaction.add(R.id.mainactivity_framelayout, news_fragment);
                    toolbar.setTitle("随心阅读");
                    break;
                }

            case R.id.nav_music:
                if (music_fragment != null){
                    transaction.show(music_fragment);
                    toolbar.setTitle("音乐播放");
                    if (pic_fragment != null){
                        transaction.hide(pic_fragment);
                    }
                    if (news_fragment != null){
                        transaction.hide(news_fragment);
                    }
                }else{
                    music_fragment = new Music_Fragment();
                    transaction.add(R.id.mainactivity_framelayout, music_fragment);
                    if (pic_fragment != null){
                        transaction.hide(pic_fragment);
                    }
                    if (news_fragment != null){
                        transaction.hide(news_fragment);
                    }
                    toolbar.setTitle("音乐播放");
                }
                break;

        }

        transaction.commit();


//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_pic) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        news_fragment = new News_Fragment();
        transaction.add(R.id.mainactivity_framelayout,news_fragment);
        transaction.commit();

    }

    //双击退出方法
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {

            long secondTime =System.currentTimeMillis();
            if (secondTime - firstTime>2000){
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            }else{
                System.exit(0);
            }
        }

        return super.onKeyUp(keyCode, event);
    }
}
