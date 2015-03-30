package co.gov.inci.evaluon.backend.services.accounts;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import co.gov.inci.evaluon.backend.services.accounts.abstracts.AccountAuthenticator;

public class Authenticator extends Service {

    @Override public IBinder onBind(Intent intent) {
        return new AccountAuthenticator(this).getIBinder();
    }

}
