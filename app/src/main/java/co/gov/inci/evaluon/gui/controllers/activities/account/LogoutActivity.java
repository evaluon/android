package co.gov.inci.evaluon.gui.controllers.activities.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import co.gov.inci.evaluon.backend.services.accounts.helpers.AccountHelper;

/**
 * Created by pandres95 on 3/31/15.
 */
public class LogoutActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AccountHelper.removeAccount(this);
        Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
