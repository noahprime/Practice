import java.util.ArrayList;
import java.math.BigInteger;

public class TotientMax{
//==========================================================================================
  public static void main(String[] args){
    int n = Integer.parseInt(args[0]);
    findMax(n);
  }

  //==========================================================================================

  public static void findMax(int n){
    BigInteger maxNum = BigInteger.ZERO;
    BigInteger maxDen = BigInteger.ONE;
    int bestN = 1;
    for(int i = 2;i <= n;i++){
      BigInteger num = BigInteger.valueOf(i);
      BigInteger den = phi(i);
      BigInteger numTemp = num.multiply(maxDen);
      BigInteger maxNumTemp = maxNum.multiply(den);
      if(numTemp.compareTo(maxNumTemp) > 0){
        maxNum = num;
        maxDen = den;
        bestN = i;
      }
    }
    System.out.println("Best n: " + bestN);
    System.out.println(bestN +"/phi("+ bestN + ") = " + maxNum.toString() + "/" + maxDen.toString());
  }

  //==========================================================================================

  public static BigInteger phi(int n){
    ArrayList<Integer> divisors = findDivisors(n);
    BigInteger num = BigInteger.ONE;
    BigInteger den = BigInteger.ONE;
    for(int i = 0;i < divisors.size();i++){
      int p = divisors.get(i);
      num = num.multiply(BigInteger.valueOf(p-1));
      den = den.multiply(BigInteger.valueOf(p));
    }
    num = num.multiply(BigInteger.valueOf(n));
    return num.divide(den);
  }

  //==========================================================================================

  public static ArrayList<Integer> findDivisors(int n){
    int orig = n;
    ArrayList<Integer> divisors = new ArrayList<Integer>();

    if (n < 2){
      throw new IllegalArgumentException("must be greater than one");
    }
    int count = 0;
    while(n % 2 == 0){
      if(count == 0){
        divisors.add(2);
        count++;
      }
      n /= 2;
    }

    if(n > 1){
      int f = 3;
      while (f*f <= n){
        if (n % f == 0){
          if(!(divisors.contains(f))){
            divisors.add(f);
          }
          n /= f;
        }
        else{
          f += 2;
        }
      }
      if(!(divisors.contains(n))){
        divisors.add(n);
      }
    }
    return divisors;
  }

  //==========================================================================================


}
