package com.example.daydzk1.di;

import android.util.Log;

import com.example.daydzk1.app.app;
import com.example.daydzk1.bean.news;
import com.example.daydzk1.bean.work;
import com.example.daydzk1.bean.workDao;
import com.example.daydzk1.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/9/2.
 */

public class moudleimp implements  icontract.imoudle {

    private  String path= "https://www.apiopen.top/meituApi?page=1";
    @Override
    public void requestdata(final oncalllisten oncalllisten) {
        //①获取数据库
        final workDao workDao = app.getinstance().daoSession().getWorkDao();
        //全查
        final List<work> works = workDao.loadAll();
        //判断
        if (works.size()>0){
            //进行接口回传
            oncalllisten.responsemsg(works);
            return;
        }
        //②查不到进行网络请求
        HttpUtils httpUtils = HttpUtils.getinstance();
        httpUtils.getpost(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
                oncalllisten.responsemsg(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 //成功
                String json = response.body().string();
                //进行解析
                Gson gson = new Gson();
                //获取数据
                news news = gson.fromJson(json, news.class);
                //Log.i("eee",news+"");
                //得到对象
                List<com.example.daydzk1.bean.news.DataBean> results = news.getData();
                //创建新集合进行回传
                ArrayList<work> works1 = new ArrayList<>();
                //进行遍历
                for (int i = 0; i < results.size(); i++) {
                     //获取数据
                    String url = results.get(i).getUrl();
                    String desc = results.get(i).getType();
                    //创建对象
                    work work = new work();
                    work.setImgurl(url);
                    work.setTitle1(desc);
                    //添加到集合中
                    works1.add(work);
                }
                //接口回传
                oncalllisten.responsemsg(works1);
                //存到数据库
               workDao.insertInTx(works1);
            }
        });
    }
}
