import java.math.BigInteger;
import java.util.HashMap;

public class NonAbundantSums {
  static HashMap abundantNums;

  public static void main(String[] args){
    abundantNums = new HashMap();
    fillMap(abundantNums);
    System.out.println("# of abundant numbers less than 28,123 = " + abundantNums.size());
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

  public static void fillMap(HashMap map){
    for(int i = 1;i < 28123;i++){
      if(findSumDivisors(i) > i) {
        abundantNums.put(BigInteger.valueOf(i),BigInteger.valueOf(i));
      }
    }
  }

}
