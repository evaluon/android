package co.gov.inci.evaluon.gui.controllers.evaluations;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.MenuItem;
import co.gov.inci.evaluon.backend.models.adapters.TestMenuItem;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.proxies.GroupsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.gui.adapters.listadapters.TestMenuListAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GroupTestsActivity extends ActionBarActivity implements Callback<ApiResponse<Test[]>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_tests);
        new GroupsProxy(this).getActiveTests(getIntent().getIntExtra("id", 0), this);
    }

    @Override public void success(ApiResponse<Test[]> apiResponse, Response response) {
        Test[] tests = apiResponse.getData();
        MenuItem[] items = new MenuItem[tests.length];

        for(int i = 0; i < tests.length; i++){
            Intent intent = new Intent(this, TestPasswordActivity.class);
            intent.putExtra("id", tests[i].getId());
            items[i] = new TestMenuItem(tests[i].getDescription(), intent);
        }

        ((ListView)findViewById(R.id.tests_list_view)).setAdapter(
                new TestMenuListAdapter(this, items)
        );
    }

    @Override public void failure(RetrofitError error) {
        BoolException exception = (BoolException) BoolExceptionConverter.parse(error);
        ToastService.byResource(this, BoolException.ERROR_DICT.get(exception.getMessage()));
        finish();
    }
}
