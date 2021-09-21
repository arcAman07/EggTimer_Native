package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer;
    int currentTime = 300;
    int currentState = 0; // 0 for the the clock isn't running currently,1 for the clock is running currently
    CountDownTimer waitTimer;

    public void buttonAction(View view) {
        TextView runningTimer = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        if (currentState == 0) {

            button.setText("PAUSE");
            waitTimer = new CountDownTimer(currentTime*1000,1000) {
                @Override
                public void onTick(long l) {
                    runningTimer.setText(String.valueOf(l/1000));
                    currentTime = (int)l/1000;



                }



                @Override
                public void onFinish() {
                    Log.i("Done!","No More countdown!!");
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mediaPlayer.start();


                }
            }.start();
            currentState=1;

        }
        else if(currentState == 1) {

            button.setText("GO!");
            if(waitTimer != null) {
                waitTimer.cancel();
                waitTimer = null;
            }
            currentState=0;
//            runningTimer.setText(Integer.toString(currentTime));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int degreeTime = 15;
        SeekBar time = (SeekBar) findViewById(R.id.seekBar);
        time.setProgress(20);
        time.setMax(40);
        time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("time",Integer.toString(degreeTime*i));
                currentTime = degreeTime*i;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}