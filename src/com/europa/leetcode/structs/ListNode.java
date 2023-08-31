package com.europa.leetcode.structs;

/**
 * 链表
 * @author 山雨光云
 * @since 2023/8/30 22:12
 **/
public class ListNode {

    public ListNode next;
    public int val;

    public ListNode() {}
    public ListNode(int val) {this.val = val;}
    public ListNode(int val, ListNode next) {this.val = val; this.next = next;}
}
