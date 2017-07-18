package com.example.administrator.easyreadingdemo.fragment;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.administrator.easyreadingdemo.OnItemClickListener;
import com.example.administrator.easyreadingdemo.R;
import com.example.administrator.easyreadingdemo.adapter.Music_RecyclerView_Adapter;
import com.example.administrator.easyreadingdemo.bean.Music_Info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/7/12.
 */

public class Music_Fragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private Music_RecyclerView_Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Music_Info> musicList;

    private ImageView play;
    private ImageView next;
    private ImageView back;
    private int targetmusic;//播放的音乐

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private SeekBar mSeekBar;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.music_fragment, null);

        getAllMusicList(getContext());
        initView(view);

        return view;
    }

    //初始化控件
    private void initView(View view) {

        handler = new Handler();

        //音乐按钮
        back = (ImageView) view.findViewById(R.id.music_fragment_back);
        play = (ImageView) view.findViewById(R.id.music_fragment_play);
        next = (ImageView) view.findViewById(R.id.music_fragment_next);

        back.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);

        mSeekBar = (SeekBar) view.findViewById(R.id.music_fragment_seekbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.music_fragment_recyclerview);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Music_RecyclerView_Adapter(musicList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("TAGG", " " + mediaPlayer.getCurrentPosition());
                try {
                    if (mediaPlayer.isPlaying()){
                        resetMediaPlayer();
                    }
                    mediaPlayer.setDataSource(musicList.get(position).path);
                    targetmusic = position;
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    play.setImageResource(R.mipmap.music_pic_stop);
                    setmSeekBar(position);
                    handler.post(updateRun);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    //SeekBar进度条更新进程
    Runnable updateRun = new Runnable() {
        @Override
        public void run() {
            mSeekBar.setProgress(mediaPlayer.getCurrentPosition()/1000);
            handler.postDelayed(updateRun,1000);
        }
    };

    //重设MediaPlayer信息
    private void resetMediaPlayer(){
        mediaPlayer.stop();
        mediaPlayer.reset();
    }



    //歌曲切换方法
    private void changeMusic(int position){
        mediaPlayer.stop();
        mediaPlayer.reset();
        play.setImageResource(R.mipmap.music_pic_stop);
        try {
            mediaPlayer.setDataSource(musicList.get(position).path);
            mediaPlayer.prepare();
            mediaPlayer.start();
            setmSeekBar(position);
            handler.post(updateRun);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //获取本地音乐信息的方法
    public List<Music_Info> getAllMusicList(Context context){
        Cursor cursor = null;

        musicList = new ArrayList<>();

        cursor = getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.ALBUM_ID},
                null,null,null);

        if (cursor == null){
            Log.d("TAG", "The getMusicList cursor is null");
            return musicList;
        }

        int count = cursor.getCount();
        if (count <= 0){
            Log.d(TAG, "The getMusicList cursor count is 0.");
            return musicList;
        }

        musicList = new ArrayList<Music_Info>();
        Music_Info music_info = null;
        while (cursor.moveToNext()) {
            music_info = new Music_Info();
            music_info.id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            music_info.title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            music_info.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            music_info.duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            music_info.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            music_info.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

            music_info.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            music_info.path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            music_info.album_id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            music_info.album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            musicList.add(music_info);

        }

            if(cursor != null) {
                cursor.close();
            }
        Log.e("TAG", " " + musicList.size());


        return musicList;
    }


    /**
     * 根据时间和大小，来判断所筛选的media 是否为音乐文件，具体规则为筛选小于30秒和1m一下的
     */
    public static boolean checkIsMusic(int time, long size) {
        if(time <= 0 || size <= 0) {
            return  false;
        }

        time /= 1000;
        int minute = time / 60;
//  int hour = minute / 60;
        int second = time % 60;
        minute %= 60;
        if(minute <= 0 && second <= 30) {
            return  false;
        }
        if(size <= 1024 * 1024){
            return false;
        }
        return true;
    }

    //设置或初始化seekbar信息方法
    private void setmSeekBar(int position){
        mSeekBar.setMax((musicList.get(position).duration/1000));
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true){
                    mediaPlayer.seekTo(progress*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.music_fragment_back:
                //若目前播放的歌曲是第1首，按back直接播放最后一首
                if (targetmusic == 0){
                    targetmusic = musicList.size();
                }else{
                    targetmusic = targetmusic - 1;
                }
                changeMusic(targetmusic);

                break;

            case R.id.music_fragment_play:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setImageResource(R.mipmap.music_pic_play);
                }else{
                    play.setImageResource(R.mipmap.music_pic_stop);
                    mediaPlayer.start();
                }
                break;

            case R.id.music_fragment_next:
                if (targetmusic == musicList.size()){
                    targetmusic = 0 ;
                }else{
                    targetmusic = targetmusic + 1;
                }
                changeMusic(targetmusic);

                break;
        }
    }
}

