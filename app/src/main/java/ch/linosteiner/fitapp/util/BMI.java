/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 10.05.2026
 */

package ch.linosteiner.fitapp.util;

public class BMI {

    public static double calculateBmi(double weight, double height) {
        return Math.round(weight / Math.pow(height / 100, 2));
    }

    public enum BmiCategory {
        VERY_SEVERELY_UNDERWEIGHT("underweight", "very severely", 0.0, 15.0),
        SEVERELY_UNDERWEIGHT("underweight", "severely", 15.0, 16.0),
        MODERATELY_UNDERWEIGHT("underweight", "moderately", 16.0, 17.0),
        SLIGHTLY_UNDERWEIGHT("underweight", "slightly", 17.0, 18.5),
        NORMAL("normal (healthy weight)", "", 18.5, 25.0),
        OVERWEIGHT("overweight", "", 25.0, 30.0),
        OBESE_CLASS_I("obese", "moderately (class 1)", 30.0, 35.0),
        OBESE_CLASS_II("obese", "severely (class II)", 35.0, 40.0),
        OBESE_CLASS_III("obese", "very severely (class III)", 40.0, 100.0);

        private final String generalName;
        private final String specificName;
        private final double minValue;
        private final double maxValue;

        BmiCategory(String general, String specific, double min, double max) {
            this.generalName = general;
            this.specificName = specific;
            this.minValue = min;
            this.maxValue = max;
        }

        public String getGeneralName() {
            return generalName;
        }

        public String getSpecificName() {
            return specificName;
        }

        public double getMinValue() {
            return minValue;
        }

        public double getMaxValue() {
            return maxValue;
        }

        public String getFullName() {
            if (specificName.isEmpty()) return generalName;
            return generalName + " - " + specificName;
        }

        public static BmiCategory getCategoryForBmi(double bmiValue) {
            for (BmiCategory category : values()) {
                if (bmiValue >= category.minValue && bmiValue < category.maxValue) {
                    return category;
                }
            }
            return OBESE_CLASS_III; // Default fallback
        }
    }
}