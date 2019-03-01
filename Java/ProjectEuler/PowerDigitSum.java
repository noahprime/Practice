import java.math.BigInteger;

public class PowerDigitSum {
    public static void main(String[] args){
        findSum();
    }

    public static void findSum(){
        BigInteger two = BigInteger.valueOf(2);
        two = two.pow(1000);
        String twoString = two.toString();
        Integer sum = 0;
        for(int i = 0;i < twoString.length();i++){
            sum = sum + Integer.parseInt(Character.toString(twoString.charAt(i)));
        }
        System.out.println(sum);
    }
}
