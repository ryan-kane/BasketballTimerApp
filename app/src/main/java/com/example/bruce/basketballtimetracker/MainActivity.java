package com.example.bruce.basketballtimetracker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.Activity;
//import android.content.ClipData;
//import android.graphics.drawable.Drawable;
//import android.view.DragEvent;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.DragShadowBuilder;
//import android.view.View.OnDragListener;
//import android.view.View.OnTouchListener;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TimePicker;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    List<String> players = Arrays.asList("Avery", "Jocelyn", "Mariam", "Gabrielle",
            "Brianna", "Ruhi", "Farah", "Kalayah", "Meghan", "Katica", "Haya",
            "Kyria", "Sumaiya");

    int buttonStyle = R.style.style_btn;
    Map<String, Integer> playerSecondsPlayed = new HashMap<String, Integer>();
    int currMin;
    int currSec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button updateButton = findViewById(R.id.buttonUpdateClock);
        updateButton.setOnClickListener(updateGameClock);

        TableLayout tableLayout = findViewById(R.id.gameTableLayout);
        LinearLayout linearLayout = findViewById(R.id.gameLinearLayout);
//        layout.setPadding(15, 15, 15, 15);
//
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
//                TableRow.LayoutParams.FILL_PARENT,
//                TableRow.LayoutParams.FILL_PARENT);
//
//        layout.setLayoutParams(lp);
//        layout.setStretchAllColumns(true);

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        int index = 0;
        TableRow courtRow = new TableRow(this);
        tableLayout.addView(courtRow);
//        Button courtButton = new Button(this);
//        Button courtButton = new Button(new ContextThemeWrapper(this, buttonStyle), null, buttonStyle);
        Button courtButton = new Button(new ContextThemeWrapper(this, buttonStyle), null, buttonStyle);
        courtButton.setText("Court\n");
        courtRow.addView(courtButton);

        TableRow tableRow = new TableRow(this);
        tableRow.setOnDragListener(new MyDragListener());
        tableLayout.addView(tableRow);
        Button tableButton = new Button(new ContextThemeWrapper(this, buttonStyle), null, buttonStyle);
        tableButton.setText("Table\n");
        tableRow.addView(tableButton);

        currMin = 0;
        currSec = 0;

        while (index < players.size()) {

            TableRow benchRow = new TableRow(this);
            benchRow.setOnDragListener(new MyDragListener());
            Button benchButton = new Button(new ContextThemeWrapper(this, buttonStyle), null, buttonStyle);
            benchButton.setText("Bench\n");
            benchRow.addView(benchButton);
            for (int k = 0; k < 5 ; k++) {
                if (index < players.size()) {
                    playerSecondsPlayed.put(players.get(index),0);
                    Button btn = new Button(new ContextThemeWrapper(this, buttonStyle), null, buttonStyle);
//                    btn.setPadding(5,5,5,5);
                    btn.setText(players.get(index) + "\n00:00");
//                    btn.setOnClickListener(this);
//                    params.setMargins(15, 15, 15, 15);
//                    btn.setLayoutParams(params);
                    btn.setOnTouchListener(new MyTouchListener());

                    benchRow.addView(btn);
                    index++;
                }
            }

            tableLayout.addView(benchRow);

        }
//        linearLayout.addView(tableLayout);
        ;
    }

//    @Override
//    public void onClick(View v) {
//        String url = (String) v.getTag();
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
//        findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
//        findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
//        findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());
//        findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
//        findViewById(R.id.topright).setOnDragListener(new MyDragListener());
//        findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
//        findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
//
//    }
//


    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
//                view.setVisibility(View.INVISIBLE);
                return true;
            }
            else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
//        Drawable enterShape = getResources().getDrawable(
//                R.drawable.shape_droptarget);
//        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    View.OnClickListener updateGameClock = new View.OnClickListener() {
        @Override
        public void onClick(View button) {
            // pop up dialog
            final Dialog gameTimeDialog = new Dialog(MainActivity.this);
            gameTimeDialog.setContentView(R.layout.dialog);
            // Quarter
            final NumberPicker quarterPicker= (NumberPicker) gameTimeDialog.findViewById(R.id.Quarters);
            quarterPicker.setMaxValue(4);
            quarterPicker.setMinValue(0);
            quarterPicker.setWrapSelectorWheel(false);
            // Minute
            final NumberPicker minutePicker = (NumberPicker) gameTimeDialog.findViewById(R.id.Minutes);
            minutePicker.setMaxValue(10);
            minutePicker.setMinValue(0);
            minutePicker.setWrapSelectorWheel(true);
            // Second
            final NumberPicker secondPicker= (NumberPicker) gameTimeDialog.findViewById(R.id.Seconds);
            secondPicker.setMaxValue(59);
            secondPicker.setMinValue(0);
            secondPicker.setWrapSelectorWheel(true);
//            currTimePicker.setOnValueChangedListener(this);
                // select quarter, time, and score
            // Once they are all selected, press next
            gameTimeDialog.show();
            Button continueButton = gameTimeDialog.findViewById(R.id.continueButton);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    int quarter = quarterPicker.getValue();
                    int minute = minutePicker.getValue();
                    int second = secondPicker.getValue();
                    Log.d(TAG, ""+quarter+" "+minute+" "+second);
                    gameTimeDialog.dismiss();
                }
            });
        }
    };
}
