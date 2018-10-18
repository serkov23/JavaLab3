package test.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

class TestMatrix {
    int size;
    double[][] mat;
    double[] x;

    TestMatrix(Random rand) {
        this.size = rand.nextInt(20) + 1;
        this.mat = new double[size][size];
        this.x = new double[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                mat[i][j] = genRand(rand);

            x[i] = genRand(rand);
        }
    }

    private static double[] mul(double[][] mat, double[] v) {
        double[] ans = new double[mat.length];
        for (int i = 0; i < mat.length; i++)
            ans[i] = mul(mat[i], v);
        return ans;
    }

    private static double mul(double[] v1, double[] v2) {
        if (v1.length != v2.length)
            throw new ArithmeticException("can't multiply vectors of different sizes");
        double ans = 0;
        for (int i = 0; i < v1.length; i++)
            ans += v1[i] * v2[i];
        return ans;
    }

    private static void print(double[] vector, FileWriter fout) throws IOException {
        for (double it : vector) fout.write(Double.toString(it).replaceAll("\\.", ",") + ' ');
    }

    private static double genRand(Random rand) {
//        return (rand.nextBoolean() ? -1 : 1) * rand.nextDouble() * 100;
        return rand.nextInt(10);
    }

    private void printMat(FileWriter fout) throws IOException {
        for (int i = 0; i < size; i++) {
            print(mat[i], fout);
            fout.write('\n');
        }
    }

    void printf(FileWriter fout) {
        try {
            fout.write(Integer.toString(size) + '\n');
            printMat(fout);
            fout.write('\n');
            print(mul(mat, x), fout);
            fout.write("\n//expected output: ");
            print(x, fout);
            fout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printExpOut(FileWriter fout) {
        try {
            print(x, fout);
            fout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Random rand = new Random(Arrays.hashCode(args));
        var mat = new TestMatrix(rand);

        try {
            mat.printf(new FileWriter(new File("tests\\" + (args.length == 0 ? "test" : args[0]) + ".in")));
            var file = new FileWriter(new File("tests\\" + (args.length == 0 ? "test" : args[0]) + ".expout"));
            mat.printExpOut(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
