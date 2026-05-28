import java.util.PriorityQueue;

class Solution {

    /*
        Soluzione 1: Quickselect iterativo (media O(n), worst O(n^2))

        public int[][] kClosest(int[][] points, int k) {
            int L = 0, R = points.length - 1;
            int pivot = points.length;

            // Cerco una posizione pivot tale che tutti i punti più vicini siano nelle prime k posizioni
            while (pivot != k) {
                pivot = partition(points, L, R);
                if (pivot < k) {
                    // Se pivot è prima di k, i k punti sono a destra
                    L = pivot + 1;
                } else {
                    // Se pivot è dopo (o uguale) a k, i k punti sono a sinistra
                    R = pivot - 1;
                }
            }

            int[][] res = new int[k][2];
            System.arraycopy(points, 0, res, 0, k);
            return res;
        }

        // Partition stile Quicksort su int[][], usando la distanza al quadrato come chiave
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
            return i; // posizione finale del pivot
        }

        // Distanza al quadrato dall'origine: x^2 + y^2 (niente Math.sqrt)
        private int euclidean(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }
    */

    // Soluzione 2: max-heap (O(n log k), stabile e semplice)

    public int[][] kClosest(int[][] points, int k) {
     // max-heap per distanza: confronta i punti direttamente
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(
                b[0] * b[0] + b[1] * b[1],
                a[0] * a[0] + a[1] * a[1]
            )
        );

        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[k][2];
        for (int i = k - 1; i >= 0; i--) {
            int[] p = maxHeap.poll();
            res[i][0] = p[0];
            res[i][1] = p[1];
        }
        return res;
    }
}