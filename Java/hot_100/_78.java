package Java.hot_100;

import java.util.ArrayList;
import java.util.List;

public class _78 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        helper(nums, 0, new ArrayList<>());
        return res;
    }

    public void helper(int[] nums, int index, List<Integer> list) {
        // 选取 or 不选取当前元素。
        // 从子集的定义入手。
        if (index == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        helper(nums, index + 1, list);
        list.remove(list.size() - 1);
        helper(nums, index + 1, list);
    }


    public List<List<Integer>> subsets_2(int[] nums) {
        helper_2(nums, new ArrayList<>(), 0);
        return res;
    }

    public void helper_2(int[] nums, List<Integer> list, int position) {
        res.add(new ArrayList<>(list));
        // 每个结果都要加入到 res 中。

        for (int i = position; i < nums.length; i++) {
            // 不回头式搜索，防止重复。
            list.add(nums[i]);
            // 这一步保证选了当前元素。
            helper_2(nums, list, i + 1);
            list.remove(list.size() - 1);
            // 这一步已经保证不选当前元素。
        }
    }
}
