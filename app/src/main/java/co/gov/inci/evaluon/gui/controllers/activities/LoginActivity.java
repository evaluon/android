package co.gov.inci.evaluon.gui.controllers.activities;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.Token;
import co.gov.inci.evaluon.backend.models.proxies.AuthenticationProxy;
import co.gov.inci.evaluon.backend.services.accounts.Constants;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.backend.services.security.StringHasher;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class LoginActivity extends AccountAuthenticatorActivity
        implements View.OnClickListener, Callback<Token> {
    private String TAG = "LoginActivity";

    public static final String PARAM_AUTHTOKEN_TYPE = "auth.token";
    public static final String EXTRA_REQUEST_CODE = "req.code";

    public static final int REQ_CODE_CREATE = 1;
    public static final int REQ_CODE_UPDATE = 2;

    private String username;
    private String password;
    private String accountType;

    private Button loginButton;
    private Button registerButton;
    private EditText emailField, passwordField;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.button_authenticate);
        registerButton = (Button) findViewById(R.id.button_register);

        emailField = (EditText) findViewById(R.id.text_mail);
        passwordField = (EditText) findViewById(R.id.text_password);

        setListeners();

    }

    private void setListeners() {
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_authenticate:
                login();
                break;
            case R.id.button_register:
                /*Intent i = new Intent(this, RegisterActivity.class);
                startActivityForResult(i, Constants.REQUEST_CODE_REGISTER);
                break;*/
        }
    }

    private void login() {
        username = emailField.getText().toString();
        password = passwordField.getText().toString();

        if(!username.equals("") && !password.equals("")){
            try {
                password = StringHasher.SHA1(password);
            } catch (NoSuchAlgorithmException e) {
                ToastService.byResource(this, R.string.register_failed);
            }
            loginButton.setEnabled(false);
            new AuthenticationProxy(this).password(username, password, this);
        } else {
            ToastService.byResource(this, R.string.missing_fields);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case Constants.REQUEST_CODE_REGISTER:
                Token token = (Token) data.getSerializableExtra("userToken");
                username = data.getStringExtra("username");
                success(token, null);
                break;
        }
    }

    @Override public void success(Token token, Response response) {

        accountType = this.getIntent().getStringExtra(PARAM_AUTHTOKEN_TYPE);
        if (accountType == null) {
            accountType = Constants.ACCOUNT_TYPE;
        }

        AccountManager accMgr = AccountManager.get(this);

        // This is the magic that adds the account to the Android Account Manager
        final Account account = new Account(username, accountType);
        accMgr.addAccountExplicitly(account, token.getAccessToken(), null);

        // Now we tell our caller, could be the Android Account Manager or even our own application
        // that the process was successful
        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
        intent.putExtra(AccountManager.KEY_AUTHTOKEN, accountType);
        setAccountAuthenticatorResult(intent.getExtras());

        setResult(RESULT_OK, intent);
        finish();

    }

    @Override public void failure(RetrofitError error) {
        loginButton.setEnabled(true);
        ToastService.byResource(this, R.string.register_failed);
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

}