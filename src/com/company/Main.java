package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        Num a = new Num(sc.next());
        String op = sc.next();
        Num b = new Num(sc.next());
        sc.close();

        if ((a.getValue() > 10) || (b.getValue() > 10)) throw new Exception();
        if ((a.getValue() <  1) || (b.getValue() <  1)) throw new Exception();
        System.out.println(Num.romeToArab("MMIX"));
        System.out.println(a.calc(b, op));

    }
}