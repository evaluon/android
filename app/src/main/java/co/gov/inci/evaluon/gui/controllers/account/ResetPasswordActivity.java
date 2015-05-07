package co.gov.inci.evaluon.gui.controllers.account;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.proxies.AuthenticationProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.accounts.helpers.ClientToken;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.backend.validators.text.MailValidator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ResetPasswordActivity extends ActionBarActivity
        implements View.OnClickListener, Callback<ApiResponse<Void>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        findViewById(R.id.button_reset).setOnClickListener(this);
    }

    @Override public void onClick(View v) {

        MailValidator validator = new MailValidator(this, findViewById(R.id.text_mail));

        try {
            if(validator.check()){
                Evaluee evaluee = new Evaluee();
                evaluee.setEmail(((TextView)findViewById(R.id.text_mail)).getText().toString());

                new AuthenticationProxy(this).resetPassword(
                        ClientToken.getToken().toString(),
                        evaluee,
                        this
                );
            } else {
                toastValidationError(validator.getError());
            }
        } catch (Exception e) {
            toastValidationError(validator.getError());
        }

    }

    public void toastValidationError(String error){
        Toast.makeText(
                this, String.format(
                        "%s\n%s",
                        getString(R.string.validation_errors),
                        error
                ), Toast.LENGTH_LONG
        ).show();
    }

    @Override public void success(ApiResponse<Void> voidApiResponse, Response response) {
        finish();
        ToastService.success(this);
    }

    @Override public void failure(RetrofitError error) {
        finish();
        ToastService.error(this, error);
    }
}
