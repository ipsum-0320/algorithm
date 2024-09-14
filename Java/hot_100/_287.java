package Java.hot_100;

public class _287 {

    public int findDuplicate(int[] nums) {
        // 建模成环形链表方法，注意题目中的两个条件：
        // 1. nums 数组长度为 n + 1。
        // 2. 其数字都在 [1, n] 范围内（包括 1 和 n），所以 nums[i] 不会越界。
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 相遇之后，将快指针回到起点，一次走一步。
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }

}
