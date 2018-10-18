package test.runner;

import lab3.Gauss;
import lab3.Main;
import test.generator.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
    private static final int TEST_AMOUNT = 10;
    private static final boolean NEED_TO_GENERATE = true;

    public static void main(String[] args) {
        try {

            gen();
            for (int i = 0; i < TEST_AMOUNT; i++) {
                var str = new String[1];
                str[0] = Integer.toString(i);
                Main.main(str);
            }
            for (int i = 0; i < TEST_AMOUNT; i++) {
                Scanner scanOut = new Scanner(new File("tests\\" + i + ".out"));
                Scanner scanExpOut = new Scanner(new File("tests\\" + i + ".expout"));
                boolean check = true;
                while (true) {
                    if (!cmpDouble(scanExpOut.nextDouble(), scanOut.nextDouble())) {
                        System.out.println("Error " + i);
                        check = false;
                        break;
                    }
                    if (!scanExpOut.hasNext())
                        break;
                }
                if (check)
                    System.out.println("ok " + i);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void gen() {
        if (NEED_TO_GENERATE)
            for (int i = 0; i < TEST_AMOUNT; i++) {
                var str = new String[1];
                str[0] = Integer.toString(i);
                Test.main(str);
            }
    }

    static boolean cmpDouble(double a, double b) {
        return Math.abs(a - b) < Gauss.EPS;
    }
}
