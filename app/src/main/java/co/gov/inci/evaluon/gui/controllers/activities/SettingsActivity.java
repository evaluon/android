package co.gov.inci.evaluon.gui.controllers.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.MainMenuItem;

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
                        R.drawable.ic_change_password
                ),
        };
    }


}