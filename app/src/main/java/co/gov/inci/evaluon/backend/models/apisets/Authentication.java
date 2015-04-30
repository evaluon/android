package co.gov.inci.evaluon.backend.models.apisets;

import co.gov.inci.evaluon.backend.models.adapters.RESET;
import co.gov.inci.evaluon.backend.models.classes.authentication.Token;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */
public interface Authentication {

    @FormUrlEncoded @POST("/auth/token")
    public void clientCredentials(@Field("grant_type") String grantType,
                                  @Field("client_id") String clientId,
                                  @Field("client_secret") String clientSecret,
                                  Callback<Token> callback);

    @FormUrlEncoded @POST("/auth/token")
    public void password(@Field("grant_type") String grantType,
                         @Field("client_id") String clientId,
                         @Field("client_secret") String clientSecret,
                         @Field("username") String username,
                         @Field("password") String password,
                         Callback<Token> callback);

    @POST("/user") public void register(
            @Header("Authorization") String authorization,
            @Body User user,
            Callback<ApiResponse<User>> callback
    );

    @PUT("/evaluee") public void createEvaluee(
            @Header("Authorization") String authorization,
            @Body Evaluee.Info evaluee,
            Callback<ApiResponse<Void>> evalueeCallback
    );

    @RESET("/user") public void resetPassword(
            @Header("Authorization") String authorization,
            @Body User user,
            Callback<ApiResponse<Void>> callback
    );

}