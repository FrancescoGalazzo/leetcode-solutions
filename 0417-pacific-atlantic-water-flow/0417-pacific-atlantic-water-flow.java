class Solution {
    private int m, n;
    private int[][] heights;
    private int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        m = heights.length;
        n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int c = 0; c < n; c++) {
            dfs(0, c, pacific, Integer.MIN_VALUE);
            dfs(m-1, c, atlantic, Integer.MIN_VALUE);
        }
        for (int r = 0; r < m; r++) {
            dfs(r, 0, pacific, Integer.MIN_VALUE);
            dfs(r, n-1, atlantic, Integer.MIN_VALUE);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                if (pacific[r][c] && atlantic[r][c])
                    result.add(Arrays.asList(r, c));
        return result;
    }

    private void dfs(int r, int c, boolean[][] visited, int prevHeight) {
        if (r < 0 || r >= m || c < 0 || c >= n) return;
        if (visited[r][c]) return;
        if (heights[r][c] < prevHeight) return;

        visited[r][c] = true;
        for (int[] d : dirs) {
            dfs(r + d[0], c + d[1], visited, heights[r][c]);
        }
    }
}