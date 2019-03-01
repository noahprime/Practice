import java.math.BigInteger;

public class MaximumPathSum {
    public static void main(String[] args){
        System.out.println(findSum());
    }

    public static String findSum(){
        BigInteger[][] tri = new BigInteger[15][15];
        String str = "75 95 64 17 47 82 18 35 87 10 20 04 82 47 65 19 01 23 75 03 34 88 02 77 73 07 63 67 99 65 04 28 06 16 70 92 41 41 26 56 83 40 80 70 33 41 48 72 33 47 32 37 16 94 29 53 71 44 65 25 43 91 52 97 51 14 70 11 33 28 77 73 17 78 39 68 17 57 91 71 52 38 17 14 91 43 58 50 27 29 48 63 66 04 68 89 53 67 30 73 16 69 87 40 31 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
        String[] temp = str.split(" ");
        int count = 0;
        for(int i = 0;i<15;i++){
            for(int j = 0; j < i+1;j++){
                tri[i][j] = new BigInteger(temp[count]);
                count++;
            }
        }

        for(int k = 0;k<15;k++){
            for(int l = 0;l<15;l++){
                if(tri[k][l] != null) {
                    System.out.print(tri[k][l] + " ");
                }
            }
            System.out.println(" ");
        }

        for(int m = 13;m>=0;m--){
            for(int n = 0;n<=m;n++){
                if(tri[m+1][n].compareTo(tri[m+1][n+1]) > 0){
                    tri[m][n] = tri[m][n].add(tri[m+1][n]);
                }
                else{
                    tri[m][n] = tri[m][n].add(tri[m+1][n+1]);
                }
            }
        }

        System.out.println(" ");
        System.out.println(" ");


        String sum = tri[0][0].toString();

        return sum;
    }
}
