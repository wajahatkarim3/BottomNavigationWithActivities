package com.wajahatkarim3.bottomnavactivities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BottomActivityWithCenter extends AppCompatActivity {

    BottomNavigationViewEx bnve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_with_center);

        bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);

        int centerPosition = 2;
        // attention: you must ensure the center menu item title is empty
        // make icon bigger at centerPosition
        bnve.setIconSizeAt(centerPosition, 36, 36);
        bnve.getBottomNavigationItemView(2).setIcon(getResources().getDrawable(R.drawable.ic_plus_icon));



        // you could set a listener for bnve. and return false when click the center item so that it won't be checked.
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_plus) {
                    //Toast.makeText(BottomActivityWithCenter.this, "add", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BottomActivityWithCenter.this, "add", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
