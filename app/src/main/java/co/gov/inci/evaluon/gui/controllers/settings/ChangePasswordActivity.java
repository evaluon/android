package co.gov.inci.evaluon.gui.controllers.settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.UserProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.backend.services.security.StringHasher;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChangePasswordActivity extends ActionBarActivity
        implements View.OnClickListener, Callback<ApiResponse<User>> {
    private String TAG;

    private EditText newPassword, repeatPassword;
    private Button send;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getString(R.string.title_activity_change_password);
        setContentView(R.layout.activity_change_password);
        bindControls();
    }

    private void bindControls() {
        newPassword = (EditText)findViewById(R.id.text_new_password);
        repeatPassword = (EditText)findViewById(R.id.text_repeat_password);
        send = (Button)findViewById(R.id.button_send);
        send.setOnClickListener(this);
    }

    @Override public void onClick(View v) {

        try {
            Evaluee user = new Evaluee();
            if(newPassword.getText().toString().equals(repeatPassword.getText().toString())){
                user.setPassword(StringHasher.SHA1(newPassword.getText().toString()));
                new UserProxy(this).update(user, this);
            } else {
                // TODO: Passwords don't match message
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO: Error message
        }

    }

    @Override public void success(ApiResponse<User> userApiResponse, Response response) {
        ToastService.success(this);
        finish();
    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
        finish();
    }
}
