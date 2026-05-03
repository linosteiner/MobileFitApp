/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 03.05.2026
 */

package ch.linosteiner.fitapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ch.linosteiner.fitapp.util.BmiCalculator;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText weightInKg = findViewById(R.id.weightInKg);
        EditText heightInCm = findViewById(R.id.heightInCm);

        Button calculateBMI = findViewById(R.id.calculateBmi);

        calculateBMI.setOnClickListener(view -> {
            if (!weightInKg.getText().toString().isEmpty() && !heightInCm.getText().toString().isEmpty()) {
                double weight = Double.parseDouble(weightInKg.getText().toString());
                double height = Double.parseDouble(heightInCm.getText().toString());

                if (weight < 40 || weight > 250) {
                    weightInKg.setError("Bitte geben Sie einen Wert zwischen 40kg und 250kg ein.");
                    return;
                }

                if (height < 100 || height > 220) {
                    heightInCm.setError("Bitte geben Sie einen Wert zwischen 100cm und 220cm ein.");
                    return;
                }
                double bmi = BmiCalculator.calculateBmi(weight, height);

                Intent resultIntent = new Intent(this, Result.class);
                resultIntent.putExtra("bmiResult", bmi);
                startActivity(resultIntent);
            }
        });

    }
}