class Solution {
    private static final int[][] directions = {{1, 0}, {-1, 0},
                                                 {0, 1}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int ROWS = grid.length, COLS = grid[0].length;
        int maxArea = 0;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 1) {
                    int area = dfs(grid, r, c);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length ||
            c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        grid[r][c] = 0; // marca come visitata
        int area = 1;   // conta se stessa

        for (int[] dir : directions) {
            area += dfs(grid, r + dir[0], c + dir[1]);
        }

        return area;
    }
}