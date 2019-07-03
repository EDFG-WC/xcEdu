package com.xuecheng.manage_cms.dao.ThreadPac;

import com.xuecheng.manage_cms.utils.ThreadPoolUtil;

import java.util.concurrent.Callable;

public class ThreadTest001 {
    static int id = 100;
    static Object obj = new Object();

    public static void main(String[] args) {
        ThreadPoolUtil threadPoolUtil = ThreadPoolUtil.getsInstance();
        for (int i = 0; i <100 ; i++) {
            threadPoolUtil.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName()
                                + "卖了编号为" + id + "的火车票");
                        id--;
                    }
                }
            });
        }
    }
}