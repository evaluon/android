package co.gov.inci.evaluon.backend.services.accounts.helpers;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import co.gov.inci.evaluon.backend.services.accounts.Constants;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class AccountHelper {


    public static boolean hasAccount(Context context){
        final AccountManager accountManager = AccountManager.get(context);
        return accountManager.getAccountsByType(Constants.ACCOUNT_TYPE).length > 0;
    }

    public static void addAccount(Activity activity, AccountManagerCallback<Bundle> callback){
        final AccountManager accountManager = AccountManager.get(activity);
        accountManager.addAccount(Constants.ACCOUNT_TYPE, Constants.AUTHTOKEN_TYPE, null, null,
                activity, callback, null);
    }

    public static Account getAccount(Context context){
        final AccountManager accountManager = AccountManager.get(context);
        return accountManager.getAccountsByType(Constants.ACCOUNT_TYPE)[0];
    }

    public static String getPassword(Context context){
        final AccountManager accountManager = AccountManager.get(context);
        return accountManager.getPassword(getAccount(context));
    }

    public static void removeAccount(Context context) {
        final AccountManager accountManager = AccountManager.get(context);
        accountManager.removeAccount(getAccount(context), null, null);
    }

}
