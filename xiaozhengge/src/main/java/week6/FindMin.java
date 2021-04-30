package week6;

public class FindMin {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int index = find(nums, 1, nums.length);
        if (index == 0) {
            return check(nums, 1) ? nums[1] : nums[0];
        } else {
            return nums[index];
        }
    }
    
    private int find(int[] nums, int start, int end) {
        if (start > end || start >= nums.length) {
            return 0;
        }
        if (start == end) {
            return check(nums, start) ? start : 0;
        }
        
        int m = (start + end) / 2;
        if (check(nums, m)) {
            return m;
        }
        
        int f1 = find(nums, start, m - 1);
        if (f1 > 0) {
            return f1;
        }
        
        int f2 = find(nums, m + 1, end);
        if (f2 > 0) {
            return f2;
        }
        return 0;
    }
    
    private boolean check(int[] nums, int index) {
        return nums[index - 1] > nums[index];
    }
    
    public static void main(String[] args) {
        FindMin app = new FindMin();
        int[] nums2 = {3,4,5,1,2};
        System.out.println(app.findMin(nums2));

        int[] nums3 = {4,5,6,7,0,1,2};
        System.out.println(app.findMin(nums3));
    
        int[] nums1 = {11,13,15,17};
        System.out.println(app.findMin(nums1));
    
        int[] nums4 = {18,11,13,15,17};
        System.out.println(app.findMin(nums4));
    
        int[] nums5 = {1,2};
        System.out.println(app.findMin(nums5));
    
        int[] nums6 = {2,1};
        System.out.println(app.findMin(nums6));
    }
}