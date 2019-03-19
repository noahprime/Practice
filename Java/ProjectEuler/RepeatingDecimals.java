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
    int num = 1;
    for(int i = 1;i < denMax;i++){
      int len = findLength(i);
      if(len > max){
        max = len;
        num = i;
      }
    }
    printDecimal(num);
    System.out.println("Denomenator producing longest recurring cycle = " + num);
    System.out.println("Length: " + max);
  }

  public static int findLength(int den){
    ArrayList<Integer> nums = new ArrayList<Integer>();
    ArrayList<Integer> decimal = new ArrayList<Integer>();
    boolean repeating = false;
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
        repeating = true;
        break;
      }
      nums.add(num);
    }
    if(repeating == true){
      return decimal.size() - index;
    }
    return 0;
  }

  public static void printDecimal(int den){
    ArrayList<Integer> nums = new ArrayList<Integer>();
    ArrayList<Integer> decimal = new ArrayList<Integer>();
    boolean repeating = false;
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
        repeating = true;
        break;
      }
      nums.add(num);
    }
    System.out.print("Decimal: 0.");
    if(repeating == true){
      for(int i = 1;i < index;i++){
        System.out.print(decimal.get(i));
      }
      System.out.print("(");
      for(int j = index;j < decimal.size();j++){
        System.out.print(decimal.get(j));
      }
      System.out.println(")");
    }
    else{
      for(int i = 1;i < decimal.size();i++){
        System.out.print(decimal.get(i));
      }
      System.out.println();
    }
  }
}
