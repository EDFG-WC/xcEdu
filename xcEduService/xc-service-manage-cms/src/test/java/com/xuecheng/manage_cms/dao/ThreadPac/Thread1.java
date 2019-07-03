package com.xuecheng.manage_cms.dao.ThreadPac;

public class Thread1 extends Thread{
    public void run() {
        for (int i = 0; i <11 ; i++) {
            synchronized (MyLock.o) {
                System.out.println(1);
                MyLock.o.notify();
                try {
                    MyLock.o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
