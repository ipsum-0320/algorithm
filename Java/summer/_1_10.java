package Java.summer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _1_10 {
    // 1-560
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
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] characters = str.toCharArray();
            Arrays.sort(characters);
            // Arrays 本质上是对数组进行排序的。
            String sortedStr = new String(characters);
            // 由 characters 转 String 需要用到 new String。
            if (map.containsKey(sortedStr)) {
                map.get(sortedStr).add(str);
            } else {
                map.put(sortedStr, Stream.of(str).collect(Collectors.toList()));
                // Arrays.asList 和 List.of 生成的 list 都是不可变的 list。
            }
        }
        return map.values().stream().collect(Collectors.toList());
    }

    public List<List<String>> groupAnagrams_2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            StringBuilder sb = new StringBuilder();
            char[] chars = str.toCharArray();
            int[] charNum = new int[26];
            for (int i = 0; i < chars.length; i++) {
                charNum[chars[i] - 'a']++;
            }
            for (int i = 0; i < charNum.length; i++) {
                sb.append('a' + i).append(charNum[i]);
                // 'a' + i === 'b'。
            }
            String sbKey = sb.toString();
            if (map.containsKey(sbKey)) {
                map.get(sbKey).add(str);
            } else {
                map.put(sbKey, Stream.of(str).collect(Collectors.toList()));
                // Arrays.asList 和 List.of 生成的 list 都是不可变的 list。
            }
        }
        return map.values().stream().collect(Collectors.toList());
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i : nums)
            set.add(i);
        for (int num : set) {
            if (set.contains(num - 1)) continue;
            int tmp = 1, cur = num;
            while (set.contains(cur + 1)) {
                tmp++;
                cur++;
            }
            res = Math.max(tmp, res);
        }
        return res;
    }

    public void moveZeroes(int[] nums) {
        // 单指针，非零压缩前移，最后补零。
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }

    public int maxArea(int[] height) {
        // 双指针完成。
        int left = 0, right = height.length - 1;
        int res = (right - left) * Math.min(height[left], height[right]);
        while (left < right) {
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                while (j < k && nums[j] + nums[k] > target) k--;
                if (j != k && nums[j] + nums[k] == target) {
                    // 防止 j、k 重复。
                    res.add(List.of(nums[i], nums[j], nums[k]));
                }
            }
        }
        return res;
    }

    public int trap(int[] height) {
        // 计算洼地的水。
        // O(1) 的做法就是每次总能确定左右两边其中一个桶能容纳多少水。
        int[] leftHeight = new int[height.length];
        int[] rightHeight = new int[height.length];

        leftHeight[0] = 0;
        int leftMax = height[0];
        for (int i = 1; i < leftHeight.length; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            leftHeight[i] = leftMax;
        }

        rightHeight[height.length - 1] = 0;
        int rightMax = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax = Math.max(rightMax, height[i + 1]);
            rightHeight[i] = rightMax;
        }

        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int minHeight = Math.min(leftHeight[i], rightHeight[i]);
            if (height[i] < minHeight) {
                res += minHeight - height[i];
            }
        }

        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口，从字符集合的角度去考虑问题。
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j < i && set.contains(s.charAt(i))) {
                // 含有 s.charAt(i) 则左边界前进。
                set.remove(s.charAt(j));
                j++;
            }
            set.add(s.charAt(i));
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    public List<Integer> findAnagrams(String s, String p) {
        // 记录每个字母出现的次数，然后使用 `Arrays.equals` 进行比较。
        int[] target = new int[26];
        for (int i = 0; i < p.length(); i++) {
            target[p.charAt(i) - 'a']++;
        }

        int subSize = p.length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length() - subSize; i++) {
            if (p.indexOf(s.charAt(i)) == -1) continue;
            int[] tmp = new int[26];
            for (int j = 0; j < p.length(); j++) {
                tmp[s.charAt(i + j) - 'a']++;
            }
            if (Arrays.equals(tmp, target)) {
                res.add(i);
            }
        }
        return res;
    }

    public int subarraySum(int[] nums, int k) {
        // 前缀和，哈希表记录前缀和出现的次数。
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += nums[i];
            res += map.getOrDefault(sum - k, 0);
        }
        return res;
    }
}
