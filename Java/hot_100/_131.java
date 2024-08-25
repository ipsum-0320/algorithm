package Java.hot_100;

import java.util.ArrayList;
import java.util.List;

public class _131 {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        helper(s, 0, new ArrayList<>());
        return res;
    }

    public void helper(String s, int index, List<String> list) {
        if (index == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String subStr = s.substring(index, i);
            if (isPalindrome(subStr)) {
                list.add(subStr);
                helper(s, i, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s) {
        // 双指针判断是否回文。
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        }
        return true;
    }

}
