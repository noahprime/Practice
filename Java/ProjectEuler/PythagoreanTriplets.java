public class PythagoreanTriplets {
    public static void main(String[] args){
        System.out.println(findProduct());
    }

    public static int findProduct(){
        for(int a = 1;a<1000;a++){
            for(int b = 1;b<1000;b++){
                for(int c = 1;c<1000;c++){
                    if(a*a + b*b == c*c){
                        if(a+b+c == 1000){
                            System.out.println(a + " " + b + " " + c);
                            return a*b*c;
                        }
                    }
                }
            }
        }

        return 0;
    }
}
