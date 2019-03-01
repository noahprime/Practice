public class SumSquares {
    public static void main(String[] args){
        System.out.println("Diff: " + findDifference());
    }

    public static int findDifference(){
        int squareSum = 0;
        int sum = 0;
        int sumSquared = 0;
        for(int i = 1;i <=100;i++){
            sum = sum + i;
            squareSum = squareSum + i*i;
        }
        sumSquared = sum*sum;

        System.out.println("squareSum: " + squareSum);
        System.out.println("sumSquared: " + sumSquared);

        return sumSquared-squareSum;
    }
}
