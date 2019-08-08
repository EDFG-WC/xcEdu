package com.batch.testspringbatch.Algorithm;

public class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    //为节点追回节点
    public ListNode append(ListNode node) {
        //当前节点是当前对象
        ListNode currentNode = this;
        //循环向后找
        while (true) {
            //取出下一个节点
            ListNode nextNode = currentNode.next;
            //如果没有下一个节点
            if (nextNode == null) {
                break;
            }
            //赋给当前节点, 就是把指针挪到下个节点上, "下一个节点"成为了当前节点
            currentNode = nextNode;
        }
        //把需要追回的节点追加为当前节点的下一个节点
        currentNode.next = node;
        return this;
    }

    //获取下一个节点
    public ListNode next() {
        return this.next;
    }

    public int getData() {
        return this.value;
    }

    public boolean isLast() {
        return next == null;
    }
}
