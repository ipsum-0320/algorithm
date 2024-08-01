package Java.hot_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15 {

    // 排序 -> 联想到双指针...
    public List<List<Integer>> threeSum(int[] nums) {
        // 三数之和可以转化为两数之和。
        // 进行升序排序，如果要降序排序，使用 Collections.reverseOrder()。
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 防止重复。
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 寻找两数之和等于 nums[i]。
            // 然后使用双指针去做。
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int leftV = nums[left];
                int rightV = nums[right];
                if (leftV + rightV == -nums[i]) {
                    // 学习初始化的方式。
                    res.add(new ArrayList<>(Arrays.asList(nums[left], nums[right], nums[i])));
                    while (left < right && nums[right] == rightV) {
                        right--;
                    }
                    while (left < right && nums[left] == leftV) {
                        left++;
                    }
                } else if (leftV + rightV > -nums[i]) {
                    while (left < right && nums[right] == rightV) {
                        right--;
                    }
                } else {
                    while (left < right && nums[left] == leftV) {
                        left++;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum_2(int[] nums) {
        // [1,2,-2,-1]
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 防止重复。
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1, k = nums.length - 1; j < k; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                // 有点二分查找的感觉。
                while (j < k && nums[j] + nums[k] > -nums[i]) {
                    k--;
                }
                if (j != k && nums[j] + nums[k] == -nums[i]) {
                    res.add(new ArrayList<>(Arrays.asList(nums[j], nums[k], nums[i])));
                }
            }
        }
        return res;
    }
}
