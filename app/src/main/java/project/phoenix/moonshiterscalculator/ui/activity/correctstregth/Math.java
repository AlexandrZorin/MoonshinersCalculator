package project.phoenix.moonshiterscalculator.ui.activity.correctstregth;

public class Math {
    private double x;
    private double x1;
    private double x2;
    private double x1y;
    private double x2y;

    public Math(double x, double x1, double x2, double x1y, double x2y) {
        this.x = x;
        this.x1 = x1;
        this.x2 = x2;
        this.x1y = x1y;
        this.x2y = x2y;
    }

    public double mathRoundingAreometerStrength() {
        return x2y - (((x2 - x) / (x2 - x1)) * (x2y - x1y));
    }

    public double mathRoundingTemperature() {
        return x1y - (((x1 - x) / (x1 - x2)) * (x1y - x2y));
    }

    public double mathRoundingAll(double x1y2, double x2y2) {
        double a = x1y2 - (((x1 - x) / (x1 - x2)) * (x1y2 - x2y2));
        double b = x1y - (((x1 - x) / (x1 - x2)) * (x1y - x2y));
        return a - (((x2 - x) / (x2 - x1)) * (a - b));
    }
}
