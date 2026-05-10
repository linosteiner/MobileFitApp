/*
 * Verantwortlicher Mitarbeiter: linosteiner
 * Letzte Änderung: 10.05.2026
 */

package ch.linosteiner.fitapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import ch.linosteiner.fitapp.calculator.EntryActivity;
import ch.linosteiner.fitapp.config.SettingsActivity;
import ch.linosteiner.fitapp.history.OverviewActivity;
import ch.linosteiner.fitapp.rating.LegendActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this instanceof MainActivity) menu.findItem(R.id.menu_home).setVisible(false);
        if (this instanceof EntryActivity) menu.findItem(R.id.menu_calculator).setVisible(false);
        if (this instanceof LegendActivity) menu.findItem(R.id.menu_rating).setVisible(false);
        if (this instanceof OverviewActivity) menu.findItem(R.id.menu_history).setVisible(false);
        if (this instanceof SettingsActivity) menu.findItem(R.id.menu_settings).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.menu_calculator) {
            startActivity(new Intent(this, EntryActivity.class));
            return true;
        } else if (id == R.id.menu_rating) {
            startActivity(new Intent(this, LegendActivity.class));
            return true;
        } else if (id == R.id.menu_history) {
            startActivity(new Intent(this, OverviewActivity.class));
            return true;
        } else if (id == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}