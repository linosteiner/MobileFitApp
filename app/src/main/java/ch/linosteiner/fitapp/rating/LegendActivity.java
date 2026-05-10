/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 10.05.2026
 */

package ch.linosteiner.fitapp.rating;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ch.linosteiner.fitapp.BaseActivity;
import ch.linosteiner.fitapp.R;
import ch.linosteiner.fitapp.util.BMI;

public class LegendActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legend);

        ListView listView = findViewById(R.id.categoryListView);

        BMI.BmiCategory[] categories = BMI.BmiCategory.values();
        String[] categoryNames = new String[categories.length];
        for (int i = 0; i < categories.length; i++) {
            categoryNames[i] = categories[i].getFullName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(LegendActivity.this, DetailActivity.class);
            intent.putExtra("CATEGORY_ENUM_NAME", categories[position].name());
            startActivity(intent);
        });
    }
}