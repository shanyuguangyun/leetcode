package com.europa.leetcode.structs;

/**
 * 链表
 *
 * @author 山雨光云
 * @since 2023/8/30 22:12
 **/
public class RandomListNode {

    public RandomListNode next;
    public int val;

    public RandomListNode random;


    public RandomListNode() {
    }

    public RandomListNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
