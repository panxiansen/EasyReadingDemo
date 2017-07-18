package com.example.administrator.easyreadingdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.easyreadingdemo.bean.News_HomeNews_Info;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/13.
 */

public class LoadNewsData {

    private List<News_HomeNews_Info.NewslistBean> mList = new ArrayList<>();
    private News_HomeNews_Info.NewslistBean mListBean = new News_HomeNews_Info.NewslistBean();
    private News_HomeNews_Info mInfo;
    private Context context;
    private String url;
    private int page;


    public void loadData(final NewsDataCallBack mcallback, String url, final int page){

        OkHttpClient client = new OkHttpClient();
        this.page = page;
        url = url + "&page=" + page;
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    Type type = new TypeToken<News_HomeNews_Info>(){}.getType();
                    Gson gson = new Gson();
                    mInfo = gson.fromJson(response.body().string(), type);
                    if (mInfo!=null) {

                        for (int i = 0;i<mInfo.getNewslist().size();i++){
                            mListBean = mInfo.getNewslist().get(i);
                            mList.add(mListBean);
                        }
                        //hui diao
                        if (mcallback !=null && page == 1) {
                            mcallback.getData(mList);
                            Log.e("TAGGG", "第一次回调");

                        }
                        if (page >1) {
                            mcallback.getDatas(mList, page);
                            Log.e("TAGGG", "下拉刷新回调");
                        }
                    }


                }
            }
        });



    }

}
