import java.util.*;

class Solution {
    private final int[][] DIRS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pacificQueue = new ArrayDeque<>();
        Queue<int[]> atlanticQueue = new ArrayDeque<>();

        for (int c = 0; c < n; c++) {
            add(0, c, pacific, pacificQueue);
            add(m - 1, c, atlantic, atlanticQueue);
        }

        for (int r = 0; r < m; r++) {
            add(r, 0, pacific, pacificQueue);
            add(r, n - 1, atlantic, atlanticQueue);
        }

        bfs(heights, pacificQueue, pacific);
        bfs(heights, atlanticQueue, atlantic);

        List<List<Integer>> result = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void add(
            int r,
            int c,
            boolean[][] visited,
            Queue<int[]> queue
    ) {
        if (!visited[r][c]) {
            visited[r][c] = true;
            queue.offer(new int[]{r, c});
        }
    }

    private void bfs(
            int[][] heights,
            Queue<int[]> queue,
            boolean[][] visited
    ) {
        int m = heights.length;
        int n = heights[0].length;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (heights[nr][nc] < heights[r][c]) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}