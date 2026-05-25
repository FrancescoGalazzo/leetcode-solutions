class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for(int n: nums)
            numSet.add(n);

        int res = 0;

        for(int num: numSet){
            if(!numSet.contains(num-1)){
                int lenght = 1;
                
                while(numSet.contains(num+lenght))
                    lenght++;
                
                res = Math.max(lenght, res);
            }
        }

        return res;
    }
}