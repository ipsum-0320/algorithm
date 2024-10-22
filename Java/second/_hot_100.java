package Java.second;

import java.util.*;
import java.util.stream.Collectors;

public class _hot_100 {
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
}
