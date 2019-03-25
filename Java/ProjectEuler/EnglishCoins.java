

public class EnglishCoins{

  public static void main(String[] args){
    int pounds = Integer.parseInt(args[0]);
    int[] maxes = findMaxes(pounds);
    findAllWays(maxes,pounds);
  }

  public static int[] findMaxes(int pnds){
    int[] maxes = new int[8];
    maxes[0] = pnds * 100;
    maxes[1] = pnds * 50;
    maxes[2] = pnds * 20;
    maxes[3] = pnds * 10;
    maxes[4] = pnds * 5;
    maxes[5] = pnds * 2;
    maxes[6] = pnds;
    maxes[7] = pnds/2;
    return maxes;
  }

  public static void findAllWays(int[] maxes,int pounds){
    int total = 0;
    int count = 0;
    for(int one = 0;one <= maxes[0];one++){
      for(int two = 0;two <= maxes[1];two++){
        for(int five = 0;five <= maxes[2];five++){
          for(int ten = 0;ten <= maxes[3];ten++){
            for(int twenty = 0;twenty <= maxes[4];twenty++){
              for(int fiftey = 0;fiftey <= maxes[5];fiftey++){
                for(int hundred = 0;hundred <= maxes[6];hundred++){
                  for(int twoHundred = 0;twoHundred <= maxes[7];twoHundred++){
                    total = one + 2*two + 5*five + 10*ten + 20*twenty + 50*fiftey + 100*hundred + 200*twoHundred;
                    if(total == 100*pounds){
                      count++;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    System.out.println(count + " many ways");
  }

}
