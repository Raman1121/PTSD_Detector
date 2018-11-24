package com.example.sargam.ptsddetector;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DisplayActivity2 extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    TextView signout;
    ListView lv;
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> selected=new ArrayList<String>();
    static int score=0;
    Button Submit;





    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display2);

        lv = findViewById(R.id.rv);
        Submit=findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "myfile";

                FileOutputStream outputStream;

                try {
                    FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(selected.get(0));
                    outputWriter.close();

                    //display file saved message
                    Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();
                    /*outputStream = openFileOutput(filename, Context.MODE_APPEND);
                    for (String s : selected) {
                        outputStream.write(s.getBytes());
                    }
                    outputStream.close();*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String temp = getApplicationContext().getFilesDir().getAbsolutePath();
                Toast.makeText(DisplayActivity2.this, temp, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(DisplayActivity2.this, Score_activity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("SCORE", score);
                i.putExtras(bundle);
                startActivity(i);
                //Toast.makeText(DisplayActivity2.this, ""+score, Toast.LENGTH_SHORT).show();
            }
        });




        signout=findViewById(R.id.signOut);

        setupList();

        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null)
                {
                    Intent i=new Intent(DisplayActivity2.this,MainActivity.class);
                    startActivity(i);
                }

            }
        };

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();



            }
        });



        questions.add("Having Upsetting thought or images about the traumatic event that come into your head when you did not want them to.");
        questions.add("Having bad dreams or nightmares about the event.");
        questions.add("Reliving the traumatic event");
        questions.add("Feeling emotionally upset when you are reminded of the traumatic event.");
        questions.add("Experiencing physical reactions when you are reminded of the traumatic event (sweating, increased heart rate.");
        questions.add("Trying not to think or talk about the traumatic event.");
        questions.add("Trying to avoid activities or people that remind you of that traumatic event.");
        questions.add("Not being able to remember an important part of the traumatic event.");
        questions.add("Having much less interest or participation in important activities.");
        questions.add("Feeling distant or cut off from people around you.");
        questions.add("Feeling emotionally numb (unable to cry or have feelings)");
        questions.add("Feeling as if your future hopes or plans will not come true.");
        questions.add("Having trouble falling or staying asleep.");
        questions.add("Feeling irritable or having fits of anger.");
        questions.add("Having trouble concentrating.");
        questions.add("Being overly alert.");
        questions.add("Being jumpy or easily startled.");

    }

    public void setupList()
    {

        DisplayActivity2.SimpleAdapter sa= new SimpleAdapter(DisplayActivity2.this,questions);
        lv.setAdapter(sa);
    }


    public class SimpleAdapter extends BaseAdapter {

        Context mContext;
        LayoutInflater layoutinflater;
        TextView Question;
        RadioGroup group;
        ArrayList<String> quesArray;

        RadioButton rb1;
        RadioButton rb2;
        RadioButton rb3;
        RadioButton rb4;

        public SimpleAdapter(Context context, ArrayList<String> questions)
        {
            mContext=context;
            quesArray=questions;
            layoutinflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return quesArray.size();
        }

        @Override
        public Object getItem(int position) {
            return quesArray.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null)
            {
                convertView=layoutinflater.inflate(R.layout.list_layout,null);
            }
            Question=convertView.findViewById(R.id.ques);
            group=convertView.findViewById(R.id.radioGroup);

            rb1 = convertView.findViewById(R.id.button1);
            rb2 = convertView.findViewById(R.id.button2);
            rb3 = convertView.findViewById(R.id.button3);
            rb4 = convertView.findViewById(R.id.button4);

            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb=findViewById(checkedId);

                    String text = rb.getText().toString();
                    selected.add(text);

                    if (text.equalsIgnoreCase("Rarely"))
                    {
                        score=score+1;
                    }
                    else if (text.equalsIgnoreCase("Never"))
                    {
                        score=score+0;
                    }
                    else if (text.equalsIgnoreCase("Often"))
                    {
                        score=score+3;
                    }
                    else if (text.equalsIgnoreCase("All the time"))
                    {
                        score=score+5;
                    }

                    // Toast.makeText(DisplayActivity2.this, "" + score, Toast.LENGTH_SHORT).show();
                    /*OutputStreamWriter outputStreamWriter = null;
                    try {
                        outputStreamWriter = new OutputStreamWriter(mContext.openFileOutput("saved_responses.txt", Context.MODE_PRIVATE));
                        outputStreamWriter.write(text);
                        outputStreamWriter.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                }
            });

            Question.setText(quesArray.get(position));

            return convertView;

        }
    }
}
