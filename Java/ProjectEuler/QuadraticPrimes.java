public class QuadraticPrimes{
  static int finalA;
  static int finalB;
  static int len;
  static int absA;
  static  int absB;

  public static void main(String[] args){
    absA = Integer.parseInt(args[0]);
    absB = Integer.parseInt(args[1]);
    findMax();
  }

  public static void findMax(){
    int count = 0;
    int bestLength = 0;
    int aBest = -absA;
    int bBest = -absB;
    int n = 0;
    int p = 0;
    boolean cont = true;
    for(int a = -absA+1;a < absA;a++){
      for(int b = -absB;b < absB+1;b++){
        n = 0;
        count = 0;
        cont = true;
        while(cont){
          p = n*n + a*n + b;
          if(isPrime(p)){
            n++;
            count++;
            if(count > bestLength){
              bestLength = count;
              aBest = a;
              bBest = b;
            }
          }
          else{
            cont = false;
          }
        }
      }
    }
    finalA = aBest;
    finalB = bBest;
    len = bestLength;
    printPrimes();
    System.out.println();
    System.out.println("a = " + aBest);
    System.out.println("b = " + bBest);
    System.out.println("a*b = " + aBest*bBest);
    System.out.println("0<=n<=" + (bestLength-1));
  }

  public static boolean isPrime(int n){
    if(n < 2){
      return false;
    }
    if(n == 2) {
        return true;
    }
    if(n == 3) {
        return true;
    }
    if(n % 2 == 0) {
        return false;
    }
    if(n % 3 == 0) {
        return false;
    }

    int i = 5;
    int w = 2;

    while(i * i <= n){
      if(n % i == 0){
        return false;
      }
      i += w;
      w = 6 - w;
    }

    return true;
  }

  public static void printPrimes(){
    for(int n = 0;n < len;n++){
      System.out.print(n*n+finalA*n+finalB + " ");
    }
  }

}
