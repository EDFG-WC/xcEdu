package com.xuecheng.manage_cms.dao.file;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Method2 {
    class ThreadToGo {
        int value = 1;
    }

    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();
    private final ThreadToGo threadToGo = new ThreadToGo();

    public Runnable newThread1() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            @Override
            public void run() {
                for (int i = 0; i < arr.length; i = i + 2) {
                    try {
                        //获得锁
                        lock.lock();
                        while (threadToGo.value == 2) {
                            condition.await();
                        }
                        Helper.print(arr[i], arr[i + 1]);
                        threadToGo.value = 2;
                        condition.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    public Runnable newThread2() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;

            @Override
            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    try {
                        lock.lock();
                        while (threadToGo.value == 1) {
                            condition.await();
                        }
                        Helper.print(arr[i]);
                        threadToGo.value = 1;
                        condition.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        Method2 method2 = new Method2();
        Helper.instance.run(method2.newThread1());
        Helper.instance.run(method2.newThread2());
        Helper.instance.shutdown();
    }
}
