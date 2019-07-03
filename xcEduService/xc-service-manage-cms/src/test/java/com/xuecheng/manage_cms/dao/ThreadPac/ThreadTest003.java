package com.xuecheng.manage_cms.dao.ThreadPac;

public class ThreadTest003 {
    public static void main(String[] args) {
        TicketSale ts = new TicketSale();
        Thread thread1 = new Thread(ts);
        Thread thread2 = new Thread(ts);
        thread1.setName("窗口A");
        thread2.setName("窗口B");
        thread1.start();
        thread2.start();
    }
}
