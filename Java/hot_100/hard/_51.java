package Java.hot_100.hard;

import java.util.ArrayList;
import java.util.List;

public class _51 {
    private List<List<String>> res = new ArrayList<>();
    private int n;

    public List<List<String>> solveNQueens(int n) {
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) board[i][j] = ".";
        }
        this.n = n;
        dfs(0, board);
        return res;
    }

    public void dfs(int row, String[][] board) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
                list.add(sb.toString());
            }
            res.add(list);
            return;
        }

        for (int i = 0; i < n; i++) {
            board[row][i] = "Q";
            if (isValid(board, row, i)) {
                dfs(row + 1, board);
            }
            board[row][i] = ".";
        }

    }

    public boolean isValid(String[][] board, int row, int col) {
        // isValid 只考虑上半部分就好，下半部分还没有填入。
        for (int i = 0; i < row; i++) {
            if (board[i][col].equals("Q")) return false;
        }

        int i = row - 1, j = col - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j].equals("Q")) return false;
            i--;
            j--;
        }

        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < n) {
            if (board[i][j].equals("Q")) return false;
            i--;
            j++;
        }
        return true;
    }
}
