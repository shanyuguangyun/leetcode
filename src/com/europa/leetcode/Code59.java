package com.europa.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释
 * 滑动窗口的位置                最大值
 * ----------------------------------
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *
 * @author 山雨光云
 * @since 2023/8/31 21:46
 **/
public class Code59 {

    public static void main(String[] args) {
        Code59 instance = new Code59();
        int[] result = instance.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(result));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
//        int[] res = maxSlidingWindowUseLoop(nums, k);
        int[] res = maxSlidingWindowUsePriorityQueue(nums, k);
        return res;
    }

    /**
     * 使用暴力循环，记录下窗口内的最大值及其下标，如果移动后的入窗口的数大于最大数，则更新最大值及下标
     * 不然则判断旧的最大数是否在窗口内，如在，则不管，如不在，则循环出新的最大数
     * 但是当窗口接近数组长度时，此时时间复杂度为O(n^2)
     *
     * @param nums 数组
     * @param k    窗口长度
     * @return 最大值数组
     */
    public int[] maxSlidingWindowUseLoop(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];

        int max = nums[0];
        int maxIdx = 0;
        for (int i = 1; i < k; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                maxIdx = i;
            }
        }
        res[0] = max;
        for (int i = k; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                maxIdx = i;
            } else {
                if (maxIdx >= i - k + 1) {

                } else {
                    max = nums[i - k + 1];
                    maxIdx = i - k + 1;
                    for (int j = i - k + 2; j <= i; j++) {
                        if (nums[j] >= max) {
                            max = nums[j];
                            maxIdx = j;
                        }
                    }
                }
            }
            res[i - k + 1] = max;
        }
        return res;
    }

    /**
     * 使用优先队列，优先队列的好处是始终能peek出极值，如此窗口一边滑动，一边offer值人队列，然后peek出极值判断是否在窗口内，如不在则出队列即可。
     *
     * @param nums 数组
     * @param k    窗口长度
     * @return 最大值数组
     */
    public int[] maxSlidingWindowUsePriorityQueue(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o2[0] - o1[0];
                    }
                }
        );

        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        res[0] = pq.peek()[0];

        for (int i = k; i < nums.length; i++) {
            pq.offer(new int[]{nums[i], i});

            while (pq.peek()[1] < i - k + 1) {
                pq.poll();
            }
            res[i - k + 1] = pq.peek()[0];
        }

        return res;
    }
}
