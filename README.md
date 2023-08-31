# 力扣笔记

## 1.常用数据结构

### 1.1 栈
* 包含min函数的栈-M
> [力扣155](https://leetcode.cn/problems/min-stack/)  
> 解法：辅助栈  
> [代码跳转](./src/com/europa/leetcode/Code155.java)

### 1.2 队列
* 包含max函数的队列
> 解法：辅助队列
> [代码跳转](./src/com/europa/leetcode/Code154.java)
  
* 用两个栈实现队列
> 解法：利用栈特性
> [代码跳转](./src/com/europa/leetcode/Code154_1.java)


### 1.3 链表

* 反转链表-E
> [力扣206](https://leetcode.cn/problems/reverse-linked-list/)  
> 常用解法：栈、递归、循环  
> 如链表 1->3->2->5->7->8->11  
> [代码跳转](./src/com/europa/leetcode/Code206.java)

* 从尾到头打印链表
> 常用解法：递归回溯、辅助栈  
> 如链表 1->3->2->5->7->8->11  
> [代码跳转](./src/com/europa/leetcode/Code206_1.java)

* 复杂链表的复制-M  
> [力扣138](https://leetcode.cn/problems/copy-list-with-random-pointer/)  
> 解法：哈希表  
> 使用Hash表存新旧节点，然后根据纠结点调整新节点的指针
> [代码跳转](./src/com/europa/leetcode/Code138.java)

### 1.4 树


## 2.自动机

## 3.滑动窗口
* 滑动窗口的最大值-H  
> [力扣59](https://leetcode.cn/leetbook/read/illustration-of-algorithm/58o46i/)  
> 解法：暴力循环、辅助优先队列  
> nums = [1,3,-1,-3,5,3,6,7], 和 k = 3  输出: [3,3,5,5,6,7]  
> [代码跳转](./src/com/europa/leetcode/Code59.java)

## 4.动态规划

## 5.热题