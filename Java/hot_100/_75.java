package Java.hot_100;

public class _75 {

    public void sortColors(int[] nums) {
        int[] colors = new int[3];
        for (int i = 0; i < nums.length; i++) {
            colors[nums[i]]++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (colors[0] != 0) {
                nums[i] = 0;
                colors[0]--;
            } else if (colors[1] != 0) {
                nums[i] = 1;
                colors[1]--;
            } else {
                nums[i] = 2;
                colors[2]--;
            }
        }
    }

}
