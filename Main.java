import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine().trim();
        scanner.close();
        try {
            String result = calc(input);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        String[] stroka = input.split(" ");
        if (stroka.length != 3) {
            throw new IllegalArgumentException("Неверное количество аргументов");
        }

        boolean isRoman = isRomanNumber(stroka[0]) && isRomanNumber(stroka[2]);
        boolean isArabic = !isRomanNumber(stroka[0]) && !isRomanNumber(stroka[2]);

        if (!isRoman && !isArabic) {
            throw new IllegalArgumentException("Калькулятор может работать только с арабскими или римскими числами одновременно");
        }

        int operand1, operand2;
        try {
            operand1 = parseNumber(stroka[0]);
            operand2 = parseNumber(stroka[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неправильный формат чисел");
        }

        if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10) {
            throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
        }

        String operator = stroka[1];
        if (!operator.matches("[+\\-*/]")) {
            throw new IllegalArgumentException("Недопустимая операция");
        }

        int result;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль невозможно");
                }
                result = operand1 / operand2;
                break;
            default:
                throw new IllegalArgumentException("Недопустимая операция");
        }

        if (isRoman) {
            if (result < 1) {
                throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
            }
            return toRomanNumeral(result);
        } else {
            return String.valueOf(result);
        }
    }

    // Преобразовывает строку для дальнейших вычислений
    static int parseNumber(String str) {
        if (isRomanNumber(str)) {
            return romanToArabic(str);
        } else {
            return Integer.parseInt(str);
        }
    }

    // Проверка на римское число
    static boolean isRomanNumber(String str) {
        return str.matches("^[IVXLC]+$");
    }

    // Конвертация римских чисел в арабские
    static int romanToArabic(String str) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        int result = 0;
        int prevValue = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            int value = map.get(str.charAt(i));
            if (value >= prevValue) {
                result += value;
            } else {
                result -= value;
            }
            prevValue = value;
        }
        return result;
    }

    // Конвертация результата в римское число
    static String toRomanNumeral(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Отрицательные числа и ноль нельзя представить римскими цифрами");
        }

        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            while (number >= entry.getKey()) {
                sb.append(entry.getValue());
                number -= entry.getKey();
            }
        }
        return sb.toString();
    }
}

