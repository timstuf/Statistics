import java.util.Scanner;
import java.util.stream.IntStream;

class Functions {
    private int[] values;
    private int[] quantities;
    private int n;
    private double expectation;
    private double dispersion;
    private Scanner scanner = new Scanner(System.in);

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
        System.out.printf("Sum = %.2f\n", expectation);
        System.out.printf("Математическое ожидание = %.2f / %d = %.2f \n", expectation, n, expectation / n);
        System.out.println("____________________________________");
        this.expectation = expectation / n;
    }

    void dispersion() {
        double dispersion = 0;
        for (int i = 0; i < values.length; i++) {
            dispersion += Math.pow((values[i] - expectation), 2) * quantities[i];
            System.out.printf("%d * (%d - %.2f)^2 = %f\n", quantities[i], values[i], expectation, Math.pow((values[i] - expectation), 2) * quantities[i]);
        }
        System.out.printf("Выборочная дисперсия = %.2f / %d = %.2f\n", dispersion, n, dispersion / n);
        System.out.printf("Исправленная выборочная дисперсия = %d/%d * %.2f = %.2f \n", n, n - 1, dispersion / n, dispersion / (n - 1));
        this.dispersion = dispersion / (n - 1);
        System.out.println("____________________________________");
    }

    void intervalExpectation(double alpha) {
        double laplasOfU = (1 - alpha) / 2;
        System.out.printf("Ф(u/2) = (1 - %.2f) / 2 = %.2f\n", alpha, laplasOfU);
        System.out.printf("Посмотрите на таблицу Лапласа. Найдите ячейку, значение которой равно %.2f. \n" +
                "Сложите строку и колонку, которой соответствует это значение, и введите это число. \n(Например, если находится на пересечении 1.6 и 0.05 введите 1,65,\n РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ)\n", laplasOfU);
        double u2 = scanner.nextDouble();
        double std = Math.sqrt(dispersion);
        double nSqrt = Math.sqrt(n);
        double first = expectation - std/nSqrt*u2;
        double second = expectation + std/nSqrt*u2;
        System.out.printf("First bound: %.2f - (%.2f * %.2f / %.2f)  =  %.2f\n", expectation, u2, std, nSqrt, first);
        System.out.printf("Second bound: %.2f - (%.2f * %.2f / %.2f)  =  %.2f\n", expectation, u2, std, nSqrt, second);
        System.out.println("____________________________________");
    }

    void intervalExpectationByDispersion(double alpha){
        System.out.printf("Посмотрите на таблицу распределения Стьюдента. Число степеней свободы равно %d. \n" +
                "Уровень значимости равен %.2f.\nВведите число на пересечении, РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ \n", n-1, alpha);
        double t = scanner.nextDouble();
        double std = Math.sqrt(dispersion);
        double nSqrt = Math.sqrt(n);
        double first = expectation - std/nSqrt*t;
        double second = expectation + std/nSqrt*t;
        System.out.printf("First bound: %.2f - (%.2f * %.2f / %.2f)  =  %.2f\n", expectation, t, std, nSqrt, first);
        System.out.printf("Second bound: %.2f - (%.2f * %.2f / %.2f)  =  %.2f\n", expectation, t, std, nSqrt, second);
        System.out.println("____________________________________");
    }
}
