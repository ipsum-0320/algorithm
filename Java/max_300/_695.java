package Java.max_300;

public class _695 {
    int max = 0;
    int res = 0;
    int[][] dir = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1 ,0}
    };

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] isVisited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !isVisited[i][j]) {
                    res = 1;
                    dfs(grid, isVisited, i, j);
                }
            }
        }

        return max;
    }

    void dfs(int[][] grid, boolean[][] isVisited, int row, int col) {
        max = Math.max(res, max);
        isVisited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int newRow = row + dir[i][0], newCol = col + dir[i][1];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length &&
                    !isVisited[newRow][newCol] && grid[newRow][newCol] == 1) {
                // 岛屿面积不是回溯，所以不需要携带 res 到 dfs 里面，每一个岛屿面积都算数，因此需要累加。
                // 可以使用 dfs 返回面积，也可以使用全局变量累加。
                res++;
                dfs(grid, isVisited, newRow, newCol);
            }
        }
    }

}
