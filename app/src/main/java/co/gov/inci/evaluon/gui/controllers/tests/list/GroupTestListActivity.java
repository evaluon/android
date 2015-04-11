package co.gov.inci.evaluon.gui.controllers.tests.list;

import android.os.Bundle;

import co.gov.inci.evaluon.backend.models.proxies.GroupsProxy;

public class GroupTestListActivity extends TestListActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GroupsProxy(this).getTests(getIntent().getIntExtra("id", 0), this);
    }
}
