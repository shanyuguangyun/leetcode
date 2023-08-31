package com.europa.leetcode.structs;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速构建数据结构工具
 *
 * @author 山雨光云
 * @since 2023/8/30 22:13
 **/
public class StructsHelper {

    /**
     * 将数组转换为链表
     * @param array 数据数组
     * @return 链表
     */
    public static ListNode toListNode(int[] array) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int arr : array) {
            curr.next = new ListNode(arr);
            curr = curr.next;
        }
        return head.next;
    }

    /**
     * 将链表转换为数组
     * @param head 链表头节点
     * @return 数组
     */
    public static int[] toArray(ListNode head) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        curr = head;
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++) {
            array[i] = curr.val;
            curr = curr.next;
        }
        return array;
    }
}
