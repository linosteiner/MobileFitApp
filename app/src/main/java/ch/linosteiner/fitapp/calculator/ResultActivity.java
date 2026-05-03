/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 03.05.2026
 */

package ch.linosteiner.fitapp.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ch.linosteiner.fitapp.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText bmiResult = findViewById(R.id.bmiResult);

        Intent intent = getIntent();
        double stringExtra = intent.getDoubleExtra("bmiResult", 0.0);
        bmiResult.setText(String.valueOf(stringExtra));
    }
}