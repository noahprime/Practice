public class TenKandOnePrime {
    public static void main(String[] args){
        System.out.println(findPrime());
    }

    public static int findPrime(){
        int prime = 2;
        int count = 0;
        while(true){
            if(isPrime(prime)){
                count++;
                if(count == 10001){
                    return prime;
                }
            }
            if(prime == 2){
                prime += 1;
            }
            else{
                prime += 2;
            }
        }
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
