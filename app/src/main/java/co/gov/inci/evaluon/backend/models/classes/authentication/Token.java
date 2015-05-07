package co.gov.inci.evaluon.backend.models.classes.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true) public class Token implements Serializable {

    @JsonProperty("access_token") private String accessToken;
    @JsonProperty("refresh_token") private String refreshToken;
    @JsonProperty("token_type") private String tokenType;
    @JsonProperty("error") private String error;
    @JsonProperty("error_description") private String errorDescription;

    public Token() {
        this("", "", "", "", "");
    }

    public Token(Token token){
        this(
                token.getAccessToken(),
                token.getRefreshToken(),
                token.getTokenType(),
                token.getError(),
                token.getErrorDescription()
        );
    }

    public Token(String accessToken, String refreshToken, String tokenType, String error,
                 String errorDescription) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    @Override public String toString(){
        return String.format("%s %s", getTokenType(), getAccessToken());
    }

}
