package Java.hot_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class _39 {
    List<List<Integer>> res = new ArrayList<>();
    int target = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.target = target;
        Arrays.sort(candidates);
        // 数组排序。
        helper(candidates, 0, new ArrayList<>(), 0);
        return res;
    }

    public void helper(int[] candidates, int cur, List<Integer> list, int index) {
        if (cur == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 从 index 开始，防止回头式搜素导致重复。
            if (cur + candidates[i] > target) {
                // 剪枝，因为数组是有序的，所以后面的数都会大于 target - cur。
                break;
            }
            list.add(candidates[i]);
            helper(candidates, cur + candidates[i], list, i);
            // 可以无限重复的拿取，所以传入的是 i。
            list.remove(list.size() - 1);
        }
    }
}
