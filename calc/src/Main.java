import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в арабском или римском формате (например, [IV + X] в римском формате, или [1 + 3] в арабском формате):");
        String input = scanner.nextLine();
        try {
            String[] write = input.split(" ");
            if (write.length != 3) {
                throw new IllegalArgumentException("Неверный формат ввода. Используйте пример: 'a + b'");
            }
            String aStr = write[0];
            String function = write[1];
            String bStr = write[2];
            int a, b;
            boolean isRoman = false;
            if (isRomanNumber(aStr) && isRomanNumber(bStr)) {
                a = RomanConverter.romanToArabic(aStr);
                b = RomanConverter.romanToArabic(bStr);
                isRoman = true;
            } else if (isArabicNumber(aStr) && isArabicNumber(bStr)) {
                a = Integer.parseInt(aStr);
                b = Integer.parseInt(bStr);
            } else {
                throw new IllegalArgumentException("Калькулятор поддерживает только арабские или римские числа одновременно.");
            }
            int result;
            switch (function) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        throw new ArithmeticException("Деление на ноль невозможно.");
                    }
                    result = a / b;
                    break;
                default:
                    throw new IllegalArgumentException("Неверная арифметическая операция: " + function);
            }

            if (isRoman) {
                if (result < 1) {
                    throw new IllegalArgumentException("Результат не может быть меньше 1 в римской системе.");
                }
                String romanResult = RomanConverter.arabicToRoman(result);
                System.out.println("Результат: " + romanResult);
            } else {
                System.out.println("Результат: " + result);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isArabicNumber(String str) {
        try {
            int num = Integer.parseInt(str);
            return num >= 1 && num <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isRomanNumber(String str) {
        return str.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }
}

// Класс для конвертации римских чисел в арабские и обратно
class RomanConverter {
    private static String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV",
            "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV",
            "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI",
            "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII",
            "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
            "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
            "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI",
            "XCVII", "XCVIII", "XCIX", "C"};

    public static int romanToArabic(String roman) {
        int result = -1;
        for (int i = 0; i <= romanNumerals.length - 1; i++)
            if (romanNumerals[i].equals(roman)) {
                result = i + 1;
                break;
            }
        return result;
    }

    public static String arabicToRoman(int number) {
        String result = romanNumerals[number - 1];
        return result;
    }
}