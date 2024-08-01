package Java.everyday;

import java.util.Arrays;

public class _40 {
    // 很巧妙的解法。
    public int maxmiumScore(int[] cards, int cnt) {
        /*
         * 将 cards 从大到小排序后，先贪心的将后 cnt 个数字加起来，若此时 sum 为偶数，直接返回即可。
         * 若此时答案为奇数，有两种方案:
         * 在数组前面找到一个最大的奇数与后 cnt 个数中最小的偶数进行替换；
         * 在数组前面找到一个最大的偶数与后 cnt 个数中最小的奇数进行替换。
         * 两种方案选最大值即可。
         * */
        Arrays.sort(cards);
        int sum = 0;
        int minOu = Integer.MAX_VALUE, minJi = Integer.MAX_VALUE;
        for (int i = cards.length - 1; i >= cards.length - cnt; i--) {
            sum += cards[i];
            if (cards[i] % 2 == 0) {
                minOu = Math.min(cards[i], minOu);
            } else {
                minJi = Math.min(cards[i], minJi);
            }
        }
        if (sum % 2 == 0) {
            return sum;
        }
        if (cards.length - cnt == 0) return 0;
        int sumAddJi = sum - minOu, sumAddOu = sum - minJi;
        int maxJi = Integer.MIN_VALUE, maxOu = Integer.MIN_VALUE;
        for (int i = 0; i < cards.length - cnt; i++) {
            if (cards[i] % 2 == 0) {
                maxOu = Math.max(cards[i], maxOu);
            } else {
                maxJi = Math.max(cards[i], maxJi);
            }
        }
        return Math.max(sumAddJi + maxJi, sumAddOu + maxOu);
    }

}
