package com.example.sargam.ptsddetector;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class result_activity extends AppCompatActivity {

    TextView tv;
    ProSwipeButton proSwipeBtn;
    ProSwipeButton proSwipeBtn2;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activity);

        tv = findViewById(R.id.tv);
        linearLayout = findViewById(R.id.linearLayout);

        //Receiving value from the previous activity
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("SCORE");

        if(score > 42){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.unsafe));
            tv.setTextColor(Color.BLACK);
        }else{
            linearLayout.setBackgroundColor(getResources().getColor(R.color.safe));
            tv.setTextColor(Color.WHITE);
        }

        //Setting the score to textView
        tv.setText("Score : "+score);

        proSwipeBtn = (ProSwipeButton) findViewById(R.id.awesome_btn);
        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        Intent i = new Intent(result_activity.this, Score_activity.class);
                        startActivity(i);
                        proSwipeBtn.showResultIcon(true); // false if task failed
                    }
                }, 1000);
            }
        });

        proSwipeBtn2 = (ProSwipeButton) findViewById(R.id.awesome_btn2);
        proSwipeBtn2.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        Intent i=new Intent(result_activity.this,SuggestActivity.class);
                        startActivity(i);
                        proSwipeBtn2.showResultIcon(true); // false if task failed
                    }
                }, 1000);
            }
        });


    }
}
