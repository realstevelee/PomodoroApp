package com.example.pomodoro;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    TextView countTextView;
    Button goButton;
    Boolean counterIsActive = false;
    Boolean isWorkingTime = true;
    CountDownTimer countDownTimer;
    EditText workTimeTextField;
    EditText restTimeTextField;

    int countNum = 0;
    int minutes;
    int seconds;
    String secondString;

    int workTime;
    int restTime;


    public void resetTimer(int time) {
        updateTimer(time * 60);
        //timerTextView.setText(Integer.toString(minutes/ 60) + ":" + secondString);
        countDownTimer.cancel();
        goButton.setText("Start");
        counterIsActive = false;
    }

    public void workingTime(){
        if (counterIsActive) {
            resetTimer(0);
            countNum = 0;
            isWorkingTime = true;
        } else{
            counterIsActive = true;
            goButton.setText("Stop");

            countDownTimer = new CountDownTimer(workTime * 60000, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    countNum++;
                    countTextView.setText("Count: " + countNum);
                    resetTimer(restTime);
                    isWorkingTime = false;
                }
            }.start();
        }
    }

    public void restTime(){
        if (counterIsActive) {
            resetTimer(0);
            countNum = 0;
            isWorkingTime = true;
        } else{
            counterIsActive = true;
            goButton.setText("Stop");

            countDownTimer = new CountDownTimer(restTime * 60000, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer(workTime);
                    isWorkingTime = true;
                }
            }.start();
        }
    }

    public void clickButton(View view){
        workTime = Integer.parseInt(workTimeTextField.getText().toString());
        restTime = Integer.parseInt(restTimeTextField.getText().toString());

        if(isWorkingTime){
            workingTime();
        }
        else{

            restTime();
        }
    }


    public void updateTimer(int secondsLeft) {
        minutes = secondsLeft / 60;
        seconds = secondsLeft - (minutes * 60);

        secondString = Integer.toString(seconds);

        if(seconds <= 9){
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        goButton = findViewById(R.id.goButton);
        countTextView = findViewById(R.id.countTextView);
        workTimeTextField = findViewById(R.id.workTimeTextField);
        restTimeTextField = findViewById(R.id.restTimeTextField);

    }
}
