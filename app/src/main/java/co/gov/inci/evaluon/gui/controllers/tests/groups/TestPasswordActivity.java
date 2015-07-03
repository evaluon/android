package co.gov.inci.evaluon.gui.controllers.tests.groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.proxies.TestsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.gui.controllers.home.MainActivity;
import co.gov.inci.evaluon.gui.controllers.tests.exams.KnowledgeAreasActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TestPasswordActivity extends ActionBarActivity
        implements Callback<ApiResponse<Void>>, View.OnClickListener {

    private int testId;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_password);
        testId = getIntent().getIntExtra("id", 0);
        findViewById(R.id.button_send).setOnClickListener(this);
    }

    @Override public void onClick(View v) {

        String code = ((EditText) findViewById(R.id.text_password)).getText().toString();
        if(code.length() != 6) ToastService.byResource(this, R.string.error_invalid_hotp_code);
        else {
            Test t = new Test(Integer.parseInt(code));
            new TestsProxy(this).openTest(testId, t, this);
        }
    }

    @Override public void success(ApiResponse<Void> voidApiResponse, Response response) {
        Intent intent = new Intent(this, KnowledgeAreasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("id", testId);
        startActivity(intent);
        finish();
    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}
