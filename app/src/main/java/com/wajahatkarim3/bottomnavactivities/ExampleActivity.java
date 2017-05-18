package com.wajahatkarim3.bottomnavactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class ExampleActivity extends PlusMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_example);
        setContentLayout(R.layout.activity_example);



    }

    public void onReviewClick(View view)
    {
        showMenu(false);
    }
}
