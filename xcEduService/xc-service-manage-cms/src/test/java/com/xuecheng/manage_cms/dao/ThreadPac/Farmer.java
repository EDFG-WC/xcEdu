package com.xuecheng.manage_cms.dao.ThreadPac;

public class Farmer extends Thread {
    public void run() {
        while (true) {
            synchronized (Barrel.barrel) {
                //1.筐放满了就让农夫休息
                if (Barrel.barrel.size() == 10) {
                    try {
                        Barrel.barrel.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //2.往筐里放水果
                Barrel.barrel.add("apple");
                System.out.println("农夫放了一个水果, 目前筐里有" + Barrel.barrel.size() + "个水果");
                //3.唤醒小孩继续吃
                Barrel.barrel.notify();
            }
            //4.模拟速度控制
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
