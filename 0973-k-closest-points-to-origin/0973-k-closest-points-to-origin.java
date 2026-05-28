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
        // max-heap: in cima c'è il punto con distanza più GRANDE tra quelli nel heap
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[0], a[0])   // ordina per distanza² decrescente
        );

        // Scorri tutti i punti
        for (int[] p : points) {
            int x = p[0];
            int y = p[1];
            int dist2 = x * x + y * y;  // distanza al quadrato

            // Nel heap metto: [dist², x, y]
            maxHeap.offer(new int[]{dist2, x, y});

            // Se ho più di k elementi, butto via il più lontano (in cima al max-heap)
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Ora nel heap sono rimasti esattamente k punti più vicini
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] e = maxHeap.poll();   // e = [dist², x, y]
            res[i][0] = e[1];           // x
            res[i][1] = e[2];           // y
        }

        return res;
    }
}