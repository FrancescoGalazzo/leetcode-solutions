class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for(int n: nums)
            numSet.add(n);

        int res = 0;

        for(int num: numSet){
            if(!numSet.contains(num-1)){
                int length = 1;
                
                while(numSet.contains(num+length))
                    length++;
                
                res = Math.max(length, res);
            }
        }

        return res;
    }
}