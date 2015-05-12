package co.gov.inci.evaluon.gui.controllers.results.institutional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.ImageMenuItem;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.classes.institutions.Institution;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.proxies.ResultsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.gui.adapters.listadapters.ImageMenuListAdapter;
import co.gov.inci.evaluon.gui.controllers.tests.groups.GroupsActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InstitutionListActivity extends ActionBarActivity
        implements Callback<ApiResponse<Institution[]>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutions);
        new ResultsProxy(this).getInstitutions(this);
    }

    @Override public void success(ApiResponse<Institution[]> apiResponse, Response response) {
        Institution[] institutions = apiResponse.getData();
        ImageMenuItem[] items = new ImageMenuItem[institutions.length];

        if(institutions.length <= 0){
            ToastService.byResource(this, R.string.error_no_institutions_available);
            return;
        }

        for(int i = 0; i < institutions.length; i++){
            Intent intent = new Intent(this, TestResultsActivity.class);
            intent.putExtra("id", institutions[i].getId());

            items[i] = new ImageMenuItem(
                    institutions[i].getName(),
                    institutions[i].getImage(),
                    intent
            );
        }

        ((GridView)findViewById(R.id.institutions_menu_grid)).setAdapter(
                new ImageMenuListAdapter(this, items)
        );
    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
        finish();
    }
}
