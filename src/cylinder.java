public class cylinder {
    public static void main (String[] args){
        float volume; // объявление переменной
        float radius = 2.0f, height = 10.0f;
        // volume инициируется динамически во время выполнения программы
        volume = 3.1416f * radius * radius * height;
        System.out.println("Объем цилиндра равен " + volume);
    }
}
