public class HomeWork24 {
    public static void main(String[] args) {
        int[][] table = new int[5][5];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (i == j)
                    table[i][j] = 1;
                System.out.println(table[i][j]);
            }
        }
    }
}
// Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
//      и с помощью цикла(-ов) заполнить его диагональные элементы единицами;