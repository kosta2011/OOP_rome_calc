package com.company;

import java.util.HashSet;
import java.util.Set;

public class Num {
    private int value;
    private TypeNumber type;

    static Set<String> arabicSet = new HashSet<>();
    static Set<String> rimSet = new HashSet<>();

    static {
        arabicSet.add("1");
        arabicSet.add("2");
        arabicSet.add("3");
        arabicSet.add("4");
        arabicSet.add("5");
        arabicSet.add("6");
        arabicSet.add("7");
        arabicSet.add("8");
        arabicSet.add("9");
        arabicSet.add("0");
        rimSet.add("I");
        rimSet.add("V");
        rimSet.add("X");
        rimSet.add("C");
        rimSet.add("L");
        rimSet.add("D");
        rimSet.add("M");
    }

    public Num(String num) throws Exception {
        if (rimTrue(num)) {
            this.type = TypeNumber.ROME;
            this.value = romeToArab(num);
        }
        if (arabicTrue(num)) {
            this.type = TypeNumber.ARAB;
            this.value = Integer.parseInt(num);
        }
        if (type == null) {
            throw new Exception();
        }
    }

    private Num(int value) {
        this.type = TypeNumber.ARAB;
        this.value = value;
    }

    private Num(int value, TypeNumber type) {
        this.type = type;
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    TypeNumber getType() {
        return this.type;
    }

    @Override
    public String toString() {
        if (type == TypeNumber.ARAB) return "" + value;
        if (type == TypeNumber.ROME) return arabToRome(value);
        return null;
    }

    static String arabToRome(int number) {
        int[] roman_value_list = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roman_value_list.length; i++) {
            while (number >= roman_value_list[i]) {
                number -= roman_value_list[i];
                res.append(roman_char_list[i]);
            }
        }
        return res.toString();
    }

    static int romeToArab(String a) {
        int ans = 0, num = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            switch (a.charAt(i)) {
                case 'I':
                    num = 1;
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;

    }

    static boolean rimTrue(String a) {
        boolean res = true;
        for (int i = 0; i < a.length(); i++) {
            if (!(rimSet.contains(("" + a.charAt(i))))) {
                res = false;
                break;
            }
        }
        return res;
    }

    static boolean arabicTrue(String a) {
        boolean res = false;
        for (int i = 0; i < a.length(); i++) {
            if ((arabicSet.contains(("" + a.charAt(i))))) {
                res = true;
                if (!res) break;
            }
        }
        return res;
    }

    public Num calc(Num n, String op) throws Exception {
        if (n.getType() != this.getType())
            throw new Exception("Калькулятор не умеет работать с числами из разных систем");
        int res = 0;
        switch (op) {
            case ("+"):
                res = this.value + n.getValue();
                break;
            case ("-"):
                res = this.value - n.getValue();
                break;
            case ("/"):
                res = this.value / n.getValue();
                break;
            case ("*"):
                res = this.value * n.getValue();
                break;
            default:
                throw new Exception("Операция \"" + op + "\" не поддерживается калькулятором");
        }
        if ((res <= 0) && (this.getType() == TypeNumber.ROME)) {
            throw new Exception("В римских числах нет чисел меньше 0");
        }
        return new Num(res, this.type);
    }
}