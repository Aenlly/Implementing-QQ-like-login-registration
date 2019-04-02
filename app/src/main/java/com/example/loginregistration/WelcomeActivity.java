package com.example.loginregistration;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    //界面可见时执行
    protected void onResume(){
        super.onResume();
        final Intent intent = new Intent();//第一种方法，用进程
        intent.setAction("Login");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);//do something
                finish();//消除该界面的资源占用
            }
        }, 10000);//延时1s执行

        CountDownTimer countDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔第二个1000执行一次该方法
                TextView wel_tg=(TextView)findViewById(R.id.wel_tg);
                wel_tg.setText(millisUntilFinished/1000+"后跳过");
                wel_tg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                        finish();//消除该界面的资源占用
                    }
                });

            }

            @Override
            public void onFinish() {
                //第一个1000为总时间，在该事件方法中发生
                startActivity(intent);
                finish();//消除该界面的资源占用
            }
        };
        countDownTimer.start();//倒计时开始
        /*第二种方法
             CountDownTimer countDownTimer=new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔第二个1000执行一次该方法
            }

            @Override
            public void onFinish() {
                //第一个1000为总时间，在该事件方法中发生
            }
        };
        countDownTimer.start();//倒计时开始
         */
    }
}
