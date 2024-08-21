package Java.hot_100;

import java.util.LinkedList;
import java.util.Queue;

public class _200 {

    public int numIslands(char[][] grid) {
        // DFS，遍历所有的岛屿。
        int rowL = grid.length;
        int colL = grid[0].length;

        int res = 0;

        for (int i = 0; i < rowL; i++) {
            for (int j = 0; j < colL; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        int rowL = grid.length;
        int colL = grid[0].length;

        if (i < 0 || i >= rowL || j < 0 || j >= colL || grid[i][j] == '0') {
            return;
        }

        // 将当前岛屿置为 0，避免重复遍历。
        grid[i][j] = '0';

        // 四个方向进行 DFS。
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public int numIslands_2(char[][] grid) {
        // BFS，遍历所有的岛屿。
        int rowL = grid.length;
        int colL = grid[0].length;

        int res = 0;

        for (int i = 0; i < rowL; i++) {
            for (int j = 0; j < colL; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    grid[i][j] = '0';
                    int index = i * colL + j;
                    // 使用 index 存储当前岛屿的位置，化二维为一维。
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(index);
                    while (!queue.isEmpty()) {
                        int cur = queue.poll();
                        int row = cur / colL;
                        // 取商得到行。
                        int col = cur % colL;
                        // 取余得到列。
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            queue.offer((row - 1) * colL + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < rowL && grid[row + 1][col] == '1') {
                            queue.offer((row + 1) * colL + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            queue.offer(row * colL + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < colL && grid[row][col + 1] == '1') {
                            queue.offer(row * colL + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }



}
