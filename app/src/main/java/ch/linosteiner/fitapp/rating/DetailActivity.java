/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 10.05.2026
 */

package ch.linosteiner.fitapp.rating;

import android.os.Bundle;
import android.widget.TextView;

import ch.linosteiner.fitapp.BaseActivity;
import ch.linosteiner.fitapp.R;
import ch.linosteiner.fitapp.util.BMI;

public class DetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String enumName = getIntent().getStringExtra("CATEGORY_ENUM_NAME");
        if (enumName != null) {
            BMI.BmiCategory category = BMI.BmiCategory.valueOf(enumName);

            TextView tvGeneral = findViewById(R.id.tvGeneral);
            TextView tvSpecific = findViewById(R.id.tvSpecific);
            TextView tvMin = findViewById(R.id.tvMin);
            TextView tvMax = findViewById(R.id.tvMax);

            tvGeneral.setText(getString(R.string.generalName).formatted(category.getGeneralName()));
            tvSpecific.setText(getString(R.string.specificName).formatted(category.getSpecificName()));
            tvMin.setText(getString(R.string.minValue).formatted(category.getMinValue()));
            tvMax.setText(getString(R.string.maxValue).formatted(category.getMaxValue()));
        }
    }
}