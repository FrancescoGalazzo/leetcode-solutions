class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequencies using a HashMap
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Sort by frequencies and extract the top K elements
        return countMap.entrySet().stream()
            // Sort by value (frequency) in descending order
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            // Take only the first k entries
            .limit(k)
            // Extract the original number (the key)
            .mapToInt(Map.Entry::getKey)
            // Convert the stream into the required primitive int[] array
            .toArray();
    }
}