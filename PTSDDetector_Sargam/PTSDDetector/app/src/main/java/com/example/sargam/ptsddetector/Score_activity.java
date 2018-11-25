package com.example.sargam.ptsddetector;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Score_activity extends AppCompatActivity {

    public LineChart lineChart;
    public PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lineChart = findViewById(R.id.line_chart);
        pieChart = findViewById(R.id.pie_chart);

        //Creating a Progress Bar
        final ProgressDialog dialog = new ProgressDialog(Score_activity.this);
        dialog.setTitle("Calculating your total score");
        dialog.setMessage("Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();

        long delayInMillis = 3000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, delayInMillis);

        //Receiving value from the previous activity
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("SCORE");

        Toast.makeText(this, ""+ score, Toast.LENGTH_SHORT).show();

        //Setting up the Line chart
        lineChart();
        //Setting up the Pie chart
        piechart();
    }

    public void lineChart(){
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,60f));
        yValues.add(new Entry(1,45f));
        yValues.add(new Entry(2,56f));
        yValues.add(new Entry(3,34f));
        yValues.add(new Entry(4,58f));
        yValues.add(new Entry(5,76f));
        yValues.add(new Entry(6,67f));

        LineDataSet set1 = new LineDataSet(yValues, "");

        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data1 = new LineData(dataSets);
        lineChart.setData(data1);
    }

    public void piechart(){

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5,10,10,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(4f, "IT"));
        pieEntries.add(new PieEntry(6f, "Big Data"));
        pieEntries.add(new PieEntry(4.6f, "Cloud Services"));
        pieEntries.add(new PieEntry(5f, "Hardware"));
        pieEntries.add(new PieEntry(74f, "Networking"));
        pieEntries.add(new PieEntry(10f, "Analytics"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Revenue by Services");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);
    }
}
