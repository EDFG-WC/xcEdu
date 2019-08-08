package com.batch.testspringbatch.Algorithm.TwoSum;

public class TwoSum {
    public static void main(String[] args) {
        ListNode num1 = new ListNode(2);
        ListNode num2 = new ListNode(4);
        ListNode num3 = new ListNode(3);
        num1.next = num2;
        num2.next = num3;
        num3 = null;
        ListNode num4 = new ListNode(5);
        ListNode num5 = new ListNode(6);
        ListNode num6 = new ListNode(4);
        num4.next = num5;
        num5.next = num6;
        num6.next = null;
        ListNode listNode = addTwoNumbers(num1, num4);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //dummyHead是一个哑节点(值为空的节点)
        ListNode dummyHead = new ListNode(0);
        //curr和dummyHead指向同一个地址; curr变动, dummyHead也会变动
        ListNode p = l1, q = l2, curr = dummyHead;
        //代表进位, 有1和0
        int carry = 0;
        //p和q不为空作为循环条件, 代表着每次进位时"当前节点"要往前蹿.
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
