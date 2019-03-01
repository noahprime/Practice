import java.util.ArrayList;
import java.util.LinkedList;

public class PalindromeNumbers {
    public static void main(String[] args){
        System.out.println(findLargestPal());
    }

    public static Integer findLargestPal(){
        int num = 13;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 100;i<1000;i++){
            for(int j = 100;j<1000;j++){
                num = i*j;
                String numString = Integer.toString(num);

                String reverse = "";
                for(int k = numString.length()-1;k>=0;k--){
                    reverse = reverse + numString.charAt(k);
                }
                if(reverse.equals(numString)) {
                    list.add(Integer.parseInt(numString));
                }
            }
        }
        Integer largest = 0;
        for(Integer x:list){
            if(x > largest){
                largest = x;
            }
        }
        return largest;
    }
}
