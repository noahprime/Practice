import java.math.BigInteger;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class NameScore {
  static String[] names;

  public static void main(String[] args) {
    readFile();
    sort(0,names.length-1);
    System.out.println("Total name Score = " + findSum().toString());
  }

  static void readFile() {
    File file = new File("names.txt");
    try{
      Scanner sc = new Scanner (file);
      while(sc.hasNextLine()) {
        names = sc.nextLine().split(",");
      }
    }catch(FileNotFoundException e) {
      System.out.println("File not found");
      System.exit(0);
    }
  }

  static void sort(int low, int high) {
    if(low < high){
      int p = partition(low, high);
      sort(low, p - 1);
      sort(p + 1, high);
    }
  }

  static int partition(int low, int high){
    String pivot = names[high];
    int i = low;
    String temp;
    for(int j = low; j < high; j++){
      if(names[j].compareTo(pivot) < 0){
        temp = names[i];
        names[i] = names[j];  //swap names[i] and names[j]
        names[j] = temp;
        i = i + 1;
      }
    }
    temp = names[i];
    names[i] = names[high];
    names[high] = temp;
    return i;
  }

  static BigInteger findSum() {
    BigInteger sum = BigInteger.valueOf(0);
    int nameSum;
    char a = 'A';
    for(int i = 0;i < names.length;i++) {
      nameSum = 0;
      for(int j = 1;j < names[i].length()-1;j++) {
        int charVal = ((int)names[i].charAt(j) - (int)a) + 1;
        nameSum += charVal;
      }
      sum = sum.add(BigInteger.valueOf(nameSum*(i+1)));
    }
    return sum;
  }

}
