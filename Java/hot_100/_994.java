package Java.hot_100;

import java.util.LinkedList;
import java.util.Queue;

public class _994 {

    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 统计新鲜橘子的数量。
                if (grid[i][j] == 1) {
                    fresh++;
                } else if (grid[i][j] == 2) {
                    // 将所有腐烂的橘子加入队列。
                    queue.offer(i * grid[0].length + j);
                }
            }
        }
        int min = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                int row = index / grid[0].length;
                int col = index % grid[0].length;
                if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                    grid[row - 1][col] = 2;
                    fresh--;
                    queue.offer((row - 1) * grid[0].length + col);
                }
                if (row + 1 < grid.length && grid[row + 1][col] == 1) {
                    grid[row + 1][col] = 2;
                    fresh--;
                    queue.offer((row + 1) * grid[0].length + col);
                }
                if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                    grid[row][col - 1] = 2;
                    fresh--;
                    queue.offer(row * grid[0].length + col - 1);
                }
                if (col + 1 < grid[0].length && grid[row][col + 1] == 1) {
                    grid[row][col + 1] = 2;
                    fresh--;
                    queue.offer(row * grid[0].length + col + 1);
                }
            }
            min++;
        }
        // 最后一轮没有新鲜橘子被腐烂。
        if (min != 0) min--;
        if (fresh == 0) {
            return min;
        }
        return -1;
    }

}
