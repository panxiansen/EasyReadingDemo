package com.example.administrator.easyreadingdemo;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.easyreadingdemo.bean.Pic_Info;
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
 * Created by Administrator on 2017/7/6.
 */

public class LoadPicData {

    private List<Pic_Info.ImgsBean> mList = new ArrayList<>();
    private Pic_Info mInfo;
    private String url;
    private int page;
    private Context context;



    public void loadData(final PicDataCallBack mcallback, String url, final int page){

        url = url + "&pn=" + page + "&rn=10&p=channel&from=1" ;

        OkHttpClient client = new OkHttpClient();
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
                    Type type = new TypeToken<Pic_Info>(){}.getType();
                    Gson gson = new Gson();
                    mInfo = gson.fromJson(response.body().string(), type);
                    if (mInfo != null){

                        for (int i = 0; i<mInfo.getImgs().size();i++){
                            mList.add(mInfo.getImgs().get(i));
                        }

                        if (mcallback != null && page == 1){
                            mcallback.getData(mList);

                        }

                        if (page>1){

                            mcallback.getDatas(mList, page);
                        }

                    }
                }

            }
        });
    }


}
