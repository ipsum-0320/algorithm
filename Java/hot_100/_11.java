package Java.hot_100;

public class _11 {
    public int maxArea(int[] height) {
        // 利用贪心去做。
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int curH = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, curH * (right - left));
            if (height[left] < height[right]) {
                int curLH = height[left];
                while (left < right && height[left] <= curLH) {
                    left++;
                }
            } else {
                int curRH = height[right];
                while (left < right && height[right] <= curRH) {
                    right--;
                }
            }
        }
        return  maxArea;
    }
}
