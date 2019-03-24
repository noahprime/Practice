import java.util.ArrayList;
import java.math.BigInteger;

public class DistinctPowers{

  public static void main(String[] args){
    int aMax = Integer.parseInt(args[0]);
    int bMax = Integer.parseInt(args[1]);
    System.out.println("aMax = " + aMax + ", bMax = " + bMax);
    System.out.println(numDistinct(aMax,bMax));
  }

  public static int numDistinct(int aMax,int bMax){
    ArrayList<BigInteger> pows = new ArrayList<BigInteger>();
    BigInteger a;
    BigInteger result;
    for(int i = 2;i <= aMax;i++){
      for(int b = 2;b <= bMax;b++){
        a = BigInteger.valueOf(i);
        result = a.pow(b);
        if(!pows.contains(result)){
          pows.add(result);
        }
      }
    }
    return pows.size();
  }
}
