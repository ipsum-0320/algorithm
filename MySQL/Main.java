package MySQL;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        long l1 = in.nextInt();
        long r1 = in.nextInt();
        long l2 = in.nextInt();
        long r2 = in.nextInt();
        long sum1 = helper(r1, r2) - helper(r1, l2 - 1);
        long sum2 = helper(l1 - 1, r2) - helper(l1 - 1, l2 - 1);
        System.out.println(sum1 - sum2);
    }

    public static long helper(long prefix, long suffix) {
        if (prefix == 0) {
            return 0;
        }
        if (suffix == 0) {
            return 0;
        }
        long finalRes = 0;
        for (long i = 1; i <= suffix; ) {
            long bus = prefix / i;
            if (bus != 0) {
                long preBus = prefix / bus;
                if (preBus > suffix)
                    preBus = suffix;
                finalRes += bus * (preBus - i + 1);
                i = preBus + 1;
            } else break;
        }

        return finalRes;
    }
}