class Solution {
    public int majorityElement(int[] nums) {
                int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;   // pick a new candidate
                count = 1;
            } else if (num == candidate) {
                count++;           // same as candidate → support
            } else {
                count--;           // different → cancel one vote
            }
        }

        // Because the problem guarantees that a majority element exists,
        // candidate must be that majority.
        return candidate;
    }
}