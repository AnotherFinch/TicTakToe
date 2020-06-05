public class HomeWork26 {
    public static void main(String[] args) {
        int[] $arr = {10,3,2,1,4};
        int[] $toArr = {2,1,5,4,2,2};
        int[] $nextArr = {1,2,4,2,5};
        int[] $toNextArr = {2,1,5,8};
        int[] $falseArr = {1,2,4,5};
        System.out.println(checkBalance($arr));
        System.out.println(checkBalance($toArr));
        System.out.println(checkBalance($nextArr));
        System.out.println(checkBalance($toNextArr));
        System.out.println(checkBalance($falseArr));
    }
    static boolean checkBalance(int[] arr) {
        int $leftArSum;
        int $rigthArSum;
        for (int $i = 0; $i < arr.length - 1; $i++) {
            $leftArSum = 0;
            $rigthArSum = 0;
            for (int $v = 0; $v <= $i; $v++)
                $leftArSum += arr[$v];
            for (int $t = $i + 1; $t < arr.length; $t++)
                $rigthArSum += arr[$t];
            if ($leftArSum == $rigthArSum)
                return true;
        }
        return false;
    }
}

//6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
// если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([1, 1, 1,
// || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) →
// true, граница показана символами ||, эти символы в массив не входят;