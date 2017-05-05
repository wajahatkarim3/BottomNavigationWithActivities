package com.wajahatkarim3.bottomnavactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dashboard);
        setContentLayout(R.layout.activity_dashboard);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelected(R.id.navigation_dashboard);
    }
}
