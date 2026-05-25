class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Count the frequencies
        Map<Integer, Integer> count = new HashMap<>();
        for(int n: nums)
            count.put(n, count.getOrDefault(n, 0)+1);

        //2. Create buckets: index = frequencies
        List<Integer>[] freq = new List[nums.length +1];
        for(int i=0; i<freq.length; i++)
            freq[i] = new ArrayList<>();
        
        //3. Fill the frequencies buckets
        for(Map.Entry<Integer,Integer> entry: count.entrySet()){
            int value = entry.getKey();
            int c = entry.getValue();
            freq[c].add(value);
        }

        //4. Collect from highest frequency to lowest
        int[] res = new int[k];
        int index = 0;

        for(int i = freq.length -1; i > 0 && index < k; i--){
            for(int n : freq[i]){
                res[index++] = n;
                if(index == k)
                    return res;
            }
        }

        return res;
    }
}