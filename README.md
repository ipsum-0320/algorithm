# Algorithm

This is my ropo for algorithm Solutions.

# Hot100 分类

## 1.哈希

1. 两数之和（Map 存储；排序）。
2. 字母异位词分组（排序比较；字母计数）。
3. 最长连续序列（利用哈希表，从最小的开始算）。

## 2.双指针

1. 移动零（双指针，快指针找非 0，慢指针挪位置）。
2. 盛水最多的容器（左右指针夹逼 + 贪心，比我高的我才计算）。
3. 三数之和（排序，不回头式查找，左右指针夹逼，有点二分查找的感觉）。

## 3.滑动窗口

1. 无重复字符的最长子串（滑动窗口，从字符集，也就是哈希集合的角度考虑问题，记录每个字符是否出现过，只要发现 set 里面有重复，那么就一直让左指针去移动）。
2. 找到字符串中所有字母异位词分组（滑动窗口，记录每个字母出现的次数，然后使用 `Arrays.equals` 进行比较）。

## 4.子串/子数组

1. 和为 K 的子数组（前缀和，哈希表记录前缀和出现的次数，注意保存 0）。

## 5.普通数组

1. 最大子数组和（前缀和，找最小的前缀和，注意区分**当前**的前缀和与**过往**前缀和的最小值；动态规划，）。

> 前缀和的关键词的是**连续**。

2. 合并区间（按照左端点排序，记着考虑 `[[1, 4], [2, 3]]` 的情况）。
2. 轮转数组（一维数组轮转；O(1) 空间复杂度的方法使用三次 reverse 即可解决）。
2. 除自身以外数组的乘积（前缀积，后缀积，左右开弓；O(1) 空间复杂度的方法可以利用 res 数组，后缀积需要单独定义一个变量 R）。

## 6.矩阵

1. 矩阵置零（用两个标记数组分别记录每一行和每一列是否有零出现；O(1) 的解法是使用输入 **matrix** 的第一行与第一列记录是否有零出现，然后额外使用两个标记变量分别记录第一行和第一列是否原本包含 0）。
2. 螺旋矩阵（定义变量用来记录遍历了多少，定义变量用来记录是否遍历过，然后定义四个方向 `[row, col]` 进行循环；O(1) 的方法是使用四个变量来记录边界）。
3. 旋转图像（可以找到规律为：对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在第 j 行倒数第 i 列的位置，要注意赋值顺序，`[i][j]` 为逆时针，`[i][j]` 在后为顺时针；第二种方法是先上下翻转，然后主对角线翻转）。
4. 搜索二维矩阵 II（左下角或者右上角起手，根据大小定方向）。

## 7.链表

1. 相交链表（A + B = B + A，重点是两个链表要互相**换链**）。

   > 核心在于 A 链表 + B 链表与 B 链表 + A 链表必然是相同的长度，所以一定会同时遍历到结尾。
   >
   > 这样的两个叠加链表同时遍历到有相同节点的时候，一定一边是 A 链表一边是 B 链表（因为题目保证没有环）。而相交节点开始到结尾的节点都相同，所以第一个相同的节点就是 A 链表和 B 链表的交点。

2. 反转链表（迭代解法，**存前取后**；递归解法，遍历到最后一个节点再进行处理，需要有一个节点存储最新的头节点，需要一个 helper 函数）。

3. 回文链表（存储到数组里面然后左右指针对比；快慢指针找到中点，反转后半段链表，然后做双指针顺序比对）。

4. 环形链表（快慢指针；单指针 + 哈希表）。

5. 环形链表 II（快慢指针，快指针走两步，慢指针走一步，相遇后，快指针回到起点，两个指针都走一步，再次相遇的地方就是环的入口；单指针 + 哈希表）。

6. 合并两个有序链表（双指针有序合并，学会使用 DummyNode + curNode）。

7. 两数相加（模拟加法，注意进位；空间复杂度为 O(1) 的解法是使用原链表）。

8. 删除链表的倒数第 N 个结点（双指针，引入 dummy 节点，方便删除头结点）。

9. 两两交换链表中的节点（递归，理解交互链表中的两个节点是什么意思；迭代可使用 dummy 节点，虽然要交换两个节点，但是需要三个 ListNode 变量）。

10. 随机链表的复制（哈希表，使用 hashmap 建立原始节点和新节点的映射，先 next，再 random；节点拆分，我们首先将该链表中每一个节点拆分为两个相连的节点，例如对于链表 A -> B -> C，我们可以将其拆分为 A -> A' -> B -> B' -> C -> C'。对于任意一个原节点 S，其拷贝节点 S' 即为其后继节点）。

11. 排序链表（归并排序，递归，需要合并两个有序链表子方法；迭代也可以完成，主要依靠 subLength，此处不使用该方法）。

12. **LRU 缓存**（Map + 双向链表，注意事项：capacity、map、dummy、tail，注意 next 是否为 null 的情况，从而更新 tail）。

## 8.二叉树

1. 二叉树的中序遍历（递归；迭代，显示维护一个栈，双 while 循环 -> 中序遍历始终先跑完左节点）。

2. 二叉树的最大深度（递归，左右子树的最大深度 + 1）。

3. 翻转二叉树（递归，交换两棵子树的位置，即完成以 root 为根节点的整棵子树的翻转）。

