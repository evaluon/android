package co.gov.inci.evaluon.gui.controllers.activities;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.Token;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.AuthenticationProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.accounts.helpers.AccountHelper;
import co.gov.inci.evaluon.backend.services.accounts.helpers.ClientToken;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class SplashActivity extends ActionBarActivity implements Callback<ApiResponse<User>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new AuthenticationProxy(this).clientCredentials(clientCredentialsRequest);
    }

    Callback<Token> clientCredentialsRequest = new Callback<Token>() {
        @Override public void success(Token token, Response response) {
            try {
                ClientToken.getToken(token);
                if(AccountHelper.hasAccount(SplashActivity.this)){
                    getUser();
                } else {
                    AccountHelper.addAccount(SplashActivity.this, new AccountManagerCallback<Bundle>() {
                        @Override
                        public void run(AccountManagerFuture<Bundle> bundleAccountManagerFuture) {
                            if (bundleAccountManagerFuture.isCancelled()) {
                                finish();
                            } else {
                                getUser();
                            }
                        }
                    });
                }
            } catch(Exception ex){
                ToastService.byResource(SplashActivity.this, R.string.message_client_register_failed);
            }
        }

        @Override public void failure(RetrofitError error) {
            ToastService.byResource(SplashActivity.this, R.string.message_client_request_failed);
        }
    };

    @Override public void success(ApiResponse<User> userApiResponse, Response response) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override public void failure(RetrofitError error) {
        finish();
    }


    public void getUser() {
        success(null, null);
    }
}
