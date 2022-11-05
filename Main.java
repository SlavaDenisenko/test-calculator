import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите математическую операцию, состоящую из двух операндов и одного оператора (+, -, *, /):");
        Scanner in = new Scanner(System.in);
        System.out.println(Main.calc(in.nextLine()));
    }
    public static String calc (String Input) {
        int a, b, result;
        char operation = ' ';
        char[] massive = Input.toCharArray();
        for (int i = 0; i < Input.length(); i++) {
            massive[i] = Input.charAt(i);
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
        if(wow.length > 2){
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if(isRoman(first) && isRoman(s)){
            a = romanToNum(first);
            b = romanToNum(s);
            result = calculated(a, b,operation);
            String resultRoman = numToRoman(result);
            System.out.println("Решение:");

            if(result < 0) {
                throw new IllegalArgumentException("Результат операции ниже нуля");
            } return numToRoman(result);
        } else if (isInteger(first) && isInteger(s)) {
            result = calculated(Integer.parseInt(first), Integer.parseInt(s), operation);
            System.out.println("Решение:");
        } else {
            throw new IllegalArgumentException("Операции допустимы только со значениями одинаковой системы счисления");
        }
        return String.valueOf(result);
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