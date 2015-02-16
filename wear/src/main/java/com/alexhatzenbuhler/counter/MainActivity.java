package com.alexhatzenbuhler.counter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    //the current count
    int count = 0;

    //current position of color
    int color_location = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //onCreate method to create the instance
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_main);

        //show the tapping hints
        showHints();

        //views to interact with elements
        TextView counter = (TextView) findViewById(R.id.text);
        ImageView reset = (ImageView) findViewById(R.id.reset);
        ImageView circle = (ImageView) findViewById(R.id.circle);

        //click listener for text view
        counter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count += 1;
                updateCounter(count);
            }
        });

        //long click listener for text view
        counter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (count > 0) {
                    count -= 1;
                    updateCounter(count);
                }

                return true;
            }
        });

        //click listener for image
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                updateCounter(count);
            }
        });

        //click listener for color changer
        circle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateColor();
            }
        });
    }

    //method to update counter
    public void updateCounter(int count) {

        final TextView counter = (TextView) findViewById(R.id.text);
        counter.setText(Integer.toString(count));

    }

    //method to update color
    public void updateColor() {

        //elements from xml - used to change properties
        final ImageView color_circle = (ImageView) findViewById(R.id.circle);
        final RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        final TextView counter = (TextView) findViewById(R.id.text);
        final ImageView reset = (ImageView) findViewById(R.id.reset);

        //switch statement to change color based on current state
        switch (color_location){
            case 0:
                background.setBackgroundColor(getResources().getColor(R.color.myGreen));
                color_circle.setImageResource(R.drawable.yellow_circle);
                color_location = 1;
                break;

            case 1:
                background.setBackgroundColor(getResources().getColor(R.color.myYellow));
                color_circle.setImageResource(R.drawable.red_circle);
                counter.setTextColor(getResources().getColor(R.color.myGrey));
                reset.setImageResource(R.drawable.reset_icon_gray);
                color_location = 2;
                break;

            case 2:
                background.setBackgroundColor(getResources().getColor(R.color.myRed));
                color_circle.setImageResource(R.drawable.blue_circle);
                reset.setImageResource(R.drawable.reset_icon);
                counter.setTextColor(getResources().getColor(R.color.white));
                color_location = 3;
                break;

            case 3:
                background.setBackgroundColor(getResources().getColor(R.color.myBlue));
                color_circle.setImageResource(R.drawable.green_circle);
                color_location = 0;
                break;
        }
    }

    //show hints
    public void showHints () {

        //switch statement to change color based on current state
        final TextView hintUp = (TextView) findViewById(R.id.hintUp);
        final TextView hintDown = (TextView) findViewById(R.id.hintDown);

        //show the TextViews for 3 seconds, then set to gone
        hintUp.postDelayed(new Runnable() {
           public void run() {
                hintUp.setVisibility(View.GONE);
                hintDown.setVisibility(View.GONE);
            }

        }, 2500);
    }
}
