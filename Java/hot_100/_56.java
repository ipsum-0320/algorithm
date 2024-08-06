package Java.hot_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _56 {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            // 二维排序。
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        // 按照左端点进行排序。
        List<int[]> list = new ArrayList<>();
        // List 的泛型可以是数组。
        int left = intervals[0][0], right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
                // 防止 [[1, 4], [2, 3]] 这种情况。
            } else {
                list.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        list.add(new int[]{left, right});
        return list.toArray(new int[list.size()][]);
        // List 转数组。
    }
}
