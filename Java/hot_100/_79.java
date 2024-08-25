package Java.hot_100;

import java.util.HashSet;
import java.util.Set;

class _79 {
    Set<Integer> set = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        char key = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == key)
                    if (helper(board, word, i * board[0].length + j, 1))
                        return true;
            }
        }

        return false;
    }

    public boolean helper(char[][] board, String word, int start, int cur) {
        if (cur == word.length())
            return true;

        set.add(start);

        int rowL = start / board[0].length;
        int colL = start % board[0].length;

        if (rowL + 1 < board.length && board[rowL + 1][colL] == word.charAt(cur) && !set.contains((rowL + 1) * board[0].length + colL)) {
            if (helper(board, word, (rowL + 1) * board[0].length + colL, cur + 1))
                return true;
        }

        if (rowL - 1 >= 0 && board[rowL - 1][colL] == word.charAt(cur) && !set.contains((rowL - 1) * board[0].length + colL)) {
            if (helper(board, word, (rowL - 1) * board[0].length + colL, cur + 1))
                return true;
        }

        if (colL + 1 < board[0].length && board[rowL][colL + 1] == word.charAt(cur) && !set.contains(rowL * board[0].length + colL + 1)) {
            if (helper(board, word, rowL * board[0].length + colL + 1, cur + 1))
                return true;
        }

        if (colL - 1 >= 0 && board[rowL][colL - 1] == word.charAt(cur) && !set.contains(rowL * board[0].length + colL - 1)) {
            if (helper(board, word, rowL * board[0].length + colL - 1, cur + 1))
                return true;
        }

        set.remove(start);

        return false;
    }
}