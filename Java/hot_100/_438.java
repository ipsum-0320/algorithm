package Java.hot_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _438 {
    public List<Integer> findAnagrams(String s, String p) {
        int left = 0, right = p.length() - 1;
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        String targetP = new String(pArr);
        List<Integer> list = new ArrayList<>();
        while (right < s.length()) {
            // substring 方法不会修改原始字符串，而是返回一个新的字符串作为结果。
            String subS = s.substring(left, right + 1);
            // 注意 substring 是左闭右开区间。
            char[] subSArr = subS.toCharArray();
            Arrays.sort(subSArr);
            String sortedSubS = new String(subSArr);
            if (sortedSubS.equals(targetP)) {
                list.add(left);
            }
            left++;
            right++;
        }
        return list;
    }

    public List<Integer> findAnagrams_2(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<>();
        int left = 0, right = p.length() - 1;
        int[] pCount = new int[26];
        int[] subSCount = new int[26];
        // 默认相应的值为 0。
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
        }
        String subS = s.substring(left, right + 1);
        for (int i = 0; i < p.length(); i++) {
            subSCount[subS.charAt(i) - 'a']++;
        }
        List<Integer> list = new ArrayList<>();
        while (right < s.length()) {
            if (Arrays.equals(pCount, subSCount)) {
                // Arrays.equals 用于检查两个指定的Object数组是否相等或不是。 如果两个数组相等则返回true，否则返回false。 如果两个数组包含相同顺序的相同元素（内容和顺序都相同），则认为它们相等。
                list.add(left);
            }
            subSCount[s.charAt(left) - 'a']--;
            left++;
            right++;
            if (right < s.length()) {
                subSCount[s.charAt(right) - 'a']++;
            }
        }
        return list;
    }

    // 记录 differ 值。
//    public List<Integer> findAnagrams_3(String s, String p) {
//        if (s.length() < p.length()) return new ArrayList<>();
//        int left = 0, right = p.length() - 1;
//        int[] count = new int[26];
//        // 默认相应的值为 0。
//        for (int i = 0; i < p.length(); i++) {
//            count[s.charAt(i) - 'a']++;
//            count[p.charAt(i) - 'a']--;
//            // p 是维持平衡的那一个。
//        }
//        int differ = 0;
//        for (int c : count) {
//            if (c != 0) differ++;
//        }
//
//        List<Integer> list = new ArrayList<>();
//        while (right < s.length()) {
//            if (differ == 0) {
//                list.add(left);
//            }
//            if (count[s.charAt(left) - 'a'] == 1) {
//                --differ;
//            } else if (count[s.charAt(left) - 'a'] == 0) {
//                ++differ;
//            }
//            --count[s.charAt(left) - 'a'];
//            left++;
//            right++;
//            if (right >= s.length()) break;
//            if (count[s.charAt(right) - 'a'] == -1) {
//                --differ;
//            } else if (count[s.charAt(right) - 'a'] == 0) {
//                ++differ;
//            }
//            ++count[s.charAt(right) - 'a'];
//        }
//        return list;
//    }
}
