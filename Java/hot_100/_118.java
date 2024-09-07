package Java.hot_100;

import java.util.ArrayList;
import java.util.List;

public class _118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 1; i <= numRows; i++) {
            if (i == 1) res.add(List.of(1));
            else if (i == 2) res.add(List.of(1, 1));
            else {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                for (int j = 1; j < i - 1; j++) {
                    list.add(res.get(res.size() - 1).get(j - 1) + res.get(res.size() - 1).get(j));
                }
                list.add(1);
                res.add(list);
            }
        }

        return res;
    }

}
