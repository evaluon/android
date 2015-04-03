package co.gov.inci.evaluon.gui.controllers.activities.settings;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.GridView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.MainMenuItem;
import co.gov.inci.evaluon.gui.adapters.listadapters.MainMenuListAdapter;

public class SettingsActivity extends ActionBarActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setMenu();
    }

    private void setMenu() {
        MainMenuItem[] items = new MainMenuItem[] {
                new MainMenuItem(
                        R.string.label_change_password,
                        R.string.descriptor_change_password,
                        R.drawable.ic_change_password,
                        new Intent(this, ChangePasswordActivity.class)
                ),
                new MainMenuItem(
                        R.string.label_profile_update,
                        R.string.descriptor_profile_update,
                        R.drawable.ic_profile_update,
                        new Intent(this, UpdateUserActivity.class)
                ),
                new MainMenuItem(
                        R.string.label_help,
                        R.string.descriptor_help,
                        R.drawable.ic_help
                ),
                new MainMenuItem(
                        R.string.label_about,
                        R.string.descriptor_about,
                        R.drawable.ic_turnoff
                )
        };
        ((GridView)findViewById(R.id.main_menu_grid)).setAdapter(new MainMenuListAdapter(
                this, items
        ));
    }


}