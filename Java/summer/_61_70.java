package Java.summer;

import java.util.ArrayList;
import java.util.List;

public class _61_70 {
    // 131-155
    // 131
    List<List<String>> partition_res = new ArrayList<>();
    public List<List<String>> partition(String s) {
        partition_helper(s, 0, new ArrayList<>());
        return partition_res;
    }
    public void partition_helper(String s, int index, List<String> tmp) {
        if (index == s.length()) {
            partition_res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (checker(s.substring(index, i + 1))) {
                tmp.add(s.substring(index, i + 1));
                partition_helper(s, i + 1, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
    public boolean checker(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 51
    List<List<String>> solveNQueens_res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        // 回溯。
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == i) sb.append("Q");
                else sb.append(".");
            }
            tmp.add(sb.toString());
            solveNQueens_helper(1, n, tmp);
            tmp.remove(tmp.size() - 1);
        }
        return solveNQueens_res;
    }
    public void solveNQueens_helper(int cur, int n, List<String> tmp) {
        if (n == cur) {
            solveNQueens_res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < n; i++) {
            // 此时坐标为 [cur, i]
            // 检查是否在同一个列。
            boolean skip = false;
            for (int j = 0; j < tmp.size(); j++) {
                if (tmp.get(j).charAt(i) == 'Q') {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            // 检查是否在同一主对角线。
            for (int j = 1; cur - j >= 0 && i - j >= 0; j++) {
                if (tmp.get(cur - j).charAt(i - j) == 'Q') {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            // 检查是否在同一副对角线。
            for (int j = 1; cur - j >= 0 && i + j < n; j++) {
                if (tmp.get(cur - j).charAt(i + j) == 'Q') {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == i) sb.append("Q");
                else sb.append(".");
            }
            tmp.add(sb.toString());
            solveNQueens_helper(cur + 1, n, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 35
    public int searchInsert(int[] nums, int target) {
        // 抽象为二分搜索问题，搜索第一个等于或者大于 target 的位置。
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;
            else if (target < nums[mid]) right = mid;
            // 寻找第一个大的，因此要 right = mid，继续寻找。
            // 注意是 target 不动，mid 动，mid 去寻找 target。
            else left = mid;
        }
        if (nums[left] >= target) return left;
        else if (nums[right] >= target) return right;
        else return right + 1;
    }

    // 74
    public boolean searchMatrix(int[][] matrix, int target) {
        // 化二维为一维。
        int left = 0, right = matrix.length * matrix[0].length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) right = mid;
            else left = mid;
        }
        int row = left / matrix[0].length;
        int col = left % matrix[0].length;
        if (matrix[row][col] == target) return true;
        row = right / matrix[0].length;
        col = right % matrix[0].length;
        return matrix[row][col] == target;
    }

    // 34
    public int[] searchRange(int[] nums, int target) {
        // 二分查找。
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        // 查找第一个位置。
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) right = mid;
            else left = mid;
        }
        if (nums[left] == target) res[0] = left;
        else if (nums[right] == target) res[0] = right;
        // 查找第最后一个位置。

        left = 0;
        right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) right = mid;
            else left = mid;
        }
        if (nums[right] == target) res[1] = right;
        else if (nums[left] == target) res[1] = left;

        return res;
    }

    // 33
    public int search(int[] nums, int target) {
        // 趋势图可能为 / | /，所以额外需要一个变量判断是第一个上涨段还是第二个上涨段。
        int first = nums[0];
        if (target >= first) {
            // target 可能在第一段。
            int left = 0, right = nums.length - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] > target) right = mid;
                else {
                    // nums[mid] < target
                    if (nums[mid] < first) right = mid;
                    else left = mid;
                }
            }
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            return -1;
        } else {
            int left = 0, right = nums.length - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] < target) left = mid;
                else {
                    // nums[mid] > target
                    if (nums[mid] < first) right = mid;
                    else left = mid;
                }
            }
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            return -1;
        }
    }

    // 153
    public int findMin(int[] nums) {


    }




}
