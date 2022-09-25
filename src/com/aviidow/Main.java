package com.aviidow;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int romanToArabic(String roman) {
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            switch (roman.charAt(i)) {
                case 'I' -> result += 1;
                case 'V' -> {
                    result += 5;
                    if (i > 0 && roman.charAt(i - 1) == 'I') result -= 2;
                }
                case 'X' -> {
                    result += 10;
                    if (i > 0 && roman.charAt(i - 1) == 'I') result -= 2;
                }
            }
        }
        return result;
    }

    public static String arabicToRoman(int arabic) {
        StringBuilder result = new StringBuilder();
        while (arabic - 100 >= 0) {
            result.append("C");
            arabic -= 500;
        }
        if (arabic - 90 >= 0) {
            result.append("XC");
            arabic -= 90;
        }
        while (arabic - 50 >= 0) {
            result.append("L");
            arabic -= 50;
        }
        if (arabic - 40 >= 0) {
            result.append("LX");
            arabic -= 40;
        }
        while (arabic - 10 >= 0) {
            result.append("X");
            arabic -= 10;
        }
        if (arabic - 9 >= 0) {
            result.append("IX");
            arabic -= 9;
        }
        while (arabic - 5 >= 0) {
            result.append("V");
            arabic -= 5;
        }
        if (arabic - 4 >= 0) {
            result.append("IV");
            arabic -= 4;
        }
        while (arabic - 1 >= 0) {
            result.append("I");
            arabic -= 1;
        }
        return result.toString();
    }

    public static String calc(String input) throws RuntimeException {
        final boolean roman;
        if (input.matches("(\\d+\\s*[-+/*]\\s*\\d+)")) {
            roman = false;
        }
        else if (input.matches("([IVX]+\\s*[-+/*]\\s*[IVX]+)")) {
            roman = true;
        }
        else {
            throw new RuntimeException("Incorrect input");
        }
        input = input.replace(" ", "");
        int[] numbers;
        if (roman) {
            numbers = Arrays.stream(input.split("[-+/*]")).mapToInt(Main::romanToArabic).toArray();
        }
        else {
            numbers = Arrays.stream(input.split("[-+/*]")).mapToInt(Integer::parseInt).toArray();
        }
        if (numbers[0] < 1 || numbers[0] > 10
                || numbers[1] < 1 || numbers[1] > 10) {
            throw new RuntimeException("Number out of bounds");
        }
        int result = 0;
        switch (input.charAt(input.split("[-+/*]")[0].length())) {
            case '-' -> {
                result = numbers[0] - numbers[1];
            }
            case '+' -> {
                result = numbers[0] + numbers[1];
            }
            case '/' -> {
                result = numbers[0] / numbers[1];
            }
            case '*' -> {
                result = numbers[0] * numbers[1];
            }
        }
        if (roman && result < 1) {
            throw new RuntimeException("Roman result is lesser than 1");
        }
        return "" + result;
//        return roman ? arabicToRoman(result) : ("" + result);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println(calc(scanner.nextLine()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
