package lab3;

public class Gauss {
    public static final double EPS = 1e-9;

    private static boolean isZero(double a) {
        return Math.abs(a) <= EPS;
    }

    private static int findNotZero(ExtendedMatrix mat, int i) throws ZeroDetException {
        int ans = -1;
        for (int j = i + 1; j < mat.getSize(); j++) {
            if (!isZero(mat.getElem(j, i))) {
                ans = j;
                break;
            }
        }
        if (ans == -1)
            throw new ZeroDetException("Matrix det = 0");
        return ans;
    }

    private static ExtendedMatrix forwardStep(ExtendedMatrix mat) throws ZeroDetException {
        for (int i = 0; i < mat.getSize(); i++) {

            if (isZero(mat.getElem(i, i))) {
                mat.swapStr(i, findNotZero(mat, i));
            }

            double aii = mat.getElem(i, i);

            for (int j = i + 1; j < mat.getSize(); j++) {
                if (isZero(mat.getElem(j, i)))
                    continue;
                mat.subMultipliedStrings(j, i, mat.getElem(j, i) / aii);
            }
        }
        return mat;
    }

    static double[] run(ExtendedMatrix mat) throws ZeroDetException {
        mat = forwardStep(mat);
        double[] x = new double[mat.getSize()];
        for (int i = mat.getSize() - 1; i >= 0; i--) {
            x[i] = getXiFromTriangleMatrix(mat, i, x);
        }
        return x;
    }

    private static double getXiFromTriangleMatrix(ExtendedMatrix mat, int i, double[] x) {
        double ans = mat.getB(i);
        for (int j = i + 1; j < mat.getSize(); j++) {
            ans -= mat.getElem(i, j) * x[j];
        }
        ans /= mat.getElem(i, i);
        return ans;
    }
}
