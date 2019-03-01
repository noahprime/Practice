import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        System.out.println(findSum(1000));
    }

    public static int findSum(int n){
        int sum = 0;
        for(int i = 1;i<n;i++){
            if(i%3 == 0 || i%5==0){
                sum = sum + i;
            }
        }
        return sum;
    }
}
