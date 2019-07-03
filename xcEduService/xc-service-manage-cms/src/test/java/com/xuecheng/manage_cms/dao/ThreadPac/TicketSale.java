package com.xuecheng.manage_cms.dao.ThreadPac;

public class TicketSale implements Runnable {
    private int id = 100;
    Object obj = new Object();

    @Override
    public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (obj) {
                    if (id > 0) {
                    System.out.println(Thread.currentThread().getName()
                            + "卖了编号为" + id + "的火车票");
                    id--;
//                    try {
//                        Thread.sleep(600);
//                    } catch (InterruptedException e) {
//                    }
                }
            }
        }
    }
}
