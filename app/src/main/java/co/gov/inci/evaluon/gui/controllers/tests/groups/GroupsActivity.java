package co.gov.inci.evaluon.gui.controllers.tests.groups;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.GridView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.ImageMenuItem;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.classes.general.Image;
import co.gov.inci.evaluon.backend.models.classes.institutions.Group;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.proxies.InstitutionsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.gui.adapters.listadapters.ImageMenuListAdapter;
import co.gov.inci.evaluon.gui.controllers.tests.list.GroupTestListActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GroupsActivity extends ActionBarActivity implements Callback<ApiResponse<Group[]>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        int id = getIntent().getIntExtra("id", 0);
        new InstitutionsProxy(this).getGroups(id, this);
    }


    @Override public void success(ApiResponse<Group[]> apiResponse, Response response) {
        Group[] groups = apiResponse.getData();
        ImageMenuItem[] items = new ImageMenuItem[groups.length];

        for(int i = 0; i < groups.length; i++){
            Intent intent = new Intent(this, GroupTestListActivity.class);
            intent.putExtra("id", groups[i].getId());

            items[i] = new ImageMenuItem(
                    groups[i].getUser().getName(),
                    new Image(
                            "0",
                            String.format(
                                    "%s%c%c",
                                    getString(R.string.placeHoldItUrl),
                                    groups[i].getUser().getFirstName().charAt(0),
                                    groups[i].getUser().getLastName().charAt(0)
                            ),
                            groups[i].getUser().getName(),
                            false
                    ),
                    intent
            );
        }

        ((GridView)findViewById(R.id.groups_menu_grid)).setAdapter(
                new ImageMenuListAdapter(this, items)
        );
    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
        finish();
    }

}
