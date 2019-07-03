package com.xuecheng.manage_cms.dao.ThreadPac;

import com.xuecheng.manage_cms.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadParser implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ThreadParser.class);

    private String taskId;

    private String topicName;

    private ThreadPoolUtil threadPoolUtil;

    public ThreadParser(String taskId, String topicName, ThreadPoolUtil threadPoolUtil) {
        this.taskId = taskId;
        this.topicName = topicName;
        this.threadPoolUtil = threadPoolUtil;
    }

    @Override
    public void run() {
        log.info("run method running");
        for (int i = 0; i < 100; i++) {
            System.out.println("my name is " + i + "doll" + Thread.currentThread().getName());
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("loop ends");
        System.out.println(Thread.currentThread().getName()+Thread.currentThread().getState());
    }
}
