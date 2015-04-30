package co.gov.inci.evaluon.gui.controllers.account;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.authentication.Token;
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
public class LoginActivity extends ActionBarActivity
        implements View.OnClickListener, Callback<Token> {
    private String TAG = "LoginActivity";

    private String username;
    private String password;

    private Button loginButton, registerButton, recoverButton;
    private EditText emailField, passwordField;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.button_authenticate);
        registerButton = (Button) findViewById(R.id.button_register);
        recoverButton = (Button) findViewById(R.id.button_reset);

        emailField = (EditText) findViewById(R.id.text_mail);
        passwordField = (EditText) findViewById(R.id.text_password);

        setListeners();

    }

    private void setListeners() {
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        recoverButton.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_authenticate: {
                login();
                break;
            }
            case R.id.button_register: {
                Intent i = new Intent(this, RegisterActivity.class);
                startActivityForResult(i, 0);
                break;
            }
            case R.id.button_reset: {
                Intent i = new Intent(this, ResetPasswordActivity.class);
                startActivity(i);
                break;
            }
        }
    }

    private void login() {
        username = emailField.getText().toString();
        password = passwordField.getText().toString();

        if(!username.equals("") && !password.equals("")){
            try {
                password = StringHasher.SHA1(password);
            } catch (NoSuchAlgorithmException e) {
                ToastService.byResource(this, R.string.message_register_failed);
            }
            loginButton.setEnabled(false);
            new AuthenticationProxy(this).password(username, password, this);
        } else {
            ToastService.byResource(this, R.string.message_missing_fields);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            username = data.getStringExtra("username");
            password = data.getStringExtra("password");
            Token token = (Token) data.getSerializableExtra("token");
            success(token, null);
        }
    }

    @Override public void success(Token token, Response response) {

        Intent intent = new Intent();
        intent.putExtra("token", token);
        intent.putExtra("username", username);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override public void failure(RetrofitError error) {
        loginButton.setEnabled(true);
        ToastService.error(this, error);
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

}