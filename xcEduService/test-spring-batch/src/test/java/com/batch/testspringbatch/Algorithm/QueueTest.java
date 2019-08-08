package com.batch.testspringbatch.Algorithm;

public class QueueTest {
    public static void main(String[] args) {
        MyQueue mq = new MyQueue();
        mq.add(9);
        mq.add(8);
        mq.add(7);
        System.out.println(mq.poll());
    }
}
