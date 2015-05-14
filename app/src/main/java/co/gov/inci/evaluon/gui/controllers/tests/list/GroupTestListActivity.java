package co.gov.inci.evaluon.gui.controllers.tests.list;

import android.os.Bundle;

import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.proxies.GroupsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GroupTestListActivity extends TestListActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GroupsProxy(this).getActiveTests(getIntent().getIntExtra("id", 0), this);
    }

}
