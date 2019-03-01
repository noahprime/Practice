public class DivisBy20Down {
    public static void main(String[] args){
        System.out.println(findSmallest());
    }

    public static int findSmallest(){
        int[] nonPrimes = {20,19,18,17,16,15,14,13,12,11};
        boolean isTrue = true;
        int i = 2520;
        int smallest = 0;
        while(isTrue){
            for(int x:nonPrimes){
                if(i%x != 0){
                    smallest = 0;
                    break;
                }
                else{
                    smallest = i;
                }
            }
            if(smallest != 0){
                return smallest;
            }
            else{
                i++;
            }
        }
        return 0;
    }
}
