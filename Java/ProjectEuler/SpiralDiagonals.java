import java.math.BigInteger;

public class SpiralDiagonals{

  public static void main(String[] args){
    int n = Integer.parseInt(args[0]);
    System.out.println(findSum(n).toString());
  }

  public static BigInteger findSum(int n){
    BigInteger sum = BigInteger.ONE;
    BigInteger upRight = BigInteger.valueOf(1);
    BigInteger upLeft = BigInteger.valueOf(0);
    BigInteger downLeft = BigInteger.valueOf(0);
    BigInteger downRight = BigInteger.valueOf(0);
    for(int i = 3;i <= n;i += 2){
      int dif = 2*i + 2*(i-2);
      BigInteger sub = BigInteger.valueOf(i-1);
      upRight = upRight.add(BigInteger.valueOf(dif));
      upLeft = upRight.subtract(sub);
      downLeft = upLeft.subtract(sub);
      downRight = downLeft.subtract(sub);
      sum = sum.add(upRight).add(upLeft).add(downLeft).add(downRight);
    }
    return sum;
  }
}
