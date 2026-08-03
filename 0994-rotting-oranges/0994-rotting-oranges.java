class Solution {
    public int orangesRotting(int[][] grid) {
           int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        int freshOranges = 0;

        // Scansione iniziale:
        // - conta le arance fresche
        // - aggiunge tutte le arance inizialmente marce alla queue
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    freshOranges++;
                } else if (grid[row][col] == 2) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        int[][] directions = {
            {-1, 0}, // alto
            {1, 0},  // basso
            {0, -1}, // sinistra
            {0, 1}   // destra
        };

        int minutes = 0;

        // Un'iterazione del while = un livello BFS = un minuto
        while (!queue.isEmpty() && freshOranges > 0) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    boolean insideGrid =
                        newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols;

                    if (insideGrid && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        freshOranges--;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            minutes++;
        }

        return freshOranges == 0 ? minutes : -1;
    
    }
}