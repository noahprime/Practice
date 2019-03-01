import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeFactor {
    public static void main(String[] args){
        String numString = "29183748712349811241";
        BigInteger num = new BigInteger(numString);
        ArrayList<BigInteger> factors = tdFactors(num);
        for(BigInteger x:factors){
            System.out.println(x);
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
}
