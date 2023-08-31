package com.europa.leetcode;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * s---------e
 * 1 2 3 4 5
 * s---------e
 * 入栈的数导出到另一个栈即可
 * | 1 2 3 4 5
 * | 5 4 3 2 1
 *
 * @author 山雨光云
 * @since 2023/8/31 21:31
 **/
public class Code154_1 {
    public static void main(String[] args) {
        Code154_1 instance = new Code154_1();
        CQueue cQueue = instance.new CQueue();
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        cQueue.deleteHead();
        cQueue.deleteHead();
    }

    class CQueue {
        Stack<Integer> stackA;
        Stack<Integer> stackB;

        public CQueue() {
            stackA = new Stack<>();
            stackB = new Stack<>();
        }

        public void appendTail(int value) {
            stackA.push(value);
        }

        public int deleteHead() {
            if (stackA.isEmpty() && stackB.isEmpty()) {
                return -1;
            } else {
                if (stackB.isEmpty()) {
                    while (!stackA.isEmpty()) {
                        stackB.push(stackA.pop());
                    }
                }
                return stackB.pop();
            }
        }
    }
}
