package co.gov.inci.evaluon.gui.controllers.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.authentication.Token;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.AuthenticationProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.accounts.helpers.ClientToken;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.backend.services.security.StringHasher;
import co.gov.inci.evaluon.backend.validators.FieldValidator;
import co.gov.inci.evaluon.backend.validators.text.DateValidator;
import co.gov.inci.evaluon.backend.validators.text.MailValidator;
import co.gov.inci.evaluon.backend.validators.text.NameValidator;
import co.gov.inci.evaluon.backend.validators.text.PasswordValidator;
import co.gov.inci.evaluon.backend.validators.text.RepeatPasswordValidator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends ActionBarActivity
        implements Callback<ApiResponse<User>>, View.OnClickListener {

    private EditText idNumber, firstName, lastName, birthday, email, password, repeatPassword;
    private RadioGroup gender, disability, level, type;
    private Button send;
    private Evaluee user;
    private Token userToken;
    private AuthenticationProxy api;
    private ArrayList<FieldValidator> validators;
    private ProgressDialog progressDialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindControls();
        bindValidators();
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

        send = (Button)findViewById(R.id.button_send);
        send.setOnClickListener(this);
    }

    private void bindValidators() {
        validators = new ArrayList<>();

        // First name validator
        NameValidator firstNameValidator = new NameValidator(
                this, firstName, R.string.validation_first_name_required
        );
        firstName.addTextChangedListener(firstNameValidator);
        firstName.setOnFocusChangeListener(firstNameValidator);
        validators.add(firstNameValidator);

        // Last name validator
        NameValidator lastNameValidator = new NameValidator(
                this, lastName, R.string.validation_last_name_required
        );
        lastName.addTextChangedListener(lastNameValidator);
        lastName.setOnFocusChangeListener(lastNameValidator);
        validators.add(lastNameValidator);

        // Birthday validator
        DateValidator birthdayValidator = new DateValidator(
                this, birthday,
                R.string.validation_birthday_required,
                "yyyy-MM-dd"
        );
        birthday.addTextChangedListener(birthdayValidator);
        birthday.setOnFocusChangeListener(birthdayValidator);
        validators.add(birthdayValidator);

        // Email validator
        MailValidator emailValidator = new MailValidator(this, email);
        email.addTextChangedListener(emailValidator);
        email.setOnFocusChangeListener(emailValidator);
        validators.add(emailValidator);

        // Password validator
        PasswordValidator passwordValidator = new PasswordValidator(this, password, 6);
        password.addTextChangedListener(passwordValidator);
        password.setOnFocusChangeListener(passwordValidator);
        validators.add(passwordValidator);

        // Repeat password validator
        RepeatPasswordValidator repeatPasswordValidator = new RepeatPasswordValidator(
                this, repeatPassword, password
        );
        repeatPassword.addTextChangedListener(repeatPasswordValidator);
        repeatPassword.setOnFocusChangeListener(repeatPasswordValidator);
        validators.add(repeatPasswordValidator);
    }

    private void preValidate() throws Exception {

        ArrayList<String> messages = new ArrayList<>();

        for(FieldValidator validator : validators){
            try {
                if(!validator.check()) messages.add(validator.getError());
            } catch (Exception e) {
                messages.add(validator.getError());
            }
        }

        if(messages.size() > 0){
            StringBuilder builder = new StringBuilder();
            builder.append(getString(R.string.validation_errors) + "\n");
            int c = 1;
            for(String s : messages) {
                builder.append(c++ + ". " + s + "\n");
            }
            throw new Exception(builder.toString());
        }

    }

    @Override public void onClick(View v) {
        try {
            preValidate();
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
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(R.string.app_name);
            progressDialog.setMessage(getString(R.string.message_wait));
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            api.register(token.toString(), user, this);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override public void success(ApiResponse<User> userApiResponse, Response response) {
        api.password(user.getEmail(), user.getPassword(), loginCallback);
    }

    @Override public void failure(RetrofitError error) {
        progressDialog.dismiss();
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
            progressDialog.dismiss();
            finish();
        }

        @Override public void failure(RetrofitError error) {
            RegisterActivity.this.failure(error);
        }
    };

}
