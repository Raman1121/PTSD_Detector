package com.example.sargam.ptsddetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import io.rmiri.buttonloading.ButtonLoading;

public class result_activity extends AppCompatActivity {

    TextView tv;
    Button bttn;
    ButtonLoading buttonLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activity);

        tv = findViewById(R.id.tv);
        //bttn = findViewById(R.id.analytics_bttn);

        //Receiving value from the previous activity
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("SCORE");

        //Setting the score to textView
        //tv.setText(score);

        Toast.makeText(this, ""+ score, Toast.LENGTH_SHORT).show();

        buttonLoading = findViewById(R.id.bttnLoading);
        buttonLoading.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
            @Override
            public void onClick() {
                Intent i = new Intent(result_activity.this, Score_activity.class);
                startActivity(i);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }
        });

//        bttn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(result_activity.this, Score_activity.class);
//                startActivity(i);
//            }
//        });


    }
}
