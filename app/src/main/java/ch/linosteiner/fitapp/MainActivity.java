/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 10.05.2026
 */

package ch.linosteiner.fitapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ch.linosteiner.fitapp.calculator.EntryActivity;
import ch.linosteiner.fitapp.databinding.AddFruitBinding;

public class MainActivity extends BaseActivity {
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

        String[] fruitList = {
                "",
                "Apple",
                "Banana",
                "Clementine",
                "Date"
        };

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fruitList);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelected(false);
                String fruit = parent.getItemAtPosition(position).toString();
                getSharedPreferences("prefs", MODE_PRIVATE).edit().putString("fruit", fruit).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button newFruitButton = findViewById(R.id.newFruitButton);
        ConstraintLayout fruitDialogLayout = AddFruitBinding.inflate(getLayoutInflater()).getRoot();
        newFruitButton.setOnClickListener(view -> new AlertDialog.Builder(this)
                .setView(fruitDialogLayout)
                .setMessage("Aloha")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Frucht hinzufügen", (dialog, which) ->
                        Toast.makeText(this, "Frucht hinzugefügt", Toast.LENGTH_SHORT)
                                .show())
                .setNegativeButton("Abbrechen", null)
                .create()
                .show());

        Button openBmiCalculator = findViewById(R.id.openBmiCalculator);
        openBmiCalculator.setOnClickListener(view -> {
            Intent bmiCalculatorIntent = new Intent(this, EntryActivity.class);
            startActivity(bmiCalculatorIntent);
        });
    }
}