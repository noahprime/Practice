import java.math.BigInteger;

public class AmicableNumbers {
    public static void main(String[] args){
      int[][] arr = new int[10000][2];
      fillArr(arr);
      System.out.println("The sum of amicable numbers less than 10,000 = " + sumAmicable(arr).toString());
    }

    public static void fillArr(int[][] arr){
      for(int i = 1;i < 10000;i++){
        arr[i-1][0] = i;
        arr[i-1][1] = findSumDivisors(i);
      }
    }

    public static int findSumDivisors(int n){
      int sum = 0;
      for(int i = 1;i < n;i++){
        if(n % i == 0){
          sum += i;
        }
      }
      return sum;
    }

    public static BigInteger sumAmicable(int[][] arr){
      BigInteger sum = BigInteger.valueOf(0);
      for(int i = 2;i < 10000;i++){
        if(arr[i-1][1] < 10000){
          if(i == arr[(arr[i-1][1])-1][1] && i != arr[i-1][1]){
            sum = sum.add(BigInteger.valueOf(i));
          }
        }
      }
      return sum;
    }
}
