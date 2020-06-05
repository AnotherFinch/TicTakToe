import java.util.Random;
import java.util.Scanner;

public class Task1 {
    /*
    блок настроек игры
     */
    private static char[][] map;    // матрица игры
    private static int SIZE = 3; // размерность поля

    private static final char DOT_EMPTY = '.';     // пустой символ-свободное поле
    private static final char DOT_X = 'X';        //  крестик
    private static final char DOT_O = 'O';        //  нолик
    private static final boolean debug = false; // тестирование в консоли

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private static final boolean SILLY_MODE = false;
    public static void main (String[] args ){
        initMap();
        printMap();
        while (true){
            humanTurn();
            if(isEndGame(DOT_X)){
                break;
            }
            computerTurn();
            if(isEndGame(DOT_O)){
                break;
            }
        }
        System.out.println("Игра окончена");
    }

    private static void computerTurn(){
        int x = -1;
        int y = -1;

        if(SILLY_MODE) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }
            while (!isCellValid(x, y));
        }else{
            if (debug) System.out.println("игра умного компьютера");
            int[] res = logik_play();
            x = res[0];
            y = res[1];
        }
        System.out.println("Компьютер выбрал ячейку "+(y+1)+" "+(x+1));
        map[y][x] = DOT_O;
    }

    private static int[] logik_play(){
        int[] result = new int[2];
        int x = -1;
        int y = -1;
        // игра умного компьютера
        // проверяем есть ли возможность выиграть следующим ходом
        int[] res_win_comp = chwckWinNexStep(DOT_O);
        if (debug) System.out.println("chwckWinNexStep(DOT_O) x="+res_win_comp[0]+" y="+res_win_comp[1]);
        if(res_win_comp[0] > -1 && res_win_comp[1]> -1){
            y = res_win_comp[0];
            x = res_win_comp[1];
        }
        if (debug) System.out.println("стр 56 x="+x+" y="+y);
        //если выигрышного хода нет, то
        //надо проверить не выиграет ли человек следующим ходом
        //если да, то надо занять эту ячейку
        if(x == -1) {
            int[] res_win_hum = chwckWinNexStep(DOT_X);
            if (debug) System.out.println("chwckWinNexStep(DOT_X) x="+res_win_hum[0]+" y="+res_win_hum[1]);
            if (res_win_hum[0] > -1 && res_win_hum[1] > -1) {
                y = res_win_hum[0];
                x = res_win_hum[1];
            }
        }
        if (debug) System.out.println("ищем стратегически важные точки x="+x+" y="+y);
        //надо найти стратегически важные точки
        // ищем центр поля
        if(x == -1) {
            int mid = SIZE / 2;
            if (isCellValid(mid, mid)) {
                x = mid;
                y = mid;
            }
        }
        if (debug) System.out.println("результат поиска центра x="+x+" y="+y);
        //ищем свободные угловые точки
        if(x == -1) {
            if(isCellValid(0, SIZE-1)){
                x = 0;
                y = SIZE-1;
            }
        }
        if (debug) System.out.println("первая угловая x="+x+" y="+y);
        if(x == -1) {
            if (isCellValid(0, 0)) {
                x = 0;
                y = 0;
            }
        }
        if (debug) System.out.println("вторая угловая x="+x+" y="+y);
        if(x == -1) {
            if (isCellValid(SIZE-1, 0)) {
                x = SIZE-1;
                y = 0;
            }
        }
        if (debug) System.out.println("третья угловая x="+x+" y="+y);
        if(x == -1) {
            if (isCellValid(SIZE-1, SIZE-1)) {
                x = SIZE-1;
                y = SIZE-1;
            }
        }
        if (debug) System.out.println("четвертая угловая x="+x+" y="+y);
        if(x == -1) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }
            while (!isCellValid(x, y));
            if (debug) System.out.println("ставим рандомом x="+x+" y="+y);
        }

        result[0]=x;
        result[1]=y;
        return result;
    }

    private static int[] chwckWinNexStep(char playerSymbol){
        int[] result = {-1,-1};
        for (int x=0; x < SIZE; x++){
            for (int y=0; y < SIZE; y++){
                if(isCellValid(y, x)){
                    if (debug) System.out.println("Проверка ячеек x="+x+" y="+y);
                    if(checkWinNexStep(playerSymbol, x, y)) {
                        if (debug) System.out.println("checkWin да x=" + x + " y=" + y);
                        result[0] = x;
                        result[1] = y;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    private static void humanTurn(){
        int x, y;
        do{
            System.out.println("Введите координаты через пробел");
            y= scanner.nextInt() - 1;
            x= scanner.nextInt() - 1;
        }while (!isCellValid(x,y));
        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y){
        boolean result = true;
        //проверка координаты
        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE){
            if (debug) System.out.println("isCellValid x < 0 || x >= SIZE || y < 0 || y >= SIZE" + x + " y=" + y+" SIZE="+SIZE);
            return false; //оптимизация: делаем возврат сразу, следующая проверка уже не нужна
        }
        //проверка заполнености
        if(map[y][x] != DOT_EMPTY){
            if (debug) System.out.println("isCellValid map[y][x] != DOT_EMPTY x=" + x + " y=" + y);
            return false;
        }
        return result;
    }

    private static boolean isEndGame(char playerSymbol){
        boolean result = false;

        printMap();

        //проверяем необходимость следующего кода
        if(checkWin(playerSymbol)){
            System.out.println("Победили "+playerSymbol);
            result = true;
        }

        if(isMapFull()){
            System.out.println("Ничья");
            result = true;
        }
        return result;
    }

    private static boolean checkWinNexStep(char playerSymbol, int x, int y){
        boolean result = false;
        char[][] testmap = cloneArray();
        testmap[x][y] = playerSymbol;
        if(
                (testmap[0][0] == playerSymbol && testmap[0][1] == playerSymbol && testmap[0][2] == playerSymbol) ||
                        (testmap[1][0] == playerSymbol && testmap[1][1] == playerSymbol && testmap[1][2] == playerSymbol) ||
                        (testmap[2][0] == playerSymbol && testmap[2][1] == playerSymbol && testmap[2][2] == playerSymbol) ||
                        (testmap[0][0] == playerSymbol && testmap[1][0] == playerSymbol && testmap[2][0] == playerSymbol) ||
                        (testmap[0][1] == playerSymbol && testmap[1][1] == playerSymbol && testmap[2][1] == playerSymbol) ||
                        (testmap[0][2] == playerSymbol && testmap[1][2] == playerSymbol && testmap[2][2] == playerSymbol) ||
                        (testmap[0][0] == playerSymbol && testmap[1][1] == playerSymbol && testmap[2][2] == playerSymbol) ||
                        (testmap[2][0] == playerSymbol && testmap[1][1] == playerSymbol && testmap[0][2] == playerSymbol)
        ){
            result = true;
        }
        return result;
    }

    private static char[][] cloneArray(){
        char[][] res = new char[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++){
            for (int y = 0; y < SIZE; y++){
                res[x][y] = map[x][y];
            }
        }
        return res;
    }

    private static boolean checkWin(char playerSymbol){
        boolean result = false;
        if(
                (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                        (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                        (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                        (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)
        ){
            result = true;
        }
        return result;
    }

    private static boolean isMapFull(){
        boolean result = true;
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(map[i][j] == DOT_EMPTY)
                    return false; //оптимизация: если нашли первую пустую ячейку, то сразу возвращаем  false
            }
        }
        return result;
    }

    private static void initMap(){
        map = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for (int y = 0; y < SIZE; y++){
                map[i][y] = DOT_EMPTY;
            }
        }
    }

    private static void printMap(){
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 0; i < SIZE; i++){
            System.out.print((i+1) + " ");
            for(int j = 0; j < SIZE; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
