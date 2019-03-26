import java.util.ArrayList;
import java.math.BigInteger;

public class PandigitalProducts{

  public static void main(String[] args){
    int n = Integer.parseInt(args[0]);
    findPandigitalProducts(n);
  }

  public static void findPandigitalProducts(int n){
    ArrayList<String> arr = new ArrayList<String>();
    for(int i = 0;i <= 10000;i++){
      for(int j = 0;j <= 10000;j++){
        int prod = i * j;
        String as = Integer.toString(i);
        String bs = Integer.toString(j);
        String cs = Integer.toString(prod);
        String tot = as + bs + cs;
        if(tot.length() > n){
          break;
        }
        if(isPandigital(tot,n) && !arr.contains(cs)){
          arr.add(cs);
          System.out.println(as + " * " + bs + " = " + cs);
        }
      }
    }
    BigInteger sum = BigInteger.ZERO;
    for(String str:arr){
      sum = sum.add(new BigInteger(str));
    }
    System.out.println("Sum = " + sum.toString());
  }

  public static boolean isPandigital(String tot,int n){
    if(tot.length() != n){
      return false;
    }
    ArrayList<Integer> arr = new ArrayList<Integer>();

    for(char c:tot.toCharArray()){
      if(Character.getNumericValue(c) <= n && Character.getNumericValue(c) != 0 && !arr.contains(Character.getNumericValue(c))){
        arr.add(Character.getNumericValue(c));
      }
    }
    if(arr.size() == n){
      return true;
    }
    return false;
  }
}
