import java.math.BigInteger;

public class SumFactDigits {
    public static void main(String[] args){
        String num = findNum(15496).toString();
        System.out.println(num);
        System.out.println("================================");
        System.out.println(findSum(num));
    }

    public static BigInteger findNum(int n){
        if(n == 1){
            return BigInteger.valueOf(1);
        }
        else{
            return BigInteger.valueOf(n).multiply(findNum(n-1));
        }
    }

    public static int findSum(String num){
        int sum = 0;
        for(int i = 0;i < num.length()-1;i++){
            sum += Integer.parseInt(Character.toString(num.charAt(i)));
        }

        return sum;
    }
}
