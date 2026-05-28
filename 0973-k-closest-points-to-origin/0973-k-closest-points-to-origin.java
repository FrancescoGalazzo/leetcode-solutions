class Solution {

    /*
        public int[][] kClosest(int[][] points, int k) {
        int L = 0, R = points.length - 1;
        int pivot = points.length;

        while (pivot != k) {
            pivot = partition(points, L, R);
            if (pivot < k) {
                L = pivot + 1;
            } else {
                R = pivot - 1;
            }
        }
        int[][] res = new int[k][2];
        System.arraycopy(points, 0, res, 0, k);
        return res;
    }

    private int partition(int[][] points, int l, int r) {
        int pivotIdx = r;
        int pivotDist = euclidean(points[pivotIdx]);
        int i = l;
        for (int j = l; j < r; j++) {
            if (euclidean(points[j]) <= pivotDist) {
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
                i++;
            }
        }
        int[] temp = points[i];
        points[i] = points[r];
        points[r] = temp;
        return i;
    }

    private int euclidean(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    */

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