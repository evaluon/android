package co.gov.inci.evaluon.backend.models.proxies;

import android.content.Context;

import co.gov.inci.evaluon.backend.models.apisets.Users;
import co.gov.inci.evaluon.backend.models.classes.UserPassword;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;

public class UserProxy extends ApiProxy<Users> {

    public UserProxy(Context context) {
        super(context, Users.class, true);
    }

    public void retrieve(Callback<ApiResponse<User>> userCallback){
        api.retrieve(userCallback);
    }

    public void update(User user, Callback<ApiResponse<User>> userCallback){
        api.update(user, userCallback);
    }

    public void changePassword(UserPassword password, Callback<ApiResponse<User>> userCallback){
        api.changePassword(password, userCallback);
    }

}
