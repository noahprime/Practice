public class Fib {
    public static void main(String[] args){
        System.out.println(findSum());
    }

    public static int findSum(){
        int fib1 = 1;
        int fib2 = 2;
        int holder = 0;
        int sum = 0;

        while(fib1 < 4000000){
            if(fib2 % 2 == 0){
                sum = sum + fib2;
            }
            holder = fib2;
            fib2 = fib1 + fib2;
            fib1 = holder;
            System.out.println(holder);
        }


        return sum;
    }
}
