class Solution {

    private int dist2(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }

    private int partition(int[][] points, int l, int r) {
        int[] pivot = points[r];
        int pivotDist = dist2(pivot);
        int i = l;
        for (int j = l; j < r; j++) {
            if (dist2(points[j]) <= pivotDist) {
                swap(points, i, j);
                i++;
            }
        }
        swap(points, i, r);
        return i;
    }

    private void quickselect(int[][] points, int l, int r, int K) {
        if (l >= r) return;
        int p = partition(points, l, r);
        int leftCount = p - l + 1;
        if (leftCount == K) {
            return;
        } else if (leftCount > K) {
            quickselect(points, l, p - 1, K);
        } else {
            quickselect(points, p + 1, r, K - leftCount);
        }
    }

    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    public int[][] kClosest(int[][] points, int K) {
        quickselect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    /*
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
    */
}