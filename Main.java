import java.util.Scanner;
import java.util.TreeMap;

class Converters {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

    public Converters() {
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
    public int romanToInt(String s) {
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
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите вырожение: ");
        String exp = input.nextLine();
        Main result = new Main();
        try {
            String ans = result.calc(exp);
            System.out.println(ans);
        } catch (NullPointerException e2) {
            System.out.println("throws Excepsion");
        }
    }

    public static String calc(String input) {
        String excepsion = "throws Excepsion";
        boolean isRoman = false;
        int result = 0;
        Converters converter = new Converters();
        String[] data = input.split(" ");
        if (data.length != 3) {
            return excepsion;
        }
        int a, b;
        try {
            a = Integer.valueOf(data[0]);
            b = Integer.valueOf(data[2]);
        } catch (NumberFormatException e) {
            try {
                a = converter.romanToInt(String.valueOf(data[0]));
                b = converter.romanToInt(String.valueOf(data[2]));
                isRoman = true;
            } catch (NumberFormatException e1) {
                return excepsion;
            }
        }
        if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
            return excepsion;
        }
        String actions = data[1];
        switch (actions) {
            case "+":
                result = a + b;
                break;
            case  "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case  "/":
                result = a / b;
                break;
            default: {
                return excepsion;
            }
        }
        String output;
        if(isRoman) {
            if (result < 1) {
                return excepsion;
            } else {
                output = converter.intToRoman(result);
            }
        }
        else {
            output = Integer.toString(result);
            }
        return output;
    }
}
