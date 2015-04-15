package co.gov.inci.evaluon.gui.controllers.tests.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import co.gov.inci.evaluon.backend.models.adapters.TestMenuItem;
import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.proxies.TestsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.gui.controllers.tests.exams.KnowledgeAreasActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SelfTestListActivity extends TestListActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askForPassword = false;
        new TestsProxy(this).getSelfTest(getTestsCallback);
    }



    private Callback<ApiResponse<Test[]>> getTestsCallback = new Callback<ApiResponse<Test[]>>() {
        @Override public void success(ApiResponse<Test[]> apiResponse, Response response) {
            SelfTestListActivity.this.success(apiResponse, response);
        }

        @Override public void failure(RetrofitError error) {
            Log.e("SelfTest", error.getUrl(), BoolExceptionConverter.parse(error));
            new TestsProxy(SelfTestListActivity.this).createSelfTest(newSelfTestCallback);
        }
    };

    private Callback<ApiResponse<Test>> newSelfTestCallback = new Callback<ApiResponse<Test>>() {
        @Override public void success(ApiResponse<Test> apiResponse, Response response) {
            Intent intent = new Intent(SelfTestListActivity.this, KnowledgeAreasActivity.class);
            intent.putExtra("id", apiResponse.getData().getId());
            startActivity(intent);
            finish();
        }

        @Override public void failure(RetrofitError error) {
            SelfTestListActivity.this.failure(error);
        }
    };

}
