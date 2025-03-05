package Java.summer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _61_70 {
    // 131-155
    // 131
    List<List<String>> partition_res = new ArrayList<>();
    public List<List<String>> partition(String s) {
        partition_helper(s, 0, new ArrayList<>());
        return partition_res;
    }
    public void partition_helper(String s, int index, List<String> tmp) {
        if (index == s.length()) {
            partition_res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (checker(s.substring(index, i + 1))) {
                tmp.add(s.substring(index, i + 1));
                partition_helper(s, i + 1, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
    public boolean checker(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 51
    List<List<String>> solveNQueens_res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        // 回溯。
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == i) sb.append("Q");
                else sb.append(".");
            }
            tmp.add(sb.toString());
            solveNQueens_helper(1, n, tmp);
            tmp.remove(tmp.size() - 1);
        }
        return solveNQueens_res;
    }
    public void solveNQueens_helper(int cur, int n, List<String> tmp) {
        if (n == cur) {
            solveNQueens_res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < n; i++) {
            // 此时坐标为 [cur, i]
            // 检查是否在同一个列。
            boolean skip = false;
            for (int j = 0; j < tmp.size(); j++) {
                if (tmp.get(j).charAt(i) == 'Q') {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            // 检查是否在同一主对角线。
            for (int j = 1; cur - j >= 0 && i - j >= 0; j++) {
                if (tmp.get(cur - j).charAt(i - j) == 'Q') {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            // 检查是否在同一副对角线。
            for (int j = 1; cur - j >= 0 && i + j < n; j++) {
                if (tmp.get(cur - j).charAt(i + j) == 'Q') {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == i) sb.append("Q");
                else sb.append(".");
            }
            tmp.add(sb.toString());
            solveNQueens_helper(cur + 1, n, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 35
    public int searchInsert(int[] nums, int target) {
        // 抽象为二分搜索问题，搜索第一个等于或者大于 target 的位置。
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;
            else if (target < nums[mid]) right = mid;
            // 寻找第一个大的，因此要 right = mid，继续寻找。
            // 注意是 target 不动，mid 动，mid 去寻找 target。
            else left = mid;
        }
        if (nums[left] >= target) return left;
        else if (nums[right] >= target) return right;
        else return right + 1;
    }

    // 74
    public boolean searchMatrix(int[][] matrix, int target) {
        // 化二维为一维。
        int left = 0, right = matrix.length * matrix[0].length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) right = mid;
            else left = mid;
        }
        int row = left / matrix[0].length;
        int col = left % matrix[0].length;
        if (matrix[row][col] == target) return true;
        row = right / matrix[0].length;
        col = right % matrix[0].length;
        return matrix[row][col] == target;
    }

    // 34
    public int[] searchRange(int[] nums, int target) {
        // 二分查找。
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        // 查找第一个位置。
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) right = mid;
            else left = mid;
        }
        if (nums[left] == target) res[0] = left;
        else if (nums[right] == target) res[0] = right;
        // 查找第最后一个位置。

        left = 0;
        right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) right = mid;
            else left = mid;
        }
        if (nums[right] == target) res[1] = right;
        else if (nums[left] == target) res[1] = left;

        return res;
    }

    // 33
    public int search(int[] nums, int target) {
        // 趋势图可能为 / | /，所以额外需要一个变量判断是第一个上涨段还是第二个上涨段。
        int first = nums[0];
        if (target >= first) {
            // target 可能在第一段。
            int left = 0, right = nums.length - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] > target) right = mid;
                else {
                    // nums[mid] < target
                    if (nums[mid] < first) right = mid;
                    else left = mid;
                }
            }
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            return -1;
        } else {
            int left = 0, right = nums.length - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] < target) left = mid;
                else {
                    // nums[mid] > target
                    if (nums[mid] < first) right = mid;
                    else left = mid;
                }
            }
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            return -1;
        }
    }

    // 153
    public int findMin(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) return nums[0];
        // 第一个小于末尾数字的位置。
        int last = nums[nums.length - 1];
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= last) right = mid;
            else if (nums[mid] > last) left = mid;
        }
        return Math.min(nums[left], nums[right]);
    }


    // 4 寻找两个有序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 首先分奇数偶数去讨论。
        int all = nums1.length + nums2.length;
        if (all % 2 == 0) {
            // 如果是偶数。
            int left = helper(nums1, nums2, 0, 0, all / 2);
            int right = helper(nums1, nums2, 0, 0, all / 2 + 1);
            return (left + right) / 2.0;
        }
        // 如果是奇数。
        return helper(nums1, nums2, 0, 0,all / 2 + 1);
    }
    public int helper(int[] nums1, int[] nums2, int p1, int p2, int k) {
        // 该函数的作用是在 nums1 和 nums2 中找**第 k 大的数字**。
        // 为了减少讨论，保证 nums1 的 length 永远小于 nums2 的 length。
        if (nums1.length - p1 > nums2.length - p2) {
            // 注意需要减去 p1、p2。
            return helper(nums2, nums1, p2, p1, k);
        }

        if (p1 >= nums1.length) {
            return nums2[p2 + k - 1];
        }

        if (k == 1) {
            // 找第一大的数字，直接找两者最小就可以了。
            return Math.max(nums1[p1], nums2[p2]);
        }

        int index1 = Math.min(nums1.length, p1 + k / 2) - 1;
        // 找到 nums1 的第 k/2 个元素。
        // 这里不需要管多一个还是少一个，反正取出一半就行。
        int index2 = p2 + k / 2 - 1;
        // 找到 nums2 的第 k/2 个元素（因为是“第”，所以要 - 1）。

        if (nums1[index1] > nums2[index2]) {
            // 把较小的一段丢掉，因此把 index2 对应的丢弃（减掉 k 时需要保证抛弃的数量准确，不能因为精度问题有影响）。
            return helper(nums1, nums2, p1, index2 + 1, k - (index2 - p2 + 1));
        }
        return helper(nums1, nums2, index1 + 1, p2, k - (index1 - p1 + 1));
    }

    // 20-有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (s.charAt(i) == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                }
                if (s.charAt(i) == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                }
                if (s.charAt(i) == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                }
            }
        }
        return stack.isEmpty();
    }


    // 155-最小栈。
    class MinStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public MinStack() {
        }

        public void push(int val) {
            this.stack.push(val);
            if (this.minStack.isEmpty() ||
                    this.minStack.peek() >= val) this.minStack.push(val);
        }

        public void pop() {
            if (this.stack.peek().equals(this.minStack.peek())) this.minStack.pop();
            this.stack.pop();
        }

        public int top() {
            return this.stack.peek();
        }

        public int getMin() {
            return this.minStack.peek();
        }
    }
}
