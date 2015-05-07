package co.gov.inci.evaluon.backend.models.converters;

import co.gov.inci.evaluon.backend.models.classes.authentication.Token;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.RetrofitError;

public class BoolExceptionConverter {

    public static Exception parse(RetrofitError error){
        if (error.getBody() != null){

            if(error.getBody() instanceof Token){

                Token errorToken = ((Token)error.getBody());
                return new BoolException(errorToken.getErrorDescription(), errorToken.getError());

            } else return ((ApiResponse<Void>)error.getBody()).getError() != null ?
                        ((ApiResponse<Void>)error.getBody()).getError() :
                        null;

        } else return null;
    }

}
