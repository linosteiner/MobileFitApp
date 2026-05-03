/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 03.05.2026
 */

package ch.linosteiner.fitapp.util;

public class BMI {

    public static double calculateBmi(double weight, double height) {
        return Math.round(weight / Math.pow(height / 100, 2));
    }
}
