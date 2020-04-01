import java.util.stream.IntStream;

public class Functions {
    private int[] values;
    private int[] quantities;
    private int n;

    public Functions(int[] values, int[] quantities) {
        this.values = values;
        this.quantities = quantities;
        n = IntStream.of(quantities).sum();
    }

    double expectation() {
        double expectation = 0;
        for (int i = 0; i < values.length; i++) {
            expectation += values[i] * quantities[i];
            System.out.printf("%d * %d = %d\n", values[i], quantities[i], values[i] * quantities[i]);
        }
        System.out.printf("Sum = %f\n", expectation);
        System.out.printf("Математическое ожидание = %f / %d = %f \n", expectation, n, expectation / n);
        System.out.println("____________________________________");
        return expectation / n;
    }

    public double dispersion(double expectation) {
        double dispersion = 0;
        for (int i = 0; i < values.length; i++) {
            dispersion += Math.pow((values[i] - expectation), 2) * quantities[i];
            System.out.printf("%d * (%d - %f)^2 = %f\n", quantities[i], values[i], expectation, Math.pow((values[i] - expectation), 2) * quantities[i]);
        }
        System.out.printf("Выборочная дисперсия = %f / %d = %f\n", dispersion, n, dispersion / n);
        System.out.printf("Исправленная выборочная дисперсия = %d/%d * %f = %f \n", n, n - 1, dispersion / n, dispersion / (n - 1));
        return dispersion / (n - 1);
    }
}
