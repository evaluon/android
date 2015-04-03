package co.gov.inci.evaluon.gui.controllers.activities.settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.Evaluee;
import co.gov.inci.evaluon.backend.models.classes.Student;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.UserProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UpdateUserActivity extends ActionBarActivity implements Callback<ApiResponse<User>> {

    EditText idNumber, firstName, lastName, birthday;
    RadioGroup gender, disability;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        bindControls();
        retrieveUserData();
    }

    private void bindControls() {
        idNumber = (EditText)findViewById(R.id.id_number);
        idNumber.setEnabled(false);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        birthday = (EditText)findViewById(R.id.birthday);
        gender = (RadioGroup)findViewById(R.id.gender_options);
        disability = (RadioGroup)findViewById(R.id.disability_options);
    }


    public void retrieveUserData() {
        new UserProxy(this).retrieve(this);
    }

    @Override public void success(ApiResponse<User> userApiResponse, Response response) {
        User user = userApiResponse.getData();
        idNumber.setText(user.getId());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        birthday.setText(new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
        gender.check(Evaluee.genders[((Student)user).getEvaluee().getGender()]);
        disability.check(Evaluee.disabilities[((Student)user).getEvaluee().getDisability()]);
    }

    @Override public void failure(RetrofitError error) {

    }
}
