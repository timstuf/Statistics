import java.util.Scanner;
import java.util.stream.IntStream;

class Functions {
    private int[] values;
    private int[] quantities;
    private int n;
    private double expectation;
    private double dispersion;

    Functions(int[] values, int[] quantities) {
        this.values = values;
        this.quantities = quantities;
        n = IntStream.of(quantities).sum();
    }

    void expectation() {
        double expectation = 0;
        for (int i = 0; i < values.length; i++) {
            expectation += values[i] * quantities[i];
            System.out.printf("%d * %d = %d\n", values[i], quantities[i], values[i] * quantities[i]);
        }
        System.out.printf("Sum = %f\n", expectation);
        System.out.printf("Математическое ожидание = %f / %d = %f \n", expectation, n, expectation / n);
        System.out.println("____________________________________");
        this.expectation = expectation / n;
    }

    void dispersion() {
        double dispersion = 0;
        for (int i = 0; i < values.length; i++) {
            dispersion += Math.pow((values[i] - expectation), 2) * quantities[i];
            System.out.printf("%d * (%d - %.3f)^2 = %f\n", quantities[i], values[i], expectation, Math.pow((values[i] - expectation), 2) * quantities[i]);
        }
        System.out.printf("Выборочная дисперсия = %.3f / %d = %.3f\n", dispersion, n, dispersion / n);
        System.out.printf("Исправленная выборочная дисперсия = %d/%d * %.3f = %.3f \n", n, n - 1, dispersion / n, dispersion / (n - 1));
        this.dispersion = dispersion / (n - 1);
        System.out.println("____________________________________");
    }

    void intervalExpectation(double alpha) {
        Scanner scanner = new Scanner(System.in);
        double laplasOfU = (1 - alpha) / 2;
        System.out.printf("Посмотрите на таблицу Лапласа. Найдите ячейку, значение которой равно %.3f. " +
                "Сложите строку и колонку, которой соответствует это значение, и введите это число. (Например, если находится на пересечении 1.6 и 0.05 введите 1,65)\n", laplasOfU);
        double u2 = scanner.nextDouble();
        double std = Math.sqrt(dispersion);
        double nSqrt = Math.sqrt(n);
        double first = expectation - std/nSqrt*u2;
        double second = expectation + std/nSqrt*u2;
        System.out.printf("First interval: %.3f - (%.3f * %.3f / %.3f)  =  %.3f\n", expectation, u2, std, nSqrt, first);
        System.out.printf("First interval: %.3f - (%.3f * %.3f / %.3f)  =  %.3f\n", expectation, u2, std, nSqrt, second);
        System.out.println("____________________________________");
    }
}
