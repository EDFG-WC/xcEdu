package com.batch.testspringbatch.Algorithm;

public class NodeTest {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.append(node2).append(node3);
        System.out.println(node1.next().next().getData());
    }
}
