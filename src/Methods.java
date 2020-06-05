public class Methods {
    public static void main (String[] args){
        int a = getSum(2, 3 );
        int b = getSum(5, 6);// можно указать числами и не писать x , y
        System.out.println(a);
        System.out.println(b);
    }
    static int getSum(int x, int y){
        return x + y;
    }
}
