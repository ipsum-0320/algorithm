package Java.hot_100;

import java.util.*;

public class _207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 拓扑排序。
        Map<Integer, Integer> map = new HashMap<>();
        // map 用来统计每个节点的入度。
        Map<Integer, List<Integer>> inOutList = new HashMap<>();
        // inOutList 用来存储每个节点的出度。

        for (int i = 0; i < numCourses; i++) {
            map.put(i, 0);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            map.put(prerequisites[i][1], map.get(prerequisites[i][1]) + 1);
            if (inOutList.containsKey(prerequisites[i][0])) {
                inOutList.get(prerequisites[i][0]).add(prerequisites[i][1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][1]);
                inOutList.put(prerequisites[i][0], list);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (map.get(i) == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                numCourses--;
                if (inOutList.containsKey(course)) {
                    for (int out : inOutList.get(course)) {
                        map.put(out, map.get(out) - 1);
                        if (map.get(out) == 0) {
                            queue.offer(out);
                        }
                    }
                }
            }
        }
        return numCourses == 0;
    }
}
