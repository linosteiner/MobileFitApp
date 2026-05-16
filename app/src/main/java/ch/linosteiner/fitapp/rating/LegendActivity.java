/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 10.05.2026
 */

package ch.linosteiner.fitapp.rating;

import static android.R.layout.*;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

import ch.linosteiner.fitapp.BaseActivity;
import ch.linosteiner.fitapp.R;
import ch.linosteiner.fitapp.databinding.ActivityLegendBinding;
import ch.linosteiner.fitapp.util.BMI;

public class LegendActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivityLegendBinding.inflate(getLayoutInflater()).getRoot());
        ListView listView = findViewById(R.id.categoryListView);

        BMI.BmiCategory[] categories = BMI.BmiCategory.values();
        String[] categoryNames = Arrays.stream(categories)
                .map(BMI.BmiCategory::getFullName)
                .toArray(String[]::new);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, simple_list_item_1, categoryNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(LegendActivity.this, DetailActivity.class);
            intent.putExtra("CATEGORY_ENUM_NAME", categories[position].name());
            startActivity(intent);
        });
    }
}