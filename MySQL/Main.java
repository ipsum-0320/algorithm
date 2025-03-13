package MySQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();

        int[] arr = new int[n + 1];
        boolean[] isEven = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
            isEven[i] = (arr[i] % 2 == 0);
        }

        List<List<Integer>> nei = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nei.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            nei.get(u).add(v);
            nei.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];
        long res = 0;
        for (int i = 1; i <= n; i++) {
            if (isEven[i] && !visited[i]) {
                int count = dfs(i, visited, nei, isEven);
                res += ((long) count * (count + 1)) / 2;
            }
        }

        System.out.println(res);

    }

    private static int dfs(int node, boolean[] visited, List<List<Integer>> nei, boolean[] even) {
        visited[node] = true;
        int count = 1;
        for (int n : nei.get(node)) {
            if (even[n] && !visited[n]) {
                count += dfs(n, visited, nei, even);
            }
        }
        return count;
    }
}