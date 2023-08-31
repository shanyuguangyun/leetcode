package com.europa.leetcode;

import com.europa.leetcode.structs.ListNode;
import com.europa.leetcode.structs.StructsHelper;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 链表反转
 * 使用递归、循环、辅助栈
 *
 * @author 山雨光云
 * @since 2023/8/30 22:06
 **/
public class Code206 {

    public static void main(String[] args) {
        Code206 instance = new Code206();
        ListNode head = StructsHelper.toListNode(new int[]{1, 3, 2, 5, 7, 8, 11});

        ListNode afterReversed = instance.reverseListNode(head);
        int[] array = StructsHelper.toArray(afterReversed);

        System.out.println(Arrays.toString(array));
    }

    public ListNode reverseListNode(ListNode head) {
//        ListNode afterReversed = reverseByStack(head);
//        ListNode afterReversed = reverseByLoop(head);
        ListNode afterReversed = reverseByRecursive(head);
        return afterReversed;
    }

    /**
     * 递归是相对难理解的做法
     * @param head 链表头
     * @return 反转后的链表
     */
    private ListNode reverseByRecursive(ListNode head) {
        if(head == null || head.next == null) {return head;}
        // 假设链表为 1->2->3，则最底层返回时 head为2->3 newNode为3
        ListNode newNode = reverseByRecursive(head.next);
        // 此时链表为环 2->3->2->3->2...
        head.next.next = head;
        // 此操作后 head为2->null 而newNode为 3->2->null 接到了head前面 超级难理解。
        // 总体意思是成环后将自身next置为null，而newNode因为本身为next，所以是下一个head节点会成null。可死记硬背。
        head.next = null;
        return newNode;
    }

    private ListNode reverseByLoop(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            // 假设链表为 1->2->3 则next为 2->3
            ListNode next = curr.next;
            // curr.next = null 则 1->null
            curr.next = prev;
            // prev = 1->null
            prev = curr;
            // curr = 2->3
            curr = next;
        }
        return prev;
    }

    public ListNode reverseByStack(ListNode head) {
        Deque<Integer> deque = new LinkedList<>();
        ListNode curr = head;
        while (curr != null) {
            deque.push(curr.val);
            curr = curr.next;
        }

        ListNode prev = new ListNode(0);
        curr = prev;
        while (!deque.isEmpty()) {
            curr.next = new ListNode(deque.pop());
            curr = curr.next;
        }
        return prev.next;
    }

}
