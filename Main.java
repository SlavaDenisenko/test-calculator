import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static int a, b, result;
    static char operation;

    public static void main(String[] args) {
        System.out.println("Введите математическую операцию, состоящую из двух операндов и одного оператора (+, -, *, /):");
        String userInput = in.nextLine();
        char[] massive = userInput.toCharArray();
        for (int i = 0; i < userInput.length(); i++) {
            massive[i] = userInput.charAt(i);
            if (massive[i] == '+') {
                operation = '+';
            }
            if (massive[i] == '-') {
                operation = '-';
            }
            if (massive[i] == '*') {
                operation = '*';
            }
            if (massive[i] == '/') {
                operation = '/';
            }
        }
        String under_char = String.valueOf(massive);
        String[] wow = under_char.split("[-+/*]");
        String first = wow[0];
        String second = wow[1];
        String s = second.trim();
        if(isRoman(first) && isRoman(s)){
            a = romanToNum(first);
            b = romanToNum(s);
            result = calculated(a, b,operation);

            if(result < 0) {
                throw new IllegalArgumentException("Результат операции ниже нуля");
            } else {
                String resultRoman = numToRoman(result);
                System.out.println("Решение:");
                System.out.println(resultRoman);
            }
        } else if (isInteger(first) && isInteger(s)) {
            result = calculated(Integer.parseInt(first), Integer.parseInt(s), operation);
            System.out.println("Решение:");
            System.out.println(result);
        } else {
            throw new IllegalArgumentException("Операции допустимы только со значениями одинаковой системы счисления");
        }
    }
    private static final int[] arabian = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    private static int romanToNum (String numRoman) {
        int r = 0;
        for (int i = 0; i < arabian.length; i++) {
            while (numRoman.indexOf(roman[i]) == 0) {
                r += arabian[i];
                numRoman = numRoman.substring(roman[i].length());
            }
        } return r;
    }
    private static String numToRoman (int numArab) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roman.length; i++) {
            while (numArab >= arabian[i]) {
                sb.append(roman[i]);
                numArab -= arabian[i];
            }
        } return sb.toString();
    }
    private static boolean isRoman (String under_char) {
        String[] romanNum = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        for (String keys : romanNum) {
            if(Objects.equals(under_char, keys)){
                return true;
            }
        } return false;
    }
    private static boolean isInteger (String under_char) {
        try {
            int isInt = Integer.parseInt(under_char);
            if(isInt<0 || isInt>10) {
                throw new IllegalArgumentException("Значение "+under_char+" вне диапазона 0-10");
            } return true;
            } catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return false;
    }
    private static int calculated (int a, int b, char operation){
        int rez = switch (operation) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> throw new IllegalArgumentException("Неверный знак операции над числами");
        };
        return rez;
    }
}