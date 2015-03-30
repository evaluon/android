package co.gov.inci.evaluon.backend.models.apisets;

import co.gov.inci.evaluon.backend.models.classes.Token;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

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

}