package co.gov.inci.evaluon.backend.models.proxies;

import android.content.Context;

import co.gov.inci.evaluon.backend.models.apisets.Authentication;
import co.gov.inci.evaluon.backend.models.classes.authentication.Token;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class AuthenticationProxy extends ApiProxy<Authentication> {

    public AuthenticationProxy(Context context){
        super(context, Authentication.class, false);
    }

    public void password(String username, String password, Callback<Token> callback){
        api.password("password", clientId, clientSecret, username, password, callback);
    }

    public void clientCredentials(Callback<Token> callback){
        api.clientCredentials("client_credentials", clientId, clientSecret, callback);
    }

    public void register(String authorization, User user, Callback<ApiResponse<User>> callback){
        api.register(authorization, user, callback);
    }

    public void createEvaluee(String authorization, Evaluee.Info evaluee,
                              Callback<ApiResponse<Void>> callback){
        api.createEvaluee(authorization, evaluee, callback);
    }

}
