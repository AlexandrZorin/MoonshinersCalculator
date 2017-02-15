package project.phoenix.moonshiterscalculator.ui.activity.correctstregth;

public class Formulas {

    public double formulaRoundingAreometerStrength(double x, double x1, double x2, double x1y, double x2y) {
        return x2y - (((x2 - x) / (x2 - x1)) * (x2y - x1y));
    }

    public double formulaRoundingTemperature(double temperature, double temperatureRoundDown, double temperatureRoundUp, double correctStrengthRoundDown, double correctStrengthRoundUp) {
        return correctStrengthRoundDown - (((temperatureRoundDown - temperature) / (temperatureRoundDown - temperatureRoundUp)) * (correctStrengthRoundDown - correctStrengthRoundUp));
    }

    public double formulaRoundingAll(double x, double x1, double x2, double x1y, double x2y, double x1y2, double x2y2) {
        double a = x1y2 - (((x1 - x) / (x1 - x2)) * (x1y2 - x2y2));
        double b = x1y - (((x1 - x) / (x1 - x2)) * (x1y - x2y));
        return a - (((x2 - x) / (x2 - x1)) * (a - b));
    }
}
