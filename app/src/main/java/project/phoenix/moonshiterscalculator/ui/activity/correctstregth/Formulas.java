package project.phoenix.moonshiterscalculator.ui.activity.correctstregth;

public class Formulas {

    public double mathRoundingAreometerStrength(double x, double x1, double x2, double x1y, double x2y) {
        return x2y - (((x2 - x) / (x2 - x1)) * (x2y - x1y));
    }

    public double mathRoundingTemperature(double x, double x1, double x2, double x1y, double x2y) {
        return x1y - (((x1 - x) / (x1 - x2)) * (x1y - x2y));
    }

    public double mathRoundingAll(double x, double x1, double x2, double x1y, double x2y, double x1y2, double x2y2) {
        double a = x1y2 - (((x1 - x) / (x1 - x2)) * (x1y2 - x2y2));
        double b = x1y - (((x1 - x) / (x1 - x2)) * (x1y - x2y));
        return a - (((x2 - x) / (x2 - x1)) * (a - b));
    }
}
