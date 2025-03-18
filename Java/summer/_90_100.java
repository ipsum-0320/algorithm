package Java.summer;

public class _90_100 {
    // 62-287
    // 62

    // 64

    // 5
    public String longestPalindrome(String s) {
        // 用动规去判断是否是回文子串。
        boolean[][] dp = new boolean[s.length()][s.length()];
        // dp[i][j] 就表示 s[i, j] 是否是回文子串。

        for (int i = 0; i < s.length(); i++)
            dp[i][i] = true;

        for (int i = 0; i + 1 < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }

        for (int i = 2; i < s.length(); i++) {
            for (int j = 0; j + i < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j + i)) {
                    dp[j][j + i] = dp[j + 1][j + i - 1];
                }
            }
        }
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j + i < s.length(); j++) {
                if (dp[j][j + i] && i + 1 > str.length()) {
                    str = s.substring(j, j + i + 1);
                }
            }
        }

        return str;
    }

    // 1143
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i <= text1.length(); i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= text2.length(); i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                // 主动求变，i != j 时，dp[i][j] 就是 Math.max(dp[i - 1][j], dp[i][j - 1])。
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    // 72
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // 编辑距离三步操作。
        // dp[i][j] 表示将字符串从 word1[0, i] 变成 word2[0, j] 最少需要几步操作。

        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                dp[i][j] = dp[i - 1][j - 1] + 1; // 替换一个字符。
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1); // 插入一个字符。
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1); // 删除一个字符。
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 如果 word1 和 word2 相同。
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }


    // 31
    public void nextPermutation(int[] nums) {
        // 下一个排列。
        /*
         * (1) 我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
         * (2) 同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。
         * (3) 这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
         * */

        // 根据代码中的思路，我们先找到第一个「小-大」组合。
        int index;
        for (index = nums.length - 1; index >= 0; index--) {
            if (index - 1 < 0 || nums[index - 1] < nums[index]) {
                break;
            }
        }
        index--; // 继续自减，让 index 指向小的那一个;

        // index == -1; 说明下一个排列是：小、中、大。
        if (index == -1) {
            // 直接 reverse。
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
            return;
        }

        // 此时，index 指向较小的那一个。
        // 然后需要，在 [index + 1, last] 中找到第一个比 nums[index] 大的，[index + 1, last] 是降序排列的。
        // 可以用二分查找，我们这里直接顺序查找。
        for (int i = nums.length - 1; i != index; i--) {
            if (nums[i] > nums[index]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                break;
            }
        }

        int left = index + 1, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    // 75 - 双指针解法
    public void sortColors(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            while (left < nums.length && nums[left] == 0) left++;
            // nums[left] 第一个不为 0 的。
            while (right < nums.length && nums[right] != 0 || right < left) right++;
            // nums[right] 是第一个为 0 的。
            if (left < nums.length && right < nums.length) {
                nums[right] = nums[left];
                nums[left] = 0;
            }
        }
        // left 之前都是 0。
        int bound = left;
        left = nums.length - 1;
        right = nums.length - 1;
        while (left >= bound) {
            while (right >= 0 && nums[right] == 2) right--;
            // nums[right] 第一个不为 2 的。
            while (left >= 0 && nums[left] != 2 || left > right) left--;
            // nums[left] 是第一个为 2 的。
            if (right >= 0 && left >= 0) {
                nums[left] = nums[right];
                nums[right] = 2;
            }
        }
    }

}
