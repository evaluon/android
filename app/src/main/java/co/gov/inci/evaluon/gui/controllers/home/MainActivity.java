package co.gov.inci.evaluon.gui.controllers.home;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.GridView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.ResourcesMenuItem;
import co.gov.inci.evaluon.gui.adapters.listadapters.ImageMenuListAdapter;
import co.gov.inci.evaluon.gui.controllers.account.LogoutActivity;
import co.gov.inci.evaluon.gui.controllers.tests.groups.InstitutionsActivity;
import co.gov.inci.evaluon.gui.controllers.settings.SettingsActivity;
import co.gov.inci.evaluon.gui.controllers.tests.list.SelfTestListActivity;

public class MainActivity extends ActionBarActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ((GridView)findViewById(R.id.main_menu_grid)).setAdapter(
                new ImageMenuListAdapter(this, new ResourcesMenuItem[]{
                        new ResourcesMenuItem(
                                R.string.label_evaluation,
                                R.string.descriptor_evaluation,
                                R.drawable.ic_evaluation,
                                new Intent(this, InstitutionsActivity.class)
                        ),
                        new ResourcesMenuItem(
                                R.string.label_evaluate,
                                R.string.descriptor_evaluate,
                                R.drawable.ic_evaluate,
                                new Intent(this, SelfTestListActivity.class)
                        ),
                        new ResourcesMenuItem(
                                R.string.label_results,
                                R.string.descriptor_results,
                                R.drawable.ic_results
                        ),
                        new ResourcesMenuItem(
                                R.string.label_average,
                                R.string.descriptor_average,
                                R.drawable.ic_average
                        ),
                        new ResourcesMenuItem(
                                R.string.label_settings,
                                R.string.descriptor_settings,
                                R.drawable.ic_settings,
                                new Intent(this, SettingsActivity.class)
                        ),
                        new ResourcesMenuItem(
                                R.string.label_logout,
                                R.string.descriptor_logout,
                                R.drawable.ic_turnoff,
                                new Intent(this, LogoutActivity.class)
                        )
                })
        );

    }

}
