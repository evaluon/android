package co.gov.inci.evaluon.backend.services.accounts.activities;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;

import co.gov.inci.evaluon.backend.models.classes.authentication.Token;
import co.gov.inci.evaluon.backend.services.accounts.Constants;
import co.gov.inci.evaluon.gui.controllers.account.LoginActivity;

public class Authenticator extends AccountAuthenticatorActivity {

    public static final String PARAM_AUTHTOKEN_TYPE = "auth.token";
    public static final String EXTRA_REQUEST_CODE = "req.code";
    private String accountType;

    @Override protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        startActivityForResult(new Intent(this, LoginActivity.class), 0);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != RESULT_OK){
            setResult(RESULT_CANCELED);
            finish();
            return;
        }

        Token token = (Token)data.getSerializableExtra("token");
        String username = data.getStringExtra("username");

        accountType = this.getIntent().getStringExtra(PARAM_AUTHTOKEN_TYPE);
        if (accountType == null) {
            accountType = Constants.ACCOUNT_TYPE;
        }

        AccountManager accMgr = AccountManager.get(this);

        // This is the magic that adds the account to the Android Account Manager
        final Account account = new Account(username, accountType);
        accMgr.addAccountExplicitly(account, token.getAccessToken(), null);

        // Now we tell our caller, could be the Android Account Manager or even our own application
        // that the process was successful
        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
        intent.putExtra(AccountManager.KEY_AUTHTOKEN, accountType);
        setAccountAuthenticatorResult(intent.getExtras());

        setResult(RESULT_OK, intent);
        finish();

    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
