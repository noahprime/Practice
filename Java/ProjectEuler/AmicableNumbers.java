public class AmicableNumbers {
    public static void main(String[] args){
        int[][] arr = new int[1000][2];
        fillArr(arr);

    }

    public static void fillArr(int[][] arr){
        for(int i = 1;i < 1001;i++){
            arr[i-1][0] = i;
            arr[i-1][1] = findSumDivisors(i);
        }
    }

    public static int findSumDivisors(int n){

        return 0;
    }

    public static void sumAmicable(){

    }
}
