public class HomeWork8 {
    public static void main(String[] args) {
        int year = 2019;
        if ((((year % 4 == 0) &&!(year % 100 == 0))|| (year % 400 == 0))){
            System.out.println(year + " високосный год");
        } else {
            System.out.println(year + " не високосный год");

        }
    }
}
