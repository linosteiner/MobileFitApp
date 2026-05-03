/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 03.05.2026
 */

package ch.linosteiner.fitapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ch.linosteiner.fitapp.calculator.EntryActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button openBmiCalculator = findViewById(R.id.openBmiCalculator);
        openBmiCalculator.setOnClickListener(view -> {
            Intent bmiCalculatorIntent = new Intent(this, EntryActivity.class);
            startActivity(bmiCalculatorIntent);
        });
    }
}