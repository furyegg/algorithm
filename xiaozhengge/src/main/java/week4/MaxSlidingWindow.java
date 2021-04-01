package week4;

import java.util.Arrays;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            int end = i + k;
            if (end > nums.length) {
                break;
            }
            int max = max(nums, i, i + k);
            nums[i] = max;
            ++count;
        }
        return Arrays.copyOf(nums, count);
    }
    
    private int max(int[] nums, int start, int end) {
        int max = nums[start];
        for (int i = start + 1; i < end; ++i) {
            max = max < nums[i] ? nums[i] : max;
        }
        return max;
    }
    
    public static void main(String[] args) {
        MaxSlidingWindow app = new MaxSlidingWindow();
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        int[] res = app.maxSlidingWindow(nums, 3);
        for (int n : res) {
            System.out.println(n);
        }
    }
}