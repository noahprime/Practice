import java.math.BigInteger;

public class CountPaths {

    public static void main(String[] args){
        System.out.println(countPaths(20,20));
    }

    public static String countPaths(int n,int m){
        BigInteger[][] grid = new BigInteger[n+1][m+1];
        for(int i = 0;i < n+1;i++){
            for(int j = 0;j < m+1;j++){
                if(i == 0 && j == 0){
                    grid[i][j] = BigInteger.valueOf(0);
                }
                else if((j == 1 && i == 0) || (j == 0 && i == 1)){
                    grid[i][j] = BigInteger.valueOf(1);
                }
                else if(j == 0){
                    grid[i][j] = grid[i-1][j];
                }
                else if(i == 0){
                    grid[i][j] = grid[i][j-1];
                }
                else{
                    grid[i][j] = grid[i-1][j].add(grid[i][j-1]);
                }
            }
        }
        return grid[n][m].toString();
    }
}
