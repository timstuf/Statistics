public class Application {
    public static void main(String[] args) {
        int[] values = {22, 26, 30, 34, 38, 42, 46, 50, 54, 58};
        int[] quantities = {0, 1, 3, 4, 4, 5, 4, 2, 1, 1};
        Functions functions = new Functions(values, quantities);
        double expectation = functions.expectation();
        double dispersion = functions.dispersion(expectation);
    }
}
