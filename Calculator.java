import java.util.Scanner;
import java.util.TreeMap;

class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

    public Converter() {
        romanKeyMap.put ('I', 1);
        romanKeyMap.put ('V', 5);
        romanKeyMap.put ('X', 10);
        romanKeyMap.put ('L', 50);
        romanKeyMap.put ('C', 100);
        romanKeyMap.put ('D', 500);
        romanKeyMap.put ('M', 1000);

        arabianKeyMap.put (1000, "M");
        arabianKeyMap.put (900, "CM");
        arabianKeyMap.put (500, "D");
        arabianKeyMap.put (400, "CD");
        arabianKeyMap.put (100, "C");
        arabianKeyMap.put (90, "XC");
        arabianKeyMap.put (50, "L");
        arabianKeyMap.put (40, "XL");
        arabianKeyMap.put (10, "X");
        arabianKeyMap.put (9, "IX");
        arabianKeyMap.put (5, "V");
        arabianKeyMap.put (4, "IV");
        arabianKeyMap.put (1, "I");
    }

    public boolean isRoman(String number) {
        return romanKeyMap.containsKey(number.charAt(0));
    }

    public String intToRoman(int number) {
        String roman = "";
        int arabianKey;
        try {
            do {
                arabianKey = arabianKeyMap.floorKey(number);
                roman += arabianKeyMap.get(arabianKey);
                number -= arabianKey;
            } while (number != 0);
        } catch (NullPointerException e) {
                System.out.println("Ошибка! В римской системе нет отрицательных чисел");
        }
        return roman;
    }
    public int romanToInt(String s){
         int end = s.length() - 1;
         char [] arr = s.toCharArray();
         int arabian;
         int result = romanKeyMap.get(arr[end]);
         for (int i = end -1; i >= 0; i--) {
             arabian = romanKeyMap.get(arr[i]);
             if (arabian < romanKeyMap.get(arr[i + 1])) {
                 result -= arabian;
             }
             else {
                 result += arabian;
             }
         }
        return result;
    }
}

public class Calculator {
    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите вырожение: ");
        String exp = scn.nextLine().replaceAll("\\s","");
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            System.out.println("Ошибка! Введите вырожение корректно.");
            return;
        }
        String[] data = exp.split(regexActions[actionIndex]);
        try {
            if (data[2].isEmpty()) {
                return;
            }
            else {
                System.out.println("Ошибка! Введите выражение с одной операцией.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                int a, b;
                boolean isRoman = converter.isRoman(data[0]);
                if (isRoman) {
                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);
                }
                else {
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                    if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
                        System.out.println("Ошибка! Введите числа в диапазоне от 1 до 10 включительно.");
                        return;
                    }
                }
                int result = 0;
                switch (actions[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                }
                if (isRoman) {
                    System.out.println(converter.intToRoman(result));
                }
                else {
                    System.out.println(result);
                }
            }
            else {
                System.out.println("Ошибка! Введите числа в одном формате.");
            }
        }
    }
}

