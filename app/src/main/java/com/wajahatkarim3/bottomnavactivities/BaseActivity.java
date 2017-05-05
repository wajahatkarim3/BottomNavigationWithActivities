package com.wajahatkarim3.bottomnavactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int prevNav = getSelectedNav();
            int currentNav = item.getItemId();
            if (currentNav == prevNav)
                return false;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent ii = new Intent(BaseActivity.this, HomeActivity.class);
                    startActivity(ii);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_dashboard:
                    if (prevNav != R.id.navigation_home)
                        finish();
                    Intent ii2 = new Intent(BaseActivity.this, DashboardActivity.class);
                    startActivity(ii2);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_notifications:
                    if (prevNav != R.id.navigation_home)
                        finish();
                    Intent ii3 = new Intent(BaseActivity.this, NotificationsActivity.class);
                    startActivity(ii3);
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        }

    };

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public View setContentLayout(int layoutID)
    {
        FrameLayout contentLayout = (FrameLayout) findViewById(R.id.content_layout);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutID, contentLayout, true);
    }

    public void setSelected(int optionID)
    {
        navigationView.getMenu().findItem(optionID).setChecked(true);
        getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().putInt("selectedNav",optionID).commit();
    }

    public int getSelectedNav()
    {
        return getSharedPreferences(getPackageName(), MODE_PRIVATE).getInt("selectedNav", R.id.navigation_home);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}
