import java.math.BigInteger;

public class CountLetter {
    public static void main(String[] args){
        String str = countLetters();
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println(str);
    }

    public static String countLetters(){
        String[] ones = {"","one","two","three","four","five","six","seven","eight","nine"};
        String[] tens = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
        String[] otherTens = {"","","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
        BigInteger sum = BigInteger.valueOf(0);
        for(int i = 1;i < 1001;i++){
            if(Integer.toString(i).length() == 1){
                System.out.println(ones[i]);
                sum = sum.add(BigInteger.valueOf(ones[i].length()));
            }
            else if(Integer.toString(i).length() == 2){
                if(i < 20){
                    int secondDigit = Integer.parseInt(Character.toString(Integer.toString(i).charAt(1)));
                    System.out.println(tens[secondDigit]);
                    sum = sum.add(BigInteger.valueOf(tens[secondDigit].length()));
                }
                else{
                    int firstDigit = Integer.parseInt(Character.toString(Integer.toString(i).charAt(0)));
                    int secondDigit = Integer.parseInt(Character.toString(Integer.toString(i).charAt(1)));
                    System.out.println(otherTens[firstDigit] + ones[secondDigit]);
                    sum = sum.add(BigInteger.valueOf((otherTens[firstDigit] + ones[secondDigit]).length()));
                }
            }
            else if(Integer.toString(i).length() == 3){
                int firstDigit = Integer.parseInt(Character.toString(Integer.toString(i).charAt(0)));
                int secondDigit = Integer.parseInt(Character.toString(Integer.toString(i).charAt(1)));
                int thirdDigit = Integer.parseInt(Character.toString(Integer.toString(i).charAt(2)));
                if(secondDigit > 1) {
                    System.out.println(ones[firstDigit] + "hundredand" + otherTens[secondDigit] + ones[thirdDigit]);
                    sum = sum.add(BigInteger.valueOf((ones[firstDigit] + "hundredand" + otherTens[secondDigit] + ones[thirdDigit]).length()));
                }
                else if(secondDigit > 0){
                    System.out.println(ones[firstDigit] + "hundredand" + tens[thirdDigit]);
                    sum = sum.add(BigInteger.valueOf((ones[firstDigit] + "hundredand" + tens[thirdDigit]).length()));
                }
                else if(thirdDigit > 0){
                    System.out.println(ones[firstDigit] + "hundredand" + ones[thirdDigit]);
                    sum = sum.add(BigInteger.valueOf((ones[firstDigit] + "hundredand" + ones[thirdDigit]).length()));
                }
                else{
                    System.out.println(ones[firstDigit] + "hundred");
                    sum = sum.add(BigInteger.valueOf((ones[firstDigit] + "hundred").length()));
                }
            }
            else{
                System.out.println("onethousand");
                sum = sum.add(BigInteger.valueOf("onethousand".length()));
            }
        }

        String sumString = sum.toString();

        return sumString;
    }
}
