package com.example.administrator.easyreadingdemo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.easyreadingdemo.bean.News_HomeNews_Info;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewsOkHttp {

    private String url;

    private int page;//页数

    private int num;//每页新闻条数

    private Context context;

    public News_HomeNews_Info mInfo;

    public List<News_HomeNews_Info.NewslistBean> mList;

    public NewsOkHttp(String url, int page, Context context){

        this.url = url;
        this.page = page;
        this.context = context;

//        getDatas();
//        showDatas();

    }

    public void getDatas(){
        OkHttpClient client = new OkHttpClient();
        url = url + "&page=" + page;
        Request request = new Request.Builder().url(url).build();
        mInfo = new News_HomeNews_Info();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "链接失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    Type type = new TypeToken<News_HomeNews_Info>(){}.getType();
                    Gson gson = new Gson();
                    mInfo = gson.fromJson(response.body().string(), type);
                    mList = mInfo.getNewslist();
                    Log.e("TAG", mList.get(0).getTitle());

                }


            }
        });

    }



}
