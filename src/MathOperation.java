public class MathOperation {
    public static void main (String[] args){
        int a = 5;
        int b = 3;

        System.out.println(a % b);// остаток от деления помогает понять четное или нет число если делится на 2 то четное
        System.out.println(a++); // увеличивает на единицу
        System.out.println(++a); // увеличивает на два фактчески
        System.out.println(a--); // уменьшается
        System.out.println(--a);
       // System.out.println(a+=);// далее идут функции с присваиванием
        //System.out.println(a-=);
       // System.out.println(a*=);
       // System.out.println(a/=);
      //  System.out.println(a%=);
//пример

        a += 3;
        System.out.println(a); // получаем 8 , так как сама а уже равна 5 и к нему добавляет 3 и сразу выводят на экран
    }

}