4. 对称二叉树（递归，模拟出两棵树来，然后两棵树的左右子树互相相等；迭代，引入队列，两两比较是否相等）。

5. 二叉树的直径（递归，计算左右子树的深度，然后求最大值）。

6. 二叉树的层序遍历（迭代，队列秒了）。

7. 将有序数组转化为二叉搜索树（递归，取中点构建平衡的二叉搜索树）。

8. 验证二叉搜索树（利用二叉搜索树的特性 => 递归，定义上下限，上下限均为 `node.val`；或者递归，使用 Map 存储子树的最大值 or 最小值；迭代，二叉搜索树的中序遍历是递增数组，因此可以中序遍历获取来判断，注意 `root != null` 的判断）。

9. 二叉搜索树中第 K 小的元素（迭代，中序遍历；递归，使用 Map 来统计以 node 为根结点的子树的结点数，然后使用二分法寻找第 K 小的元素，记得更新 right 时，对 K 进行同步更新）。

10. 二叉树的右视图（迭代，层序遍历）。

11. 二叉树展开为链表（递归，如果左子树不为空，将左子树插入到右子树的前面）。

12. 从前序和中序遍历中构造二叉树（递归，对于任意一颗树而言，前序遍历的形式总是 `[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]`，中序遍历总是 `[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]`）。

    ![image-20240820111622654](./README.assets/image-20240820111622654.png)

13. 路径总和 III（递归，前缀和，前缀和由两部分算法组成，**前序和与现节点和**）。

14. 二叉树的最近公共祖先（递归，存储父节点，可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先）。

## 9.图论

1. 岛屿数量（递归，深度优先搜索，最终的岛屿数量就是进行深度优先搜索的次数；递归 + 队列，广度优先搜索，使用 index 存储当前岛屿的位置，化二维为一维，最终的就是进行广度优先搜索的次数）。
2. 腐烂的橘子（多源 BFS，多源 BFS 只需要把所有的 BFS 入队即可，BFS 的次数就是最小分钟数，注意最后一轮没有新鲜橘子腐烂）。
3. 课程表（拓扑排序，从入度的角度考虑问题，本质是一个 BFS）。
4. 实现 Trie（前缀树，思路其实很明确，指向子节点的指针数组 **children**，布尔字段 **isEnd**，表示该节点是否为字符串的结尾，最终的实现可以使用递归，也可以使用迭代，做法都是逐个逐个取字符）。

## 10.回溯

> 回溯问题常见的一个场景是防止重复，以下总结了常见的防止重复的方法：
>
> * 最终的答案里交换顺序属于同一种情况——**不回头式**搜索（或者说叫做顺序搜索，又分为自身可重复和自身不可重复两大类）。
> * 最终的答案里元素的个数相同属于同一种情况——**排序**聚集相同元素。
> * 元素只能被使用一次——使用**额外空间辅助记录**（数组、map 等）。
>
> 回溯算法就是个多叉树的遍历问题，关键就是在前序遍历和后序遍历的位置做一些操作，算法框架如下：
>
> ```python
> def backtrack(...):
>     for 选择 in 选择列表:
>         做选择
>         backtrack(...)
>         撤销选择
> ```
>
> 回溯的终止条件也很重要！

1. 全排列（递归，回溯模板解法，注意需要使用 Map 记录防止重复）。
1. 子集（递归，回溯模板解法，每一个结果都要保存；递归，选取 or 不选取，终止模板是 cur == n）。
1. 电话号码的字母组合（递归，回溯模板解法，不要在循环内使用 String += String，直接使用 StringBuilder 就可）。
1. 组合总和（递归，先排序做好剪枝，从 index 开始，防止回头式搜素导致组合重复，由于可以元素重复，递归时 index 传入 i）。
1. 括号生成（递归，选取左括号 or 选取右括号，做好剪枝 left <= right，终止条件是左右括号数均为 0）。
1. 单词搜索（递归，本质上仍然回溯模板解法【注意，BFS 本身做不到回溯】，注意终止条件）。
1. 分割回文串（）。













# Max300 分类









# 附录

## 1.动态规划专题

### 1.1.什么情况下使用动态规划

一般情况下，使用动态规划的三个场景：

1. 求最大值或者最小值。
2. 判断是否可行。
3. 统计方案个数。

一般情况下，不使用动态规划的两个场景：

1. 求出所有具体的方案而非方案的个数。
2. 输入数据是一个集合而不是序列。

> 什么是序列？一种数据结构，数据按顺序排列，序列中数据的顺序是十分重要的。
>
> 什么是集合？一种数据结构，数据之间没有什么关系，仅仅是聚合在一起。

### 1.2.动态规划的思路

动态规划其实就是**带备忘录的递归**。

做动态规划，要学会定义**子问题**，子问题要保证无后效性。什么是动态规划的无后效性？为了保证计算子问题能够按照顺序、不重复地进行，动态规划要求已经求解的子问题不受后续阶段的影响。这个条件也被叫做「无后效性」。

因此，考虑动态规划的转移方程时，需要明白：

* 首先一定要把前面的计算结果利用起来（分解子问题）。
* 然后**考虑一个新的元素加进来之后**，如何结合前驱元素和结果计算新的结果（其实换句话说，就是从**最后一步**入手开始考虑）



