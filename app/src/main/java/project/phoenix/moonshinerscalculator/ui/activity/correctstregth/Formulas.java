package project.phoenix.moonshinerscalculator.ui.activity.correctstregth;

/**
 * Class for calculating the correction of strength by formulas.
 */

class Formulas {

    double formulaRoundingAreometerStrength(
            double areometerStrength,
            double areometerStrengthRoundDown,
            double areometerStrengthRoundUp,
            double correctStrengthRoundDown,
            double correctStrengthRoundUp
    )
    {
        return correctStrengthRoundUp -
                (((areometerStrengthRoundUp - areometerStrength) /
                        (areometerStrengthRoundUp - areometerStrengthRoundDown)) *
                        (correctStrengthRoundUp - correctStrengthRoundDown));
    }

    double formulaRoundingTemperature(
            double temperature,
            double temperatureRoundDown,
            double temperatureRoundUp,
            double correctStrengthRoundDown,
            double correctStrengthRoundUp
    )
    {
        return correctStrengthRoundDown -
                (((temperatureRoundDown - temperature) /
                        (temperatureRoundDown - temperatureRoundUp)) *
                        (correctStrengthRoundDown - correctStrengthRoundUp));
    }

    double formulaRoundingAll(
            double temperature,
            double areometerStrength,
            double temperatureRoundDown,
            double temperatureRoundUp,
            double areometerStrengthRoundDown,
            double areometerStrengthRoundUp,
            double correctStrengthArStrUpTempDown,
            double correctStrengthArStrUpTempUp,
            double correctStrengthArStrDownTempDown,
            double correctStrengthArStrDownTempUp
    )
    {
        double a = correctStrengthArStrDownTempDown -
                (((temperatureRoundDown - temperature) /
                        (temperatureRoundDown - temperatureRoundUp)) *
                        (correctStrengthArStrDownTempDown - correctStrengthArStrDownTempUp));
        double b = correctStrengthArStrUpTempDown -
                (((temperatureRoundDown - temperature) /
                        (temperatureRoundDown - temperatureRoundUp)) *
                        (correctStrengthArStrUpTempDown - correctStrengthArStrUpTempUp));
        return a -
                (((areometerStrengthRoundUp - areometerStrength) /
                        (areometerStrengthRoundUp - areometerStrengthRoundDown)) *
                        (a - b));
    }
}
