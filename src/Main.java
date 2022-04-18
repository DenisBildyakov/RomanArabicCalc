import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int op1;
    static int op2;
    static char operation;
    static int result;

    public static void main(String[] args) {
        System.out.println("Введите сумму двух арабских или римских числе и нажмите Enter:");
        String input = scanner.nextLine();
        char[] input_line = new char[12];

        for (int i = 0; i < input.length(); i++) {
            input_line[i] = input.charAt(i);
            if (input_line[i] == '+') {
                operation = '+';
            }
            if (input_line[i] == '-') {
                operation = '-';
            }
            if (input_line[i] == '*') {
                operation = '*';
            }
            if (input_line[i] == '/') {
                operation = '/';
            }
        }
        String under_charString = String.valueOf(input_line);
        String[] blacks = under_charString.split("[+-/*]");
        String stable00 = blacks[0];
        String stable01 = blacks[1];
        String string03 = stable01.trim();
        op1 = romanToNumber(stable00);
        op2 = romanToNumber(string03);
        if (op1 < 0 && op2 < 0) {
            result = 0;
        } else {
            result = calculated(op1, op2, operation);
            System.out.println("---Результат для римских цифр----");
            String resultRoman = convertNumToRoman(result);
            System.out.println(stable00 + " " + operation + " " + string03 + " = " + resultRoman);
        }
        op1 = Integer.parseInt(stable00);
        op2 = Integer.parseInt(string03);
        result = calculated(op1, op2, operation);
        System.out.println("--Результат для арабских цифр----");
        System.out.println(op1 + " " + operation + " " + op2 + " = " + result);
    }

        public static String convertNumToRoman (int numArabian) {
            String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                    "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                    "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                    "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                    "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                    "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                    "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
            };
            String s = roman[numArabian];
            return s;
        }


        public static int romanToNumber (String roman) {
            try {
                if (roman.equalsIgnoreCase("I")) {
                    return 1;
                } else if (roman.equalsIgnoreCase("II")) {
                    return 2;
                } else if (roman.equalsIgnoreCase("III")) {
                    return 3;
                } else if (roman.equalsIgnoreCase("IV")) {
                    return 4;
                } else if (roman.equalsIgnoreCase("V")) {
                    return 5;
                } else if (roman.equalsIgnoreCase("VI")) {
                    return 6;
                } else if (roman.equalsIgnoreCase("VII")) {
                    return 7;
                } else if (roman.equalsIgnoreCase("VIII")) {
                    return 8;
                } else if (roman.equalsIgnoreCase("IX")) {
                    return 9;
                } else if (roman.equalsIgnoreCase("X")) {
                    return 10;
                }
            } catch (InputMismatchException e) {
                throw new InputMismatchException("Неверный формат данных");
            }
            return -1;
        }

    public static int calculated (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный знак операции");
        }
        return result;
    }
}
