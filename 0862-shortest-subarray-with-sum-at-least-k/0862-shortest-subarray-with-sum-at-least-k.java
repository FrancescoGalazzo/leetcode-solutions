class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int answer = Integer.MAX_VALUE;

        for (int end = 0; end <= n; end++) {
            while (!deque.isEmpty() && prefix[end] - prefix[deque.peekFirst()] >= k) {
                answer = Math.min(answer, end - deque.pollFirst());
            }
            while (!deque.isEmpty() && prefix[deque.peekLast()] >= prefix[end]) {
                deque.pollLast();
            }
            deque.offerLast(end);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer; 
    }
}