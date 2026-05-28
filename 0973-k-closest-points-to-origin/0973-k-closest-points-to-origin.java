class Solution {
    public int[][] kClosest(int[][] points, int k) {
                // Keep an int[] where index 0 is squared distance, index 1 is the point's index
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        int[][] results = new int[k][2];

        for (int i = 0; i < points.length; i++) {
            // No Math.sqrt needed! Just x^2 + y^2
            int squaredDist = points[i][0] * points[i][0] + points[i][1] * points[i][1];

            maxHeap.offer(new int[]{squaredDist, i});

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        for (int j = 0; j < k; j++) {
            results[j] = points[maxHeap.poll()[1]]; // No ugly casting!
        }

        return results;
    }
}