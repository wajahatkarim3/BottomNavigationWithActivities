package com.wajahatkarim3.bottomnavactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        setContentLayout(R.layout.activity_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelected(R.id.navigation_home);
    }
}
