package com.europa.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 包含min函数的栈
 * 定义栈数据结构，要求min、push及pop的时间复杂度都为O(1)
 * 使用栈A保存正常数据，满足push和pop操作。
 * 使用栈B保存入栈的递减数据，满足能O(1)获取最小数。
 * A: | 3 <- 5 <- 2 <- 1
 * B: | 3      <- 2 <- 1
 * @author 山雨光云
 * @since 2023/8/30 23:19
 **/
public class Code155 {

    public static void main(String[] args) {
        Code155 instance = new Code155();
        MinStack stack = instance.new MinStack();

        stack.push(3);
        stack.push(5);
        stack.push(2);
        stack.push(1);

        System.out.println(stack.min());
        System.out.println(stack.pop());
        System.out.println(stack.min());
        System.out.println(stack.pop());
        System.out.println(stack.min());
    }

    /**
     * 包含min函数的栈
     */
    class MinStack {

        Deque<Integer> dequeA;
        Deque<Integer> dequeB;

        public MinStack() {
            dequeA = new LinkedList<>();
            dequeB = new LinkedList<>();
        }

        public void push(int x) {
            dequeA.push(x);
            if(dequeB.isEmpty()) {
                dequeB.push(x);
            } else {
                if(dequeB.peek() >= x) {
                    dequeB.push(x);
                }
            }
        }

        public int pop() {
            if(dequeA.isEmpty()) {return 0;}
            Integer topVal = dequeA.pop();
            if(topVal.equals(dequeB.peek())) {
                dequeB.pop();
            }
            return topVal;
        }

        public int min() {
            if(dequeB.isEmpty()) {return 0;}
            return dequeB.peek();
        }
    }
}
