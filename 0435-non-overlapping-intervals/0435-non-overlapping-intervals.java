class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        
        // Sort by end time: earliest-finishing interval first
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int removed = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prevEnd) {
                // Conflict (strict <): current starts before previous ends
                // Remove current (it ends later, so removing it is the greedy choice)
                removed++;
            } else {
                // No conflict (touching is fine): keep this interval
                prevEnd = intervals[i][1];
            }
        }

        return removed;
    }
}