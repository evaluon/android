package co.gov.inci.evaluon.gui.controllers.results.institutional;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.classes.institutions.Institution;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InstitutionListActivity extends ActionBarActivity
        implements Callback<ApiResponse<Institution[]>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutions);
    }

    @Override public void success(ApiResponse<Institution[]> apiResponse, Response response) {

    }

    @Override public void failure(RetrofitError error) {
        BoolException exception = (BoolException) BoolExceptionConverter.parse(error);
        ToastService.byResource(this, BoolException.ERROR_DICT.get(exception.getMessage()));
        finish();
    }
}
