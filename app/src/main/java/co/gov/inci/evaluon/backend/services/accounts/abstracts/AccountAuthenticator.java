package co.gov.inci.evaluon.backend.services.accounts.abstracts;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.services.accounts.Constants;
import co.gov.inci.evaluon.gui.controllers.activities.LoginActivity;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class AccountAuthenticator extends AbstractAccountAuthenticator {

    private Context context;

    public AccountAuthenticator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                 String s) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String accountType, String authTokenType, String[] requiredFeatures,
                             Bundle bundle) throws NetworkErrorException {

        final Bundle result = new Bundle();

        if(AccountManager.get(context).getAccountsByType(Constants.ACCOUNT_TYPE).length > 0){

            result.putInt(AccountManager.KEY_ERROR_CODE, 1);
            result.putString(
                    AccountManager.KEY_ERROR_MESSAGE,
                    context.getString(R.string.message_one_account_allowed)
            );

            return result;

        } else {

            final Intent intent;

            intent = new Intent(context, LoginActivity.class);
            intent.putExtra(LoginActivity.PARAM_AUTHTOKEN_TYPE, authTokenType);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            intent.putExtra(LoginActivity.EXTRA_REQUEST_CODE, LoginActivity.REQ_CODE_CREATE);

            result.putParcelable(AccountManager.KEY_INTENT, intent);

            return result;
        }

    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                     Account account, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse,
                               Account account, String s,
                               Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public String getAuthTokenLabel(String s) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                    Account account, String s,
                                    Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse,
                              Account account, String[] strings) throws NetworkErrorException {
        return null;
    }

}
