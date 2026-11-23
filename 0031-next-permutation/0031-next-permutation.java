class Solution {
 public void nextPermutation(int[] nums) {
        
        int i = nums.length -2;

        // Step 1: I find the first index i from right such that: nums[i] < nums[i + 1]
        while(i>=0 && nums[i] >= nums[i+1])
            i--;

        //Step 2: If i >= 0 it means we are not at the last permutation
        if(i>=0){
            int j = nums.length-1;

            while(j>=0 && nums[j] <= nums[i])
                j--;

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        // Step 3: I have to reverse all the element from i+1 til the end

        int left = i+1;
        int right = nums.length -1;

        while(left < right){
            int temp = nums[left];
            nums[left] =nums[right];
            nums[right] = temp;

            left++;
            right--;
        }       

    }
}