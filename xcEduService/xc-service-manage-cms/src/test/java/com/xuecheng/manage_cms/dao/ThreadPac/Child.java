package com.xuecheng.manage_cms.dao.ThreadPac;

public class Child extends Thread {
    public void run() {
        while (true) {
            synchronized (Barrel.barrel) {
                //1. 筐里没水果了就让小孩休息
                if (Barrel.barrel.size() == 0) {
                    try {
                        Barrel.barrel.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //2.小孩吃水果
                Barrel.barrel.remove("apple");
                System.out.println("小孩吃了一个水果,目前筐里有" + Barrel.barrel.size() + "个水果");
                //3.唤醒农夫继续装水果
                Barrel.barrel.notify();
            }
            //4.模拟控制速度
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
