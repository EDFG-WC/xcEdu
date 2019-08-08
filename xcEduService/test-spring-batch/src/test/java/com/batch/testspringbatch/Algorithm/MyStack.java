package com.batch.testspringbatch.Algorithm;

public class MyStack {
    int[] elements;

    public MyStack() {
        elements = new int[0];
    }

    //压入元素
    public void push(int element) {
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

    //取出栈顶元素
    public int pop() {
        if (elements.length == 0) {
            throw  new RuntimeException("stack is empty.");
        }
        //取出数组的最后一个元素
        int element = elements[elements.length - 1];
        //创建一个新的数组
        int[] newArr = new int[elements.length - 1];
        //把除去最后一个元素的其他元素放进新数组
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
        return element;
    }

    //查看栈顶元素
    public int peek(){
        if (elements.length == 0) {
            throw  new RuntimeException("stack is empty.");
        }
        return elements[elements.length-1];
    }

    public boolean isEmpty() {
        return elements.length == 0;
    }
}
