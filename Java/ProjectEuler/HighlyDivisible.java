import java.math.BigInteger;
import java.util.ArrayList;

public class HighlyDivisible {
    public static void main(String[] args){
        System.out.println(findNum());
    }

    public static String findNum(){
        BigInteger sum = BigInteger.valueOf(45);
        int count = 10;
        while(true){
            sum = sum.add(BigInteger.valueOf(count));
            if(divisibleByFiveHundred(sum)){
                String numString = sum.toString();
                return numString;
            }

            count++;
        }
    }

    public static ArrayList<BigInteger> tdFactors(BigInteger n)
    {
        BigInteger two = BigInteger.valueOf(2);
        ArrayList<BigInteger> fs = new ArrayList<>();

        if (n.compareTo(two) < 0)
        {
            throw new IllegalArgumentException("must be greater than one");
        }

        while (n.mod(two).equals(BigInteger.ZERO))
        {
            fs.add(two);
            n = n.divide(two);
        }

        if (n.compareTo(BigInteger.ONE) > 0)
        {
            BigInteger f = BigInteger.valueOf(3);
            while (f.multiply(f).compareTo(n) <= 0)
            {
                if (n.mod(f).equals(BigInteger.ZERO))
                {
                    fs.add(f);
                    n = n.divide(f);
                }
                else
                {
                    f = f.add(two);
                }
            }
            fs.add(n);
        }

        return fs;
    }

    public static boolean divisibleByFiveHundred(BigInteger n){
        ArrayList<BigInteger> primeFactors = tdFactors(n);

        BigInteger compare = BigInteger.valueOf(2);
        int product = 1;
        while(!primeFactors.isEmpty()){
            int pow = 0;
            while(!primeFactors.isEmpty() && compare.compareTo(primeFactors.get(0)) == 0){
                primeFactors.remove(0);
                pow++;
            }
            compare = compare.add(BigInteger.valueOf(1));
            product = product * (pow + 1);
        }

        if(product > 500){
            return true;
        }

        return false;
    }
}
