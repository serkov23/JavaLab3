package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ExtendedMatrix mat = new ExtendedMatrix();
        if (args.length != 0)
            System.out.println("\t\tTest #" + args[0]);
        try {
            mat.read(new File("tests\\" + (args.length == 0 ? "test" : args[0]) + ".in"));
            var x = Gauss.run(mat);
            System.out.println(Arrays.toString(x));
            FileWriter fout = new FileWriter(new File("tests\\" + (args.length == 0 ? "test" : args[0]) + ".out"));
            for (double it : x) fout.write(Double.toString(it).replaceAll("\\.", ",")+' ');
            fout.flush();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ZeroDetException e) {
            System.out.println("You promised not to give such matrix, but look what you gave ノ( º _ ºノ): " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ZeroDetException extends Exception {
    ZeroDetException(String message) {
        super(message);
    }
}

