package Java.hot_100;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _347 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 统计每一个 nums[i] 出现的次数。
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            // 按照出现次数建立大顶堆。
            return map.get(o2) - map.get(o1);
        });

        int[] res = new int[k];
        for (int key : map.keySet()) {
            queue.offer(key);
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

}
