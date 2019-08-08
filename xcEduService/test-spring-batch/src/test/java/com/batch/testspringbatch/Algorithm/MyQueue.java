package com.batch.testspringbatch.Algorithm;

public class MyQueue {
    int[] elements;

    public MyQueue() {
        elements = new int[0];
    }

    //入列
    public void add(int element) {
        //创建新数组
        int[] newArr = new int[elements.length + 1];
        //复制原数组值到新数组
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        //添加新元素
        newArr[elements.length] = element;
        //新数组替换旧数组
        elements = newArr;
    }

    //出列
    public int poll() {
        //把数组中的第0个元素取出来
        int element = elements[0];
        //创建一个新的数组
        int[] newArr = new int[elements.length - 1];
        //把除去最后一个元素的其他元素放进新数组
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = elements[i + 1];
        }
        elements = newArr;
        return element;
    }

    public boolean isEmpty() {
        return elements.length == 0;
    }
}
