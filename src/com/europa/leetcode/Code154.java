package com.europa.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 包含max函数的队列
 * 要求实现队列，包含offer、poll、max函数 时间复杂度都为O(1)
 * 使用正常队列满足offer和poll函数
 * s----------e
 * 4 2 1 5 6 3
 * s----------e
 * 使用双端队列维护倒序结构，每个数入队清空比其小的数
 * s----------e
 * 4 2 1
 * s----------e
 * s----------e
 * 6 3
 * s----------e
 * 使用双端队列维护max函数
 * 双端队列数据保持递减列表
 *
 * @author 山雨光云
 * @since 2023/8/30 23:37
 **/
public class Code154 {

    public static void main(String[] args) {
        Code154 instance = new Code154();
        MaxQueue maxQueue = instance.new MaxQueue();

        maxQueue.offer(4);
        maxQueue.offer(2);
        maxQueue.offer(1);
        maxQueue.offer(5);
        maxQueue.offer(6);
        maxQueue.offer(3);
        maxQueue.poll();
        maxQueue.max();
    }

    class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> deque;

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public void offer(int x) {
            queue.add(x);
            if (deque.isEmpty()) {
                deque.addLast(x);
            } else {
                while (!deque.isEmpty() && deque.peekLast() < x) {
                    deque.removeLast();
                }
                deque.addLast(x);
            }
        }

        public int poll() {
            if (queue.isEmpty()) {
                return -1;
            }
            Integer poll = queue.poll();
            if (poll.equals(deque.peekFirst())) {
                deque.pollFirst();
            }
            return poll;
        }

        public int max() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }
    }
}
