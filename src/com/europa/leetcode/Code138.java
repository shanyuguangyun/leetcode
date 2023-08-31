package com.europa.leetcode;

import com.europa.leetcode.structs.RandomListNode;

import java.util.HashMap;
import java.util.Random;

/**
 * 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 *
 * @author 山雨光云
 * @since 2023/8/31 23:05
 **/
public class Code138 {

    public static void main(String[] args) {
        Code138 instance = new Code138();

        int[] nextArr = new int[]{7, 3, 1, 0, 2};
        RandomListNode[] randomListNodes = new RandomListNode[nextArr.length + 1];
        randomListNodes[nextArr.length] = null;

        for(int i = nextArr.length - 1; i >=0; i--) {
            randomListNodes[i] = new RandomListNode(nextArr[i]);
            randomListNodes[i].next = randomListNodes[i + 1];
        }

        for (int i = 0; i < nextArr.length; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(randomListNodes.length);
            System.out.println(randomNumber);
            randomListNodes[i].random = randomListNodes[randomNumber];
        }

        instance.copyRandomList2(randomListNodes[0]);
    }

    /**
     * 这题偏向于脑筋急转弯跟数据结构的熟练使用，暴力求解的话应该也可，但是要一直比对random与节点是否相等，记录random下标。时间复杂度较高
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }


    /**
     * 暴力求解
     * @param head
     * @return
     */
    public RandomListNode copyRandomList2(RandomListNode head) {
        RandomListNode cur = head;
        int count = 0;
        while(cur != null) {
            count++;
            cur = cur.next;
        }
        RandomListNode[] nodes = new RandomListNode[count];

        cur = head;
        count = 0;
        while(cur != null) {
            nodes[count++] = cur;
            cur = cur.next;
        }

        cur = head;
        int[] randomPos = new int[count];
        for(int i = 0; i < count; i++) {
            RandomListNode itr = head;
            for(int j = 0; j <= count; j++) {
                if(cur.random == itr) {
                    randomPos[i] = j;
                    break;
                }
                itr = itr.next;
            }
            cur = cur.next;
        }
        cur = head;
        RandomListNode prev = new RandomListNode(0);
        RandomListNode curr = prev;
        RandomListNode[] newNodes = new RandomListNode[count];
        for(int i = 0; i < count; i++) {
            RandomListNode newNode = new RandomListNode(nodes[i].val);
            curr.next = newNode;
            newNodes[i] = newNode;
            curr = curr.next;
        }

        curr = prev;
        for(int i = 0; i < count; i++) {
            curr.next.random = randomPos[i] == count ? null : newNodes[randomPos[i]];
            curr = curr.next;
        }
        return prev.next;
    }
}
