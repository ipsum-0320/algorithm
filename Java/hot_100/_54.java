package Java.hot_100;

import java.util.ArrayList;
import java.util.List;

public class _54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int rowL = matrix.length, colL = matrix[0].length;
        // 直接采用模拟的方式。
        List<Integer> list = new ArrayList<>();
        int[][] isTraversal = new int[rowL][colL];
        // 向右、向下、向左、向上。(row, col)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int allStep = rowL * colL, curStep = 0;
        int row = 0, col = 0, moveMode = 0;

        while (curStep < allStep) {
            list.add(matrix[row][col]);
            isTraversal[row][col] = 1;
            curStep++;
            int[] mode = directions[moveMode];
            if (row + mode[0] < 0 || row + mode[0] >= rowL || col + mode[1] < 0 || col + mode[1] >= colL || isTraversal[row + mode[0]][col + mode[1]] == 1) {
                moveMode = (moveMode + 1) % directions.length;
            }
            row += directions[moveMode][0];
            col += directions[moveMode][1];
        }

        return list;
    }

    public List<Integer> spiralOrder_2(int[][] matrix) {
        int rowL = matrix.length, colL = matrix[0].length;
        // 直接采用模拟的方式。
        List<Integer> list = new ArrayList<>();

        // 定义边界值
        int left = 0, right = colL - 1, top = 0, bottom = rowL - 1;

        // 向右、向下、向左、向上。(row, col)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int allStep = rowL * colL, curStep = 0;
        int row = 0, col = 0, moveMode = 0;
        while (curStep < allStep) {
            list.add(matrix[row][col]);
            curStep++;
            int[] mode = directions[moveMode];
            if (col + mode[1] > right) {
                // 需要 else，避免影响。
                moveMode = (moveMode + 1) % directions.length;
                top++;
            } else if (col + mode[1] < left) {
                moveMode = (moveMode + 1) % directions.length;
                bottom--;
            } else if (row + mode[0] > bottom) {
                moveMode = (moveMode + 1) % directions.length;
                right--;
            } else if (row + mode[0] < top) {
                moveMode = (moveMode + 1) % directions.length;
                left++;
            }
            row += directions[moveMode][0];
            col += directions[moveMode][1];
        }
        return list;
    }

}
