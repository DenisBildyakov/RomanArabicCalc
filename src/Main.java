import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int op1;
    static int op2;
    static char operation;
    static int result;
    static int index;

    public static void main(String[] args) {
        // Принимаем строку из консоли
        System.out.println("Введите выражение из двух арабских или двух римских чисел и нажмите Enter:");
        String input = scanner.nextLine();

        // Создаем массив, в который будем помещать символы строки
        char[] input_line = new char[12];

        // Проходим циклом по строке, разбиваем на символы, помещаем их в массив. Опеределяем знак операции, запоминаем его местоположение в строке
        for (int i = 0; i < input.length(); i++) {
            input_line[i] = input.charAt(i);
            if (input_line[i] == '+' && i != 0) {
                operation = '+';
                index = i;
                break;
            }
            if (input_line[i] == '-' && i != 0) {
                operation = '-';
                index = i;
                break;
            }
            if (input_line[i] == '*' && i != 0) {
                operation = '*';
                index = i;
                break;
            }
            if (input_line[i] == '/' && i != 0) {
                operation = '/';
                index = i;
                break;
            }
        }
//        String under_charString = String.valueOf(input_line);
//        String[] blacks = under_charString.split("[+-/*]");
//        String stable00 = blacks[0];
//        String stable01 = blacks[1];
//        String string03 = stable01.trim();
        //
        // Если содержимое второй переменной забивает массив, значит пользователь ввел больше двух переменных. Выводим сообщение об этом.
//        String tmp_stable01 = input.substring(0, index).trim();
//        String[] blocks01 = tmp_stable01.split("[+-/*]");
//        String tmp_stable02 = blocks01[1];
//        String stable00 = tmp_stable02.trim();
        // В stable00 помещаем все символы до знака операции. В stable01 все символы после знака.
        // Если в stable01 больше одной переменной, разбиваем их по знакам и берем только первую.
        // Если массив заполнился переменными, выводим сообщение об ошибке, но все равно считаем два первых числа.
        String stable00 = input.substring(0, index).trim();
        String stable01 = input.substring(index + 1, input.length()).trim();
        String[] blocks02 = stable01.split("[+-/*]");
        String stable02 = blocks02[0];
        String string03 = stable02.trim();
        if (blocks02.length > 1) {
            System.out.println("Некорректный ввод. Повторите попытку.");
        }
//        System.out.println("tmp_stable01: " + tmp_stable01);
//        System.out.println("tmp_stable02: " + tmp_stable02);
//        System.out.println("stable00: " + stable00);
//        System.out.println("stable01: " + stable01);
//        System.out.println("stable02: " + stable02);
//        System.out.println("string03: " + string03);

        // Проверяем переменные. Если они состоят из цифр, то парсим в целое число. В противном случае переводим в римские цифры.
        if (isNumeric(stable00) && isNumeric(string03)) {
            op1 = Integer.parseInt(stable00);
            op2 = Integer.parseInt(string03);
        } else {
            op1 = romanToNumber(stable00);
            op2 = romanToNumber(string03);
        }

        // Производим вычисления. Если переменные числовые, то используем вычисления для арабских цифр. В противном случае для римских.
        if (isNumeric(stable00) && isNumeric(string03)) {
            // При условии, что введены числа от 1 до 10, производим вычисления. В противном случае выводим сообщение об ошибке.
            if (op1 >= 1 && op1 <= 10 && op2 >= 1 && op2 <= 10) {
                result = calculated(op1, op2, operation);
                System.out.println("--Результат для арабских цифр----");
                System.out.println(op1 + " " + operation + " " + op2 + " = " + result);
            } else {
                System.out.println("--Результат для арабских цифр----");
                System.out.println("Допустимы только числа от 1 до 10. Повторите ввод.");
            }
        } else {
            // При условии, что введены числа от I до X, производим вычисления. В противном случае выводим сообщение об ошибке.
            if (op1 >= 1 && op1 <= 10 && op2 >= 1 && op2 <= 10) {
                result = calculated(op1, op2, operation);
                if (result <= 0) {
                    System.out.println("---Результат для римских цифр----");
                    System.out.println("Результат получился отрицательным. У римских цифр нет отрицательных значений.");
                } else {
                    System.out.println("---Результат для римских цифр----");
                    String resultRoman = convertNumToRoman(result);
                    System.out.println(convertNumToRoman(op1) + " " + operation + " " + convertNumToRoman(op2) + " = " + resultRoman);
                }
            } else {
                System.out.println("---Результат для римских цифр----");
                System.out.println("Можно использовать только римские цифры от I до X. Повторите ввод.");
            }
        }
    }

    public static String convertNumToRoman(int numArabian) {
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


    public static int romanToNumber(String roman) {
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

    public static int calculated(int num1, int num2, char op) {
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

    // Проверям переданную строку на соответствие числовому вводу.
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[+-]?\\d+");
        // [0-9.]+
    }
}
