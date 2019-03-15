import java.math.BigInteger;
import java.util.HashMap;
import java.util.ArrayList;

public class NonAbundantSums {
  static HashMap<BigInteger,BigInteger> unwanted;
  static ArrayList<BigInteger> abundantNums;

  public static void main(String[] args){
    unwanted = new HashMap<BigInteger,BigInteger>();
    abundantNums = new ArrayList<BigInteger>();
    fillArr();
    findUnwanted();
    System.out.println("Sum of non-abundant numbers = " + sumWanted().toString());
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

  public static void fillArr(){
    for(int i = 1;i < 28123;i++){
      if(findSumDivisors(i) > i) {
        abundantNums.add(BigInteger.valueOf(i));
      }
    }
  }

  public static void findUnwanted() {
    for(int i = 0;i < abundantNums.size();i++) {
      for(int j = 0;j < abundantNums.size();j++) {
        BigInteger sum = abundantNums.get(i).add(abundantNums.get(j));
        if(!unwanted.containsKey(sum)) {
          unwanted.put(sum,sum);
        }
      }
    }
  }

  public static BigInteger sumWanted() {
    BigInteger sum = BigInteger.valueOf(0);
    for(int i = 1; i < 28123;i++) {
      if(!unwanted.containsKey(BigInteger.valueOf(i))) {
        sum = sum.add(BigInteger.valueOf(i));
      }
    }
    return sum;
  }

}
