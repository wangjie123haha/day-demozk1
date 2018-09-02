package com.example.daydzk1.di;

import com.example.daydzk1.bean.work;

import java.util.List;

/**
 * Created by lenovo on 2018/9/2.
 */

public interface icontract {
    /**
     * iview层
     */
    public  interface  iview{
        //显示方法
        void  showdata(List<work> works);
    }
    /**
     * ipresebnter层
     */
    public  interface  ipresenter<iview>{
        //关联
        void  attachview(iview iview);
        //取消
        void  detachview(iview iview);
        //逻辑方法
        void  requestinfo();
    }
    /**
     * imoudle层
     */
    public  interface  imoudle{
        //接口
        public  interface  oncalllisten{
            //请求方法
            void  responsemsg(List<work> works);
        }
        //方法
        void  requestdata(oncalllisten oncalllisten);
    }
}
