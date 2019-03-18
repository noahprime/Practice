import java.math.BigInteger;

public class ThreeDigitFib{
  static int digits;

  public static void main(String[] args){
    digits = (int)Integer.parseInt(args[0]);
    System.out.println("Digits: " + digits);
    System.out.println("Index of first " + digits + " digit Fibonacci number = " + findIndex(digits));
  }

  static int findIndex(int n){
    int index = 2;
    BigInteger f1 = BigInteger.valueOf(1);
    BigInteger f2 = BigInteger.valueOf(1);
    BigInteger temp;
    while(numDigits(f1.add(f2)) < digits){
      temp = f2;
      f2 = f1.add(f2);
      f1 = temp;
      index++;
    }
    return index + 1;
  }

  static int numDigits(BigInteger num){
    String numString = num.toString();
    return numString.length();
  }
}
