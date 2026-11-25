class Solution {
    public void setZeroes(int[][] matrix) {
        
        if(matrix == null || matrix.length == 0 || matrix[0].length==0){
            return;
        }

        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();
        
    
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j]==0){
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
    
        for(Integer r : rows){
            for(int j=0; j< matrix[0].length; j++)
                matrix[r][j] = 0;
        }

        for(Integer c : columns){
            for(int i=0; i< matrix.length; i++)
                matrix[i][c] = 0;
        }
    }
}