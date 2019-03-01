import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class LongestCollatz {
    public static void main(String[] args){
        System.out.println(findNum());
    }

    public static int findNum(){
        int longest = 0;
        int largest = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Array> arr = new ArrayList<>();
        for(int i = 1;i < 1000000;i++){
            int count = 1;
            BigInteger seq = BigInteger.valueOf(i);
            while(seq.compareTo(BigInteger.valueOf(1)) != 0){
                if(seq.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0)) == 0){
                    seq = seq.divide(BigInteger.valueOf(2));
                    count++;
                    if(map.containsKey(seq)){
                        count = count + map.get(seq);
                        break;
                    }
                }
                else{
                    seq = seq.multiply(BigInteger.valueOf(3)).add(BigInteger.valueOf(1));
                    count++;
                    if(map.containsKey(seq)){
                        count = count + map.get(seq);
                        break;
                    }
                }
            }
            map.put(i,count);
            if(count > longest){
                longest = count;
                largest = i;
            }
        }

        return largest;
    }
}
