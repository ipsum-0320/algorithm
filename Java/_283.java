package Java;

public class _283 {
    // 双指针，我的解法，需要考虑很多 corner case。
    public void moveZeroes(int[] nums) {
        // 双指针。
        int p1 = 0, p2 = 0;
        while (p1 < nums.length && p2 < nums.length ) {
            while (p1 < nums.length && nums[p1] != 0) {
                // p1 寻找 0。
                p1++;
            }
            while (p2 < nums.length && nums[p2] == 0) {
                // p2 寻找非 0。
                p2++;
            }
            if (p1 > p2) {
                p2++;
                continue;
            }
            if (p1 < nums.length && p2 < nums.length) {
                int temp = nums[p1];
                nums[p1] = nums[p2];
                nums[p2] = temp;
            }
        }
    }

    public void moveZeroes_1(int[] nums) {
        if(nums==null) {
            return;
        }
        //第一次遍历的时候，j 指针记录非 0 的个数，只要是非 0 的统统都赋给 nums[j]
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[j++] = nums[i];
                // 不需要关注 nums[j] 是不是为 0。
            }
        }
        //非 0 元素统计完了，剩下的都是 0 了
        //所以第二次遍历把末尾的元素都赋为 0 即可
        for(int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}


