package Java.hot_100;

import java.util.ArrayList;
import java.util.List;

public class _22 {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        helper(n, n, new StringBuilder());
        // 类比子集的解法，取 or 不取。
        return res;
    }

    public void helper(int left, int right, StringBuilder sb) {
        // left 代表左括号的数量，right 代表右括号的数量。
        if (left > right) {
            // 剪枝，左括号的数量不能大于右括号的数量。
            return;
        }
        if (left == 0 && right == 0) {
            // 递归终止条件。
            res.add(sb.toString());
            return;
        }
        if (left - 1 >= 0) {
            // 剪枝，左括号的数量不能小于 0。
            helper(left - 1, right, new StringBuilder(sb).append("("));
        }
        if (right - 1 >= 0) {
            // 剪枝，右括号的数量不能小于 0。
            helper(left, right - 1, new StringBuilder(sb).append(")"));
        }
    }


}
