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
    RevealFrameLayout mRevealLayout;

    int[] locations = new int[2];

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


        findViewById(R.id.rootLayout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                findViewById(R.id.rootLayout).getViewTreeObserver().removeGlobalOnLayoutListener(this);

                findViewById(R.id.fab).getLocationOnScreen(locations);
                int x = locations[0];
                int y = locations[1];
            }
        });



        findViewById(R.id.secondView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View myView = findViewById(R.id.secondView);
                myView.setVisibility(View.VISIBLE);
                myView.bringToFront();

                // get the center for the clipping circle
                int cx = (myView.getLeft() + myView.getRight()) / 2;
                int cy = (myView.getTop() + myView.getBottom()) / 2;

                cx = locations[0];
                cy = locations[1];

                // get the final radius for the clipping circle
                int dx = Math.max(cx, myView.getWidth() - cx);
                int dy = Math.max(cy, myView.getHeight() - cy);
                float finalRadius = (float) Math.hypot(dx, dy);

                // Android native animator
                Animator animator =
                        io.codetail.animation.ViewAnimationUtils.createCircularReveal(myView, locations[0], locations[1], finalRadius, 0);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(300);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        myView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                animator.start();

            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(BottomActivityWithCenter.this, "add", Toast.LENGTH_SHORT).show();

                /*
                mRevealLayout.next(locations[0], locations[1], new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        findViewById(R.id.secondView).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                */



                View myView = findViewById(R.id.secondView);
                myView.setVisibility(View.VISIBLE);
                //myView.bringToFront();

                // get the center for the clipping circle
                int cx = (myView.getLeft() + myView.getRight()) / 2;
                int cy = (myView.getTop() + myView.getBottom()) / 2;

                cx = locations[0];
                cy = locations[1];

                // get the final radius for the clipping circle
                int dx = Math.max(cx, myView.getWidth() - cx);
                int dy = Math.max(cy, myView.getHeight() - cy);
                float finalRadius = (float) Math.hypot(dx, dy);

                // Android native animator
                Animator animator =
                        io.codetail.animation.ViewAnimationUtils.createCircularReveal(myView, locations[0], locations[1], 0, finalRadius);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(300);
                animator.start();



            }
        });

        addSelectorLineAt(0);
        addSelectorLineAt(1);
        addSelectorLineAt(2);
        addSelectorLineAt(3);
        addSelectorLineAt(4);

        selectItemAt(bnve.getMenu().getItem(0));

        mRevealLayout = (RevealFrameLayout) findViewById(R.id.revealLayout);
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
