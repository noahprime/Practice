import java.util.ArrayList;

public class LexPerms {
  static int[] arr;

  public static void main(String[] args){
    arr = new int[] {0,1,2,3,4,5,6,7,8,9};
    for(int i = 0;i < 999999;i++){
      permute();
    }
    for(int j = 0;j < arr.length;j++){
      System.out.print(arr[j]);
    }
    System.out.println();
  }

  public static void permute() {
    int indexToPut = -1;
    int minOption = 10;
    int indexToMove = arr.length;
    for(int i = arr.length-1;i > -1;i--){
      if(indexToPut < i){
        for(int j = i-1;j > -1;j--){
          if(arr[i] > arr[j]){
            if(j > indexToPut){
              indexToPut = j;
              minOption = arr[i];
              indexToMove = i;
              break;
            }
            else if(j == indexToPut){
              if(arr[i] < minOption){
                minOption = arr[i];
                indexToMove = i;
                break;
              }
            }
          }
        }
      }
    }
    if(indexToMove != arr.length){
      int temp = arr[indexToPut];
      arr[indexToPut] = arr[indexToMove];
      arr[indexToMove] = temp;
      for(int k = indexToPut+1;k < arr.length-1;k++){
        for(int l = k+1;l < arr.length;l++){
          if(arr[l] < arr[k]){
            int temp2 = arr[l];
            arr[l] = arr[k];
            arr[k] = temp2;
          }
        }
      }
    }
  }


}
