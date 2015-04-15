package co.gov.inci.evaluon.gui.controllers.settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.EvalueesProxy;
import co.gov.inci.evaluon.backend.models.proxies.UserProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.backend.validators.MailValidator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UpdateUserActivity extends ActionBarActivity
        implements Callback<ApiResponse<User>>, View.OnClickListener {
    private String TAG;

    private EditText idNumber, firstName, lastName, birthday, email;
    private RadioGroup gender, disability, level, type;
    private Button send;
    private Evaluee user;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        TAG = getString(R.string.title_activity_update_profile);
        bindControls();
        retrieveUserData();
    }

    private void bindControls() {
        idNumber = (EditText)findViewById(R.id.text_id_number);
        idNumber.setEnabled(false);

        firstName = (EditText)findViewById(R.id.text_first_name);
        lastName = (EditText)findViewById(R.id.text_last_name);
        birthday = (EditText)findViewById(R.id.text_birthday);

        gender = (RadioGroup)findViewById(R.id.option_group_gender);
        disability = (RadioGroup)findViewById(R.id.option_group_disabilities);
        level = (RadioGroup)findViewById(R.id.option_group_levels);
        type = (RadioGroup)findViewById(R.id.option_group_types);

        email = (EditText)findViewById(R.id.text_mail);
        email.setOnFocusChangeListener(new MailValidator(this));

        send = (Button)findViewById(R.id.button_send);
        send.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        try {
            User updateUser = new Evaluee(
                    idNumber.getText().toString(),
                    firstName.getText().toString(),
                    user.getMiddleName(),
                    lastName.getText().toString(),
                    email.getText().toString(),
                    null,
                    new SimpleDateFormat("yyyy-MM-dd").parse(birthday.getText().toString()),
                    null
            );
            new UserProxy(this).update(updateUser, updateCallback);
        } catch (ParseException e) {
            // TODO: Birthday ParseException message
        }
    }

    public void retrieveUserData() {
        new UserProxy(this).retrieve(this);
    }

    @Override public void success(ApiResponse<User> userApiResponse, Response response) {
        user = (Evaluee)userApiResponse.getData();
        idNumber.setText(user.getId());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());

        final Date userBirthday = user.getBirthday();
        birthday.setText(new SimpleDateFormat("yyyy-MM-dd").format(userBirthday));

        gender.check(Evaluee.genders[((Evaluee)user).getEvaluee().getGender()]);
        disability.check(Evaluee.disabilities[((Evaluee)user).getEvaluee().getDisability()]);
        type.check(Evaluee.types[((Evaluee)user).getEvaluee().getType()]);
        level.check(Evaluee.levels[((Evaluee)user).getEvaluee().getLevel()]);

        email.setText(user.getEmail());
    }

    @Override public void failure(RetrofitError error) {
        ToastService.byResource(this, R.string.message_client_request_failed);
        finish();
    }

    Callback<ApiResponse<User>> updateCallback = new Callback<ApiResponse<User>>() {
        @Override public void success(ApiResponse<User> userApiResponse, Response response) {
            Evaluee.Info evaluee = user.getEvaluee();
            evaluee.setGender(Evaluee.genderById(gender.getCheckedRadioButtonId()));
            evaluee.setDisability(Evaluee.disabilitiesById(disability.getCheckedRadioButtonId()));
            evaluee.setType(Evaluee.typesById(type.getCheckedRadioButtonId()));
            evaluee.setLevel(Evaluee.levelsById(level.getCheckedRadioButtonId()));
            new EvalueesProxy(UpdateUserActivity.this).update(evaluee, evalueeUpdateCallback);
        }

        @Override public void failure(RetrofitError error) {
            ToastService.byResource(UpdateUserActivity.this, R.string.message_request_failed);
            Log.e(TAG, "", BoolExceptionConverter.parse(error));
        }
    };

    Callback<ApiResponse<Void>> evalueeUpdateCallback = new Callback<ApiResponse<Void>>() {
        @Override public void success(ApiResponse<Void> userApiResponse, Response response) {
            ToastService.success(UpdateUserActivity.this);
            finish();
        }

        @Override public void failure(RetrofitError error) {
            ToastService.error(UpdateUserActivity.this, error);
            Log.e(TAG, "", BoolExceptionConverter.parse(error));
        }
    };

}
