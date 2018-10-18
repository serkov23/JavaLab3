package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

class ExtendedMatrix {
    private int size;
    private double[][] mat;

    public int getSize() {
        return size;
    }

    void read(File file) throws FileNotFoundException, InputMismatchException {
        Scanner scan = new Scanner(file);
        size = scan.nextInt();

        if (size <= 0)
            throw new InputMismatchException("Invalid size");

        mat = new double[size][size + 1];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                mat[i][j] = scan.nextDouble();

        for (int i = 0; i < size; i++)
            mat[i][size] = scan.nextDouble();
    }

    double getElem(int i, int j) {
        if (i >= size || j >= size)
            throw new IndexOutOfBoundsException("Invalid index");
        return mat[i][j];
    }

    void subMultipliedStrings(int from, int what, double l) {
        if (from >= size || what >= size)
            throw new IndexOutOfBoundsException("Invalid index");
        for (int it = 0; it <= size; it++) {
            mat[from][it] -= mat[what][it] * l;
        }
    }

    void swapStr(int i, int j) {
        if (i >= size || j >= size)
            throw new IndexOutOfBoundsException("Invalid index");
        if (i == j)
            return;
        var buf = mat[i];
        mat[i] = mat[j];
        mat[j] = buf;
    }

    double getB(int i) {
        return mat[i][size];
    }
}
