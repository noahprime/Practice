import java.math.BigInteger;
import java.util.ArrayList;

public class FifthPowerSums{

  //==========================================================================================

  public static void main(String[] args){
    int power = Integer.parseInt(args[0]);
    int digits = findMaxDigits(power);
    System.out.println("Max Digits = " + digits);
    BigInteger max = findMax(digits);
    checkNums(max,power);
  }

  //==========================================================================================

  public static int findMaxDigits(int pow){
    int digits = 1;
    while(true){
      int result = digits * (int)java.lang.Math.pow(9,pow);
      String resultS = Integer.toString(result);
      if(resultS.length() < digits){
        break;
      }
      digits++;
    }
    return digits-1;
  }

  //==========================================================================================

  public static BigInteger findMax(int digits){
    String max = "1";
    for(int i = 0;i < digits;i++){
      max = max + "0";
    }
    BigInteger result = new BigInteger(max);
    return result;
  }

  //==========================================================================================

  public static void checkNums(BigInteger max,int pow){
    ArrayList<BigInteger> arr = new ArrayList<BigInteger>();

    for(BigInteger i = BigInteger.valueOf(2);i.compareTo(max) < 0;i = i.add(BigInteger.ONE)){
      String num = i.toString();
      BigInteger result = BigInteger.ZERO;
      for(char c:num.toCharArray()){
        String digit = Character.toString(c);
        BigInteger bigDigit = new BigInteger(digit);
        result = result.add(bigDigit.pow(pow));
      }
      System.out.println(i.toString() + ": " + result.toString());
      if(result.compareTo(i) == 0){
        arr.add(i);
      }
    }

    BigInteger sum = BigInteger.ZERO;
    for(int j = 0;j < arr.size();j++){
      System.out.println(arr.get(j).toString());
      sum = sum.add(arr.get(j));
    }
    System.out.println("Sum = " + sum.toString());
  }

  //==========================================================================================

}
