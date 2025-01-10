package booleanNthOrder;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class BooleanApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите множество чисел через запятую (Пример ввода: 1, 2, 3):");
        String inputString = scanner.nextLine();
        List<Double> input = new ArrayList<>(List.of());
        for(String s : inputString.split(", ")){
            input.add(Double.valueOf(s));
        }
        System.out.println("Введите порядок искомого булеана:");
        int n = scanner.nextInt();
        int count = 0;
        for(Object subset: BooleanSubsets.booleanSubsetsNthOrder(input, n)){
            try {
                System.out.println(subset.toString().substring(1, subset.toString().length() - 1));
            }catch (StringIndexOutOfBoundsException e){
                System.out.println(subset);
            }
            count += 1;
        }
        System.out.println(count);
    }
}
