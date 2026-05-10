/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 10.05.2026
 */

package ch.linosteiner.fitapp.calculator;

import static ch.linosteiner.fitapp.util.BMI.BmiCategory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ch.linosteiner.fitapp.BaseActivity;
import ch.linosteiner.fitapp.R;
import ch.linosteiner.fitapp.rating.DetailActivity;

public class ResultActivity extends BaseActivity {

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

        BmiCategory currentCategory = BmiCategory.getCategoryForBmi(stringExtra);

        TextView categoryDisplay = findViewById(R.id.categoryDisplay);
        categoryDisplay.setText(currentCategory.getFullName());

        Button viewDetailsButton = findViewById(R.id.viewDetailsButton);
        viewDetailsButton.setOnClickListener(v -> {
            Intent detailIntent = new Intent(ResultActivity.this, DetailActivity.class);
            detailIntent.putExtra("CATEGORY_ENUM_NAME", currentCategory.name());
            startActivity(detailIntent);
        });
    }
}