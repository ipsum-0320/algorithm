package Java.hot_100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _46 {
    List<List<Integer>> res = new ArrayList<>();
    Set<Integer> set = new HashSet<>();

    public List<List<Integer>> permute(int[] nums) {
        // 回溯法。
        helper(nums, new ArrayList<>());
        return res;
    }

    public void helper(int[] nums, List<Integer> list) {
        // list 用来存储当前的排列。
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            // 注意这里要新建一个 list，否则会导致 res 中的 list 都是同一个引用。
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 选择列表。
            if (set.contains(nums[i])) {
                continue;
            }
            // 做选择。
            list.add(nums[i]);
            set.add(nums[i]);
            // 递归。
            helper(nums, list);
            // 撤销选择。
            list.remove(list.size() - 1);
            set.remove(nums[i]);
        }
    }
}
