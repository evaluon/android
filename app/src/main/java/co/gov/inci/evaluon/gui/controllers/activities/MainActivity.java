package co.gov.inci.evaluon.gui.controllers.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.GridView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.MainMenuItem;
import co.gov.inci.evaluon.gui.adapters.listadapters.MainMenuListAdapter;

public class MainActivity extends ActionBarActivity {
    private String TAG;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getString(R.string.title_activity_main);
        setContentView(R.layout.activity_menu);
        setMenu();
    }

    private void setMenu() {
        MainMenuItem[] items = new MainMenuItem[]{
                // Evaluation
                new MainMenuItem(
                        R.string.label_evaluation,
                        R.string.descriptor_evaluation,
                        R.drawable.ic_evaluation
                ),
                // Evaluate
                new MainMenuItem(
                        R.string.label_evaluate,
                        R.string.descriptor_evaluate,
                        R.drawable.ic_evaluate
                ),
                // Results
                new MainMenuItem(
                        R.string.label_results,
                        R.string.descriptor_results,
                        R.drawable.ic_results
                ),
                // Average
                new MainMenuItem(
                        R.string.label_average,
                        R.string.descriptor_average,
                        R.drawable.ic_average
                ),
                // Settings
                new MainMenuItem(
                        R.string.label_settings,
                        R.string.descriptor_settings,
                        R.drawable.ic_settings
                ),
                // Logout
                new MainMenuItem(
                        R.string.label_logout,
                        R.string.descriptor_logout,
                        R.drawable.ic_turnoff
                )
        };
        ((GridView)findViewById(R.id.main_menu_grid)).setAdapter(
                new MainMenuListAdapter(this, items)
        );

    }

}
