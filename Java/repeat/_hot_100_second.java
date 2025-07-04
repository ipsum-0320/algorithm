package Java.repeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class _hot_100_second {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        // 通过计数的方法解决。
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
                // 字符 - 字符 = 数字，这样就可以得到字符的位置。
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    sb.append((char) ('a' + i));
                    // 字符 + 数字转 chat。
                    sb.append(count[i]);
                }
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return map.values().stream().collect(Collectors.toList());
        // stream API 的使用。
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i] - 1)) continue;
            int num = nums[i];
            while (set.contains(num)) {
                num++;
            }
            res = Math.max(res, num - nums[i]);
        }
        return res;
    }

    public void moveZeroes(int[] nums) {
        int p = 0;
        while (nums[p] != 0) {
            p++;
            if (p == nums.length) return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > p) {
                    nums[p] = nums[i];
                    p++;
                    if (p == nums.length) break;
                }
            }
        }

        while (p < nums.length) {
            nums[p] = 0;
            p++;
        }
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                int k = nums.length - 1;
                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }
                if (j == k) break;
                if (nums[j] + nums[k] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                }
            }
        }
        return res;
    }

    public int trap(int[] height) {
        int maxLeftHeight = height[0], maxRightHeight = height[height.length - 1];
        int res = 0;
        int left  = 1, right = height.length - 2;

        while (left <= right) {
            // left == right 的情况也需要考虑，不然会遗漏一个格子。
            maxLeftHeight = Math.max(maxLeftHeight, height[left]);
            maxRightHeight = Math.max(maxRightHeight, height[right]);

            if (maxLeftHeight < maxRightHeight) {
                res += Math.max(maxLeftHeight - height[left], 0);
                left++;
            } else {
                res += Math.max(maxRightHeight - height[right], 0);
                right--;
            }
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int res = 0;

        for (right = 0; right < s.length(); right++) {
            Character c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                res = Math.max(res, set.size());
            } else {
                while (set.contains(c)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(c);
            }
        }
        return res;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;

        int[] need = new int[26];
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }
        int[] window = new int[26];
        for (int i = 0; i < p.length() - 1; i++) {
            window[s.charAt(i) - 'a']++;
        }
        // 模拟滑动窗口的过程，需要深刻理解。
        for (int i = 0, j = p.length() - 1; j < s.length(); i++, j++) {
            int start = s.charAt(i) - 'a';
            int end = s.charAt(j) - 'a';

            window[end]++;
            if (Arrays.equals(need, window)) {
                res.add(i);
            }
            window[start]--;
        }

        return res;
    }

    public int subarraySum(int[] nums, int k) {
        // 使用前缀和去完成。
        Map<Integer, Integer> map = new HashMap<>();
        int preSum = 0, res = 0;
        map.put(preSum, 1);
        for (int i = 0 ; i < nums.length; i++) {
            preSum += nums[i];
            res += map.getOrDefault(preSum - k, 0);
            /* 先计算 res，再更新 map。
             * 这么做为了保证子数组的定义：子数组是数组中元素的连续**非空**序列。
             * 如果先更新 map，再计算 res，就会出现子数组为空，比如 k == 0 时。
             * */
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 使用最大堆来完成。
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> b.get(0) - a.get(0));
        for (int i = 0; i < k; i++) {
            queue.add(Arrays.asList(nums[i], i));
        }
        int[] res = new int[nums.length - k + 1];
        int left = 0;
        for (int i = k; i < nums.length; i++, left++) {
            res[i - k] = queue.peek().get(0);
            while (!queue.isEmpty() && queue.peek().get(1) <= left) {
                // k 可能为 1，因此这里需要判断 queue 是否为空。
                queue.poll();
            }
            queue.add(Arrays.asList(nums[i], i));
        }
        res[nums.length - k] = queue.peek().get(0);
        // 最后一个元素的处理，res 的最后一个元素是 res[nums.length - k]，
        // 但是循环中的 i 最大取到 nums.length - 1。
        return res;
    }

    public String minWindow(String s, String t) {
        String res = "";

        // 使用 Map，而不是数组来存储，因为字符串大小写不确定。

        // Map + matches
        Map<Character, Integer> target = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            target.put(t.charAt(i), target.getOrDefault(t.charAt(i), 0) + 1);
        }

        int matches = 0;

        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (target.containsKey(character)) {
                target.put(character, target.get(character) - 1);
                if (target.get(character) == 0) {
                    matches++;
                }
                if (matches == target.keySet().size()) {
                    while (left <= i) {
                        // 注意这里的等号。
                        if (res.isEmpty()) res = s.substring(left, i + 1);
                        else if (i - left + 1 < res.length()) {
                            res = s.substring(left, i + 1);
                        }
                        Character otherCharacter = s.charAt(left);
                        if (!target.containsKey(s.charAt(left))) {
                            left++;
                            continue;
                        }
                        target.put(otherCharacter, target.get(otherCharacter) + 1);
                        if (target.get(otherCharacter) > 0) {
                            matches--;
                            left++;
                            break;
                        }
                        left++;
                    }
                }
            }
        }
        return res;
    }

    public int maxSubArray(int[] nums) {
        // 使用前缀和/动态规划【以最后一步做就行】来完成。
        int preSum = nums[0], preSumMin = Math.min(0, nums[0]), res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum += nums[i];
            res = Math.max(res, preSum - preSumMin);
            preSumMin = Math.min(preSumMin, preSum);
        }

        return res;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 按照左端点进行排序。

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            while (i + 1 < intervals.length && intervals[i + 1][0] <= right) {
                right = Math.max(right, intervals[i + 1][1]);
                i++;
            }
            res.add(Arrays.asList(left, right));
        }

        int n = res.size();
        int[][] finalRes = new int[n][2];
        for (int i = 0; i < n; i++) {
            finalRes[i][0] = res.get(i).get(0);
            finalRes[i][1] = res.get(i).get(1);
        }
        return finalRes;
    }

    public void rotate(int[] nums, int k) {
        // 找规律，使用数组反转的方法。
        int n = nums.length;
        k = k % n;
        rotate_reverse(nums, n - k, n - 1);
        rotate_reverse(nums, 0, n - k - 1);
        rotate_reverse(nums, 0, n);
    }

    public void rotate_reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public int[] productExceptSelf(int[] nums) {
        // 前缀乘积。
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        nums[0] = right[0];
        nums[nums.length - 1] = left[nums.length - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            nums[i] = left[i] * right[i];
        }

        return nums;
    }

    public int firstMissingPositive(int[] nums) {
        // 一个事实是，缺失的最小的正整数一定在 [1, n] 之间。
        int n = nums.length;

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // if -> while，保证交换过来的数也放在正确位置。
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public void setZeroes(int[][] matrix) {
        // 直接使用 O(1) 的时间复杂度
        int isColZero = 1;
        int isRowZero = 1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                isColZero = 0;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                isRowZero = 0;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (isColZero == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (isRowZero == 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int[][] dirs = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        // 自己记录边界。
        int allStep = matrix.length * matrix[0].length, curStep = 0;
        // 自己记录步数。
        int move = 0, i = 0, j = 0;
        // 记录移动方向。
        while (curStep < allStep) {
            res.add(matrix[i][j]);
            int newI = i + dirs[move][0], newJ = j + dirs[move][1];
            if (newI < top) {
                move = (move + 1) % 4;
                left++;
            } else if (newI > bottom) {
                move = (move + 1) % 4;
                right--;
            } else if (newJ < left) {
                move = (move + 1) % 4;
                bottom--;
            } else if (newJ > right) {
                move = (move + 1) % 4;
                top++;
            }
            curStep++;
            i += dirs[move][0];
            j += dirs[move][1];
        }
        return res;
    }

    public void rotate(int[][] matrix) {
        // 先上下翻转，然后沿对角线翻转。
        int mid = matrix.length / 2;
        int n = matrix.length;
        // 上下翻转。
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 对角线翻转。
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                row--;
            }
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode random;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA, curB = headB;
        while (curB != curA) {
            if (curA == null) curA = headB;
                // 一次只能走一步，因此要有 else。
            else curA = curA.next;
            if (curB == null) curB = headA;
            else curB = curB.next;
        }
        return curB;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = new ListNode(-1), cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        if (head == null) return null;
        else head.next = null;
        return pre;
    }

}



