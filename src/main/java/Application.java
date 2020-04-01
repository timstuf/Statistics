public class Application {
    public static void main(String[] args) {
        double[] values = {0.3, 0.5, 0.7, 0.8, 1, 1.2, 1.4, 1.6, 1.8, 2};
        int[] quantities = {1, 1, 2, 3, 8, 5, 4, 2, 1, 0};
        Functions functions = new Functions(values, quantities);
        functions.expectation();
        functions.dispersion();
        functions.intervalExpectation(0.05, 0.15);
        functions.intervalExpectationByDispersion(0.1);
        functions.intervalDispersion(0.02);
    }
}
