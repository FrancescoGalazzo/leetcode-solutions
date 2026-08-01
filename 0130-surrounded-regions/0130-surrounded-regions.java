class Solution {

    private int[][] directions = {{1,0}, {0,1}, {0,-1}, {-1,0}};

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int m = board.length;    // numero di righe
        int n = board[0].length; // numero di colonne

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Controlla se siamo sul bordo
                boolean isBoarder = (i == 0 || i == m - 1 || j == 0 || j == n - 1);
                if (isBoarder && board[i][j]=='O'){
                    dfs(board, i, j);
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';  // regione circondata
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';  // regione sicura: ripristino
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c){
        if(r<0 || c<0 || r>= board.length || c>= board[0].length || board[r][c] != 'O')
            return;
        
        board[r][c] = '#';

        for(int[] dir : directions)
            dfs(board, r+dir[0], c+dir[1]);
    }

}