class Solution {
    public int leastInterval(char[] tasks, int n) {
                int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;

        int maxFreq = 0;
        for (int f : freq) maxFreq = Math.max(maxFreq, f);

        int maxCount = 0; // number of task types sharing the max frequency
        for (int f : freq) if (f == maxFreq) maxCount++;

        // (maxFreq-1) full frames of size (n+1), plus a final block of maxCount
        int scheduled = (maxFreq - 1) * (n + 1) + maxCount;
        // If tasks are so diverse that no idle time is needed, answer is tasks.length
        return Math.max(tasks.length, scheduled);
    }
}