class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> res = new HashMap<>();

        for(String s: strs){
            int[] count = new int[26];
            
             for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }

            // Create a short, fast-to-hash custom string key
            char[] keyChars = new char[26];
            for (int i = 0; i < 26; i++) {
                keyChars[i] = (char) (count[i]);
            }
            String key = new String(keyChars);

            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }

        return new ArrayList<>(res.values());
    }
}