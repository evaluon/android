package co.gov.inci.evaluon.gui.controllers.tests.list;

import android.os.Bundle;
import co.gov.inci.evaluon.backend.models.proxies.TestsProxy;

public class SelfTestListActivity extends TestListActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askForPassword = false;
        new TestsProxy(this).getSelfTest(this);
    }

}
