package week6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        if (len == 2) {
            return nums[0] < nums[1] ? 1 : 0;
        }
        
        Task task = new Task(nums, 1, nums.length - 2);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> res = pool.submit(task);
        int index = res.join();
        pool.shutdown();
        
        if (index == -1) {
            if (nums[0] > nums[1]) {
                return 0;
            }
            if (nums[len - 1] > nums[len - 2]) {
                return len - 1;
            }
        }
        return index;
    }
    
    private static class Task extends RecursiveTask<Integer> {
        int[] nums;
        int start;
        int end;
        
        public Task(int[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Integer compute() {
            if (start == end) {
                return isPeak(nums, start) ? start : -1;
            }
            
            if (start > end) {
                return -1;
            }
            
            int m = (start + end) / 2;
            if (isPeak(nums, m)) {
                return m;
            }
            
            Task t1 = new Task(nums, start, m - 1);
            Task t2 = new Task(nums, m + 1, end);
            ForkJoinTask<Integer> f1 = t1.fork();
            ForkJoinTask<Integer> f2 = t2.fork();
            Integer r1 = f1.join();
            Integer r2 = f2.join();
            if (r1 > 0) {
                return r1;
            }
            if (r2 > 0) {
                return r2;
            }
            return -1;
        }
        
        private boolean isPeak(int[] nums, int index) {
            return nums[index - 1] < nums[index] && nums[index] > nums[index + 1];
        }
    }
    
    public static void main(String[] args) {
        FindPeakElement app = new FindPeakElement();
        int[] nums3 = {6, 5, 4, 3, 2, 3, 2};
        System.out.println(app.findPeakElement(nums3));
        
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(app.findPeakElement(nums1));
        
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(app.findPeakElement(nums2));
    }
}