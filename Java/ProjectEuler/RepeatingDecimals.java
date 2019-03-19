import java.util.ArrayList;

public class RepeatingDecimals{
  static int d;
  public static void main(String[] args){
    d = Integer.parseInt(args[0]);
    System.out.println("Fractions with denominators less than " + d);
    findMax(d);
  }

  public static void findMax(int denMax){
    int max = 0;
    int num = 0;
    for(int i = 1;i < denMax;i++){
      int len = findLength(i);
      if(len > max){
        max = len;
        num = i;
      }
    }
    System.out.println("Denomenator producing longest recurring cycle = " + num);
    System.out.println("Length: " + max);
  }

  public static int findLength(int den){
    ArrayList<Integer> nums = new ArrayList<Integer>();
    ArrayList<Integer> decimal = new ArrayList<Integer>();
    int num = 1;
    int index = 0;
    nums.add((Integer)num);
    int count;
    while(num != 0){
      count = 0;
      while(den <= num){
        num -= den;
        count++;
      }
      decimal.add((Integer)count);
      num *= 10;
      if(nums.contains((Integer)num)){
        index = nums.indexOf((Integer)num);
        break;
      }
      nums.add(num);
    }
    return decimal.size() - index;
  }
}
