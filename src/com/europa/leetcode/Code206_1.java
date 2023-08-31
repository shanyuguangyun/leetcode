package com.europa.leetcode;

import com.europa.leetcode.structs.ListNode;
import com.europa.leetcode.structs.StructsHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 从尾到头打印链表
 *
 * @author 山雨光云
 * @since 2023/8/30 23:06
 **/
public class Code206_1 {

    public static void main(String[] args) {
        Code206_1 instance = new Code206_1();
        ListNode head = StructsHelper.toListNode(new int[]{1, 3, 2, 5, 7, 8, 11});

        int[] array = instance.reversePrint(head);
        System.out.println(Arrays.toString(array));
    }

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        recursive(head, list);

        int[] array = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 此递归比206要容易理解，这里主要是回溯时将val加入动态数组。
     */
    private void recursive(ListNode head, List<Integer> list) {
        if(head == null) {return;}
        recursive(head.next, list);
        list.add(head.val);
    }
}
