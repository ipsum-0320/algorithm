package Java.summer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class _11_20 {
    // 239-48
    // 239
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 滑动窗口 + 最大堆，注意堆要同时存储值和下标。
        // 存储下标是为了最大值的下标为 [i, i + k] 之间。
        PriorityQueue<Integer[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 最大堆。
        for (int i = 0; i < k; i++) {
            heap.add(new Integer[] {nums[i], i});
        }

        int[] res = new int[nums.length - k + 1];
        for (int i = k; i < nums.length; i++) {
            res[i - k] = heap.peek()[0];

            heap.add(new Integer[] {nums[i], i});
            // 把最后一个元素加进去，记得补偿。
            while (heap.peek()[1] < i - k + 1) {
                heap.poll();
            }
        }
        res[nums.length - k] = heap.peek()[0];
        // 补偿。

        return res;
    }

    // 76
    public String minWindow(String s, String t) {
        // 本质是双指针，有两个关键的变量：match 和 targetMap，主要的逻辑包括了 right 的 for 循环和 left 的 while 循环。
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int match = 0;
        String res = "";
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (!map.containsKey(s.charAt(right))) {
                continue;
            }
            map.put(s.charAt(right), map.get(s.charAt(right)) - 1);
            if (map.get(s.charAt(right)) == 0) {
                match++;
                if (match == map.keySet().size()) {
                    // 窗口已经包含了所有的字符。
                    if (res.isEmpty()) {
                        res = s.substring(left, right + 1);
                    }

                    while (left <= right && match == map.keySet().size()) {
                        // 开始挪动左指针，left == i 表示最右端的字符也可以被丢弃。
                        if (map.containsKey(s.charAt(left))) {
                            map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                            if (map.get(s.charAt(left)) > 0) {
                                // 不能是 != 0，因为 map.get(s.charAt(left)) 可能是负数。
                                match--;
                            }
                        }
                        left++;
                    }
                    res = res.length() < right - left + 2 ? res : s.substring(left - 1, right + 1);
                }
            }
        }
        return res;
    }

    // 53
    public int maxSubArray(int[] nums) {
        // 动态规划。
        int[] dp = new int[nums.length];
        // dp[i] 表示以 nums[i] 结尾的最大子数组和。
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int item : dp) {
            res = Math.max(res, item);
        }
        return res;
    }

    public int maxSubArray_2(int[] nums) {
        // 连续子数组，用前缀和。
        // 最大和需要用到最小前缀和与当前的前缀和。
        int min = 0;
        int sum = nums[0];
        int res = sum;
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, sum);
            // min 是过往，因此需要先更新 min。
            sum += nums[i];
            res = Math.max(sum - min, res);
        }
        return res;
    }

    // 56
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<Integer[]> list = new ArrayList<>();

        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
                continue;
            }
            list.add(new Integer[] {left, right});
            left = intervals[i][0];
            right = intervals[i][1];
        }
        list.add(new Integer[] {left, right});
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = new int[] {list.get(i)[0], list.get(i)[1]};
        }
        return res;
    }

    // 189
    public void rotate(int[] nums, int k) {
        // 轮转数组，直接三次翻转。
        k = k % nums.length; // 记得取余数。
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int i, int j) {
        int left = i, right = j;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    // 238
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        int[] answer = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            answer[i] = left[i] * right[i];
        }
        return answer;
    }

    // 41
    public int firstMissingPositive(int[] nums) {
        // int n = nums.length;
        // 可以肯定是 answer 一定位于 [1, n + 1]
        // 核心目标就是将 nums[i] 放在数组的第 nums[i] - 1 位置上。
        for (int i = 0; i < nums.length; i++) {
            // 有一些位置不合适，直接忽略。
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                // 要保证换过来的 nums[i] 也要去正确的位置，
                // 所以需要 while，for 过去就不看这个 nums[i] 了
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public void swap(int[] nums, int i, int j) {
        // 交换 nums[i] 和 nums[j]。
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 73
    public void setZeroes(int[][] matrix) {
        boolean rowZero = false, colZero = false;
        // 判断首行，首列。
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                rowZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                colZero = true;
                break;
            }
        }
        // 判断子区块。
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 开始赋 0
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if (rowZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (colZero) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    // 54
    public List<Integer> spiralOrder(int[][] matrix) {
        // 模拟，定义好边界即可。
        int top = 0, right = matrix[0].length, bottom = matrix.length, left = 0;
        int n = matrix.length * matrix[0].length;
        // n 表示被访问过的个数。
        int[][] dir = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        List<Integer> res = new ArrayList<>();
        int change = 0, i = 0, j = 0;
        while (n > 0) {
            res.add(matrix[i][j]);
            n--;
            if (i + dir[change % 4][0] < top) {
                change++;
                left++;
            } else if (i + dir[change % 4][0] >= bottom) {
                change++;
                right--;
            } else if (j + dir[change % 4][1] < left) {
                change++;
                bottom--;
            } else if (j + dir[change % 4][1] >= right) {
                change++;
                top++;
            }
            i = i + dir[change % 4][0];
            j = j + dir[change % 4][1];
        }
        return res;
    }

    // 48
    public void rotate(int[][] matrix) {
        // 直接上技巧，先上下折叠，在主对角线对折。
        int halfRow = matrix.length / 2;
        for (int i = 0; i < halfRow; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = tmp;
            }
        }

        // 主对角线对折。
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
