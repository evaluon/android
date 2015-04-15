package co.gov.inci.evaluon.backend.services.accounts.helpers;

import co.gov.inci.evaluon.backend.models.classes.authentication.Token;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class ClientToken extends Token {

    private static ClientToken instance;

    private ClientToken(Token token){
        super(token);
        instance = this;
    }

    public static Token getToken(){
        return instance;
    }

    public static Token getToken(Token token){
        return new ClientToken(token);
    }



}
