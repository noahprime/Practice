import java.math.BigInteger;

public class SumPrimes {
    public static void main(String[] args){
        System.out.println(findPrime());
    }

    public static String findPrime(){
        int prime = 2;
        BigInteger sum = BigInteger.valueOf(0);
        while(prime < 2000000){
            if(isPrime(prime)){
                sum = sum.add(BigInteger.valueOf(prime));
            }
            if(prime == 2){
                prime += 1;
            }
            else{
                prime += 2;
            }
        }
        String sumString = sum.toString();
        return sumString;
    }

    public static boolean isPrime(int n) {
        // "" "Returns True if n is prime." ""
        if(n == 2) {
            return true;
        }
        if(n == 3) {
            return true;
        }
        if(n % 2 == 0) {
            return false;
        }
        if(n % 3 == 0) {
            return false;
        }

        int i = 5;
        int w = 2;

        while(i * i <= n) {
            if(n % i == 0) {
                return false;
            }

            i += w;
            w = 6 - w;
        }

        return true;
    }
}
