package co.gov.inci.evaluon.gui.controllers.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.authentication.Token;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.AuthenticationProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.accounts.helpers.ClientToken;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.backend.services.security.StringHasher;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends ActionBarActivity
        implements Callback<ApiResponse<User>>, View.OnClickListener, TextWatcher {

    private EditText idNumber, firstName, lastName, birthday, email, password, repeatPassword;
    private RadioGroup gender, disability, level, type;
    private Button send;
    private Evaluee user;
    private Token userToken;
    private AuthenticationProxy api;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindControls();
        api = new AuthenticationProxy(this);
    }

    private void bindControls() {
        idNumber = (EditText)findViewById(R.id.text_id_number);

        firstName = (EditText)findViewById(R.id.text_first_name);
        lastName = (EditText)findViewById(R.id.text_last_name);
        birthday = (EditText)findViewById(R.id.text_birthday);

        gender = (RadioGroup)findViewById(R.id.option_group_gender);
        disability = (RadioGroup)findViewById(R.id.option_group_disabilities);
        level = (RadioGroup)findViewById(R.id.option_group_levels);
        type = (RadioGroup)findViewById(R.id.option_group_types);

        email = (EditText)findViewById(R.id.text_mail);
        password = (EditText)findViewById(R.id.text_password);
        repeatPassword = (EditText)findViewById(R.id.text_repeat_password);
        repeatPassword.addTextChangedListener(this);

        send = (Button)findViewById(R.id.button_send);
        send.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        try {
            Token token = ClientToken.getToken();
            user = new Evaluee(
                    idNumber.getText().toString(),
                    firstName.getText().toString(),
                    "",
                    lastName.getText().toString(),
                    email.getText().toString(),
                    StringHasher.SHA1(password.getText().toString()),
                    new SimpleDateFormat("yyyy-MM-dd").parse(birthday.getText().toString()),
                    null
            );
            api.register(token.toString(), user, this);
        } catch (Exception e) {
            // TODO: Birthday ParseException message
        }
    }

    @Override public void success(ApiResponse<User> userApiResponse, Response response) {
        api.password(user.getEmail(), user.getPassword(), loginCallback);
    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
    }

    private Callback<Token> loginCallback = new Callback<Token>() {
        @Override public void success(Token token, Response response) {
            userToken = token;
            Evaluee.Info evaluee = new Evaluee.Info(
                    Evaluee.genderById(gender.getCheckedRadioButtonId()),
                    Evaluee.disabilitiesById(disability.getCheckedRadioButtonId()),
                    Evaluee.typesById(type.getCheckedRadioButtonId()),
                    Evaluee.levelsById(level.getCheckedRadioButtonId())
            );
            api.createEvaluee(token.toString(), evaluee, evalueeCallback);
        }

        @Override public void failure(RetrofitError error) {
            RegisterActivity.this.failure(error);
        }

    };

    private Callback<ApiResponse<Void>> evalueeCallback = new Callback<ApiResponse<Void>>() {
        @Override public void success(ApiResponse<Void> voidApiResponse, Response response) {
            Intent intent = new Intent();
            intent.putExtra("username", user.getEmail());
            intent.putExtra("password", user.getPassword());
            intent.putExtra("token", userToken);
            setResult(RESULT_OK, intent);
        }

        @Override public void failure(RetrofitError error) {
            RegisterActivity.this.failure(error);
        }
    };

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override public void afterTextChanged(Editable s) {
        if(!password.getText().toString().equals("")){
            if(repeatPassword.getText().toString().equals(password.getText().toString())){
                send.setEnabled(true);
                return;
            }
        }
        send.setEnabled(false);
    }
}
