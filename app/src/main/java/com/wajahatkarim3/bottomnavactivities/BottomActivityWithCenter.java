package com.wajahatkarim3.bottomnavactivities;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.wajahatkarim3.bottomnavactivities.custom.RevealLayout;

import io.codetail.widget.RevealFrameLayout;

import static android.R.attr.lockTaskMode;
import static android.R.attr.logo;
import static android.R.attr.shape;
import static com.wajahatkarim3.bottomnavactivities.R.id.bnve;
import static com.wajahatkarim3.bottomnavactivities.R.id.revealLayout;
import static com.wajahatkarim3.bottomnavactivities.R.id.text;

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
                selectItemAt(item);
                return true;
            }
        });


        addSelectorLineAt(0);
        addSelectorLineAt(1);
        addSelectorLineAt(2);
        addSelectorLineAt(3);
        addSelectorLineAt(4);

        selectItemAt(bnve.getMenu().getItem(0));
    }

    public void addSelectorLineAt(int position)
    {
        //bnve.getBottomNavigationItemView(position).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        View v = new View(this);
        v.setId(R.id.line_view_id);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                10
        );
        params.gravity = Gravity.BOTTOM;
        v.setLayoutParams(params);
        v.setBackgroundColor(Color.parseColor("#cb202d"));
        v.setVisibility(View.INVISIBLE);
        bnve.getBottomNavigationItemView(position).addView(v);
    }

    public void selectItemAt(MenuItem item)
    {
        int position = bnve.getMenuItemPosition(item);
        hidePreviousLines();
        bnve.getBottomNavigationItemView(position).findViewById(R.id.line_view_id).setVisibility(View.VISIBLE);
    }

    public void hidePreviousLines()
    {
        for (int i=0; i<bnve.getItemCount(); i++)
        {
            bnve.getBottomNavigationItemView(i).findViewById(R.id.line_view_id).setVisibility(View.INVISIBLE);
        }
    }

}
