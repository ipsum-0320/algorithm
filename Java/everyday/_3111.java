package Java.everyday;

import java.util.*;

public class _3111 {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            set.add(points[i][0]);
        }
        List<Integer> x = new ArrayList<>(set);
        x.sort((o1, o2) -> {
            return o1 - o2;
        });
        // o1 -> o2，就是从小到大。
        int res = 0, i = 0;
        while (i < x.size()) {
            int right = x.get(i) + w;
            res++;
            while (i < x.size() && x.get(i) <= right) {
                i++;
            }
        }
        return res;
    }
}
