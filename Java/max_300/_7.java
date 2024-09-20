package Java.max_300;

public class _7 {

    public int reverse(int x) {
        int res = 0;
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;

        while (x != 0) {
            int low = x % 10;
            // 判断溢出。
            if (res < 0) {
                int negLow = min % 10;
                if (res < min / 10 || (res == min / 10 && low < negLow)) {
                    return 0;
                }
            } else {
                int posLow = max % 10;
                if (res > max / 10 || (res == max / 10 && low > posLow)) {
                    return 0;
                }
            }
            x = x / 10;
            res = res * 10 + low;
        }
        return res;
    }

}
