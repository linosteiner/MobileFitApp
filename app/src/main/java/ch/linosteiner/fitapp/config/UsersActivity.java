/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 16.05.2026
 */

package ch.linosteiner.fitapp.config;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import ch.linosteiner.fitapp.BaseActivity;
import ch.linosteiner.fitapp.R;

public class UsersActivity extends BaseActivity {
    private ArrayList<String> userList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userList = new ArrayList<>(List.of("<anonymous>"));

        Spinner spinner = findViewById(R.id.usersSpinner);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userList);
        spinner.setAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String currentUser = prefs.getString("currentUser", "<anonymous>");
        if (userList.contains(currentUser)) {
            spinner.setSelection(userList.indexOf(currentUser));
        }

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String user = parent.getItemAtPosition(position).toString();
                getSharedPreferences("prefs", MODE_PRIVATE).edit().putString("currentUser", user).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });

        Button newUserButton = findViewById(R.id.newUserButton);
        newUserButton.setOnClickListener(view -> {
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_user, null);
            EditText addUserInput = dialogView.findViewById(R.id.addUserInput);

            new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setMessage("Neues Login erzeugen")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("Erzeugen", (dialog, which) -> {
                        String newUser = addUserInput.getText().toString().trim();
                        if (!newUser.isEmpty()) {
                            userList.add(newUser);
                            adapter.notifyDataSetChanged();
                            spinner.setSelection(userList.size() - 1);
                            Toast.makeText(this, "Login erzeugt", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Abbrechen", null)
                    .create()
                    .show();
        });
    }
}