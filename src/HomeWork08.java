public class HomeWork08 {
public static void main (String[] args){
int month = 2;
 int year = 2000;
 int numDays;
 switch (month){
     case 1:
     case 3:
     case 5:
     case 7:
     case 8:
     case 10:
     case 12:
         numDays = 31;
         break;
     case 4:
     case 6:
     case 9:
     case 11:
         numDays = 30;
         break;
     case 2:
         if (((year % 4 == 0) &&!(year % 100 == 0))|| (year % 400 == 0))
         numDays = 29;
         else
             numDays = 28;
         break;
     default:
         numDays = 0;
         System.err.println("несуществующий месяц");
         break;
 }
System.out.println("в месяце " + numDays + " дней");
}


}
//Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год
//   является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.