package Java.summer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class _51_60 {
    // 200-79
    // 200，图论的关键就是 DFS 和 BFS。
    public int numIslands(char[][] grid) {
        // DFS 做法。
        int[][] dir = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] == '0') {
                    continue;
                }
                res++;
                numIslandsHelper(i, j, grid, dir, visited);
            }
        }
        return res;
    }

    public void numIslandsHelper(int row, int col, char[][] grid, int[][] dir, boolean[][] visited) {
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int newRow = row + dir[i][0], newCol = col + dir[i][1];
            if (newRow >= 0 &&
                    newRow < grid.length &&
                    newCol >= 0 &&
                    newCol < grid[0].length &&
                    grid[newRow][newCol] == '1' &&
                    !visited[newRow][newCol]) {
                numIslandsHelper(newRow, newCol, grid, dir, visited);
            }
        }
    }

    // 200，BFS 做法。
    public int numIslands_2(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                res++;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i * grid[0].length + j);
                while (!queue.isEmpty()) {
                    int index = queue.poll();
                    int row = index / grid[0].length, col = index % grid[0].length;
                    if ((row + 1) >= 0
                            && (row + 1) < grid.length
                            && col >= 0
                            && col < grid[0].length
                            && grid[row + 1][col] == '1') {
                        grid[row + 1][col] = '0';
                        queue.add((row + 1) * grid[0].length + col);
                    }
                    if ((row - 1) >= 0
                            && (row - 1) < grid.length
                            && col >= 0
                            && col < grid[0].length
                            && grid[row - 1][col] == '1') {
                        grid[row - 1][col] = '0';
                        queue.add((row - 1) * grid[0].length + col);
                    }
                    if (row >= 0
                            && row < grid.length
                            && (col + 1) >= 0
                            && (col + 1) < grid[0].length
                            && grid[row][col + 1] == '1') {
                        grid[row][col + 1] = '0';
                        queue.add(row * grid[0].length + (col + 1));
                    }
                    if (row >= 0
                            && row < grid.length
                            && (col - 1) >= 0
                            && (col - 1) < grid[0].length
                            && grid[row][col - 1] == '1') {
                        grid[row][col - 1] = '0';
                        queue.add(row * grid[0].length + (col - 1));
                    }
                }
            }
        }
        return res;
    }

    // 994，多源 BFS。
    public int orangesRotting(int[][] grid) {
        // 构建多源 bfs。
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(i * grid[0].length + j);
                }
            }
        }
        // 多源 BFS 需要一开始就直接把所有源头放到一个 queue 中。

        int minute = queue.isEmpty() ? 0 : -1;
        while (!queue.isEmpty()) {
            minute++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int index = queue.poll();
                int row = index / grid[0].length;
                int col = index % grid[0].length;
                if (row >= 0 && row < grid.length &&
                        col + 1 >= 0 && col + 1 < grid[0].length &&
                        grid[row][col + 1] == 1) {
                    grid[row][col + 1] = 2;
                    queue.add(row * grid[0].length + (col + 1));
                }
                if (row >= 0 && row < grid.length &&
                        col - 1 >= 0 && col - 1 < grid[0].length &&
                        grid[row][col - 1] == 1) {
                    grid[row][col - 1] = 2;
                    queue.add(row * grid[0].length + (col - 1));
                }
                if (row + 1 >= 0 && row + 1 < grid.length &&
                        col >= 0 && col < grid[0].length &&
                        grid[row + 1][col] == 1) {
                    grid[row + 1][col] = 2;
                    queue.add((row + 1) * grid[0].length + col);
                }
                if (row - 1 >= 0 && row - 1 < grid.length &&
                        col >= 0 && col < grid[0].length &&
                        grid[row - 1][col] == 1) {
                    grid[row - 1][col] = 2;
                    queue.add((row - 1) * grid[0].length + col);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return minute;
    }

    // 207
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> toIndegreeMap = new HashMap<>();
        // 存储某一个节点的入度数。
        Map<Integer, List<Integer>> fromIndegreeMap = new HashMap<>();
        // 存储某一个节点对应有哪些出度。
        for (int[] pair : prerequisites) {
            toIndegreeMap.put(pair[1], toIndegreeMap.getOrDefault(pair[1], 0) + 1);
            if (!fromIndegreeMap.containsKey(pair[0])) {
                fromIndegreeMap.put(pair[0], new ArrayList<>());
            }
            fromIndegreeMap.get(pair[0]).add(pair[1]);
        }
        // 初始化 queue。
        Queue<Integer> courses = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (toIndegreeMap.getOrDefault(i, 0) == 0) {
                courses.add(i);
            }
        }
        while (!courses.isEmpty()) {
            Integer course = courses.poll();
            numCourses--;
            for (Integer to : fromIndegreeMap.getOrDefault(course, List.of())) {
                toIndegreeMap.put(to, toIndegreeMap.get(to) - 1);
                // 入度归零，可以被开课了。
                if (toIndegreeMap.get(to) == 0) {
                    courses.add(to);
                }
            }
        }
        return numCourses == 0;
    }

    // 208
    class Trie {
        Trie[] branch = null;
        boolean isEnd = false;

        // 用来确定是不是某一个字符串的最后一个，如果是则为 true，主要服务于 search。
        public Trie() {
            this.branch = new Trie[26];
            // 保留下一个字符，包括终止字符。
        }

        public void insert(String word) {
            if (word.isEmpty()) {
                this.isEnd = true;
                return;
            }
            char c = word.charAt(0);
            if (branch[c - 'a'] == null) {
                branch[c - 'a'] = new Trie();
            }
            branch[c - 'a'].insert(word.substring(1));
        }

        public boolean search(String word) {
            if (word.isEmpty()) {
                return this.isEnd;
            }
            char c = word.charAt(0);
            if (branch[c - 'a'] == null) {
                return false;
            }
            return branch[c - 'a'].search(word.substring(1));
        }

        public boolean startsWith(String prefix) {
            if (prefix.isEmpty()) {
                return true;
            }
            char c = prefix.charAt(0);
            if (branch[c - 'a'] == null) {
                return false;
            }
            return branch[c - 'a'].startsWith(prefix.substring(1));
        }
    }

    // 46，回溯的模板解法。
    List<List<Integer>> permute_res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 回溯。
        helper(nums, new Stack<>());
        return permute_res;
    }

    public void helper(int[] nums, Stack<Integer> tmp) {
        if (tmp.size() == nums.length) {
            permute_res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])) {
                continue;
            }
            tmp.push(nums[i]);
            helper(nums, tmp);
            tmp.pop();
        }
    }

    // 78-选取 or 不选取的做法。
    List<List<Integer>> subsets_res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsets_helper(0, new ArrayList<>(), nums);
        return subsets_res;
    }

    public void subsets_helper(int start, List<Integer> tmp, int[] nums) {
        if (start == nums.length) {
            subsets_res.add(new ArrayList<>(tmp));
            return;
        }
        // 不添加。
        subsets_helper(start + 1, tmp, nums);
        // 添加。
        tmp.add(nums[start]);
        subsets_helper(start + 1, tmp, nums);
        tmp.remove(tmp.size() - 1);
    }

    // 78-回溯模板做法。
    List<List<Integer>> subsets_res_2 = new ArrayList<>();

    public List<List<Integer>> subsets_2(int[] nums) {
        subsets_helper_2(0, new ArrayList<>(), nums);
        return subsets_res_2;
    }

    public void subsets_helper_2(int start, List<Integer> tmp, int[] nums) {
        subsets_res_2.add(new ArrayList<>(tmp));
        // 普通节点也算结果，不分配也算是一种结果。
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            subsets_helper_2(i + 1, tmp, nums);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 17-回溯。
    List<String> letterCombinations_res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return letterCombinations_res;
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        letterCombinations_helper(digits, 0, new ArrayList<>(), map);
        return letterCombinations_res;
    }
    public void letterCombinations_helper(String digits, int index, List<Character> tmp, Map<Character, String> map) {
        if (tmp.size() == digits.length()) {
            StringBuilder str = new StringBuilder();
            for (Character character : tmp) {
                str.append(character);
            }
            letterCombinations_res.add(str.toString());
            return;
        }
        for (int i = index; i < digits.length(); i++) {
            String str = map.get(digits.charAt(i));
            for (int j = 0; j < str.length(); j++) {
                tmp.add(str.charAt(j));
                letterCombinations_helper(digits, i + 1, tmp, map);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    // 39
    List<List<Integer>> combinationSum_res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum_helper(candidates, target, new ArrayList<>(), 0, 0);
        return combinationSum_res;
    }
    public void combinationSum_helper(int[] candidates, int target, List<Integer> tmp, int sum, int start) {
        if (sum == target) {
            combinationSum_res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] + sum > target) return;
            tmp.add(candidates[i]);
            combinationSum_helper(candidates, target, tmp, sum + candidates[i], i);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 22
    List<String> generateParenthesis_res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        generateParenthesis_helper(n, n, new StringBuilder());
        return generateParenthesis_res;
    }
    public void generateParenthesis_helper(int left, int right, StringBuilder sb) {
        if (left == 0 && right == 0) {
            generateParenthesis_res.add(sb.toString());
            return;
        }
        if (right > left) {
            sb.append(")");
            generateParenthesis_helper(left, right - 1, sb);
            sb.delete(sb.length() - 1, sb.length());
        }
        if (left > 0) {
            sb.append("(");
            // 前进
            generateParenthesis_helper(left - 1, right, sb);
            // 回退
            sb.delete(sb.length() - 1, sb.length());
        }
    }

    // 79
    boolean exist_res = false;
    boolean[][] visited = null;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        // 回溯剪枝。
        int[][] dirs = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist_res) return true;
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    exist_helper(board, word, 1, dirs, i, j);
                    visited[i][j] = false;
                }
            }
        }
        return exist_res;
    }
    public void exist_helper(char[][] board, String word, int index, int[][] dirs, int row, int col) {
        if (index == word.length()) {
            exist_res = true;
            return;
        }
        for (int i = 0; i < dirs.length; i++) {
            if (exist_res) return;
            int newRow = row + dirs[i][0], newCol = col + dirs[i][1];
            if (newRow >= 0 && newRow < board.length &&
                newCol >= 0 && newCol < board[0].length &&
                board[newRow][newCol] == word.charAt(index) &&
                !visited[newRow][newCol]) {
                visited[newRow][newCol] = true;
                exist_helper(board, word, index + 1, dirs, newRow, newCol);
                visited[newRow][newCol] = false;
            }
        }
    }

}
