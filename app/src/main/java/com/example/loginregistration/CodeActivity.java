package com.example.loginregistration;

import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CodeActivity extends AppCompatActivity {

    TextView com_mm, com_zcwc, code_djs;

    Button code_cxhq, code_tjyzm;

    Handler handler = new Handler();
    int i = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        findBy();
        com_mm.setTextColor(com_mm.getResources().getColor(R.color.colorheise));
        com_zcwc.setTextColor(com_zcwc.getResources().getColor(R.color.colorheise));

        setCode_cxhq();
        if (code_djs.getVisibility()==View.VISIBLE) {
            handler.postDelayed(runnable, 0);//延迟0s执行runnable.
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //自动生成方法
            //要执行的内容
            if(i>0) {
                code_djs.setText(i + "秒后重新获取验证码");
                i--;
            }
            else
            {
                code_cxhq.setVisibility(code_cxhq.VISIBLE);
                code_djs.setVisibility(code_djs.GONE);
            }
            //执行的内容结束
            handler.postDelayed(this, 1000);//每1s执行一次
        }
    };

    private void findBy() {
        com_mm = (TextView) findViewById(R.id.com_mm);
        com_zcwc = (TextView) findViewById(R.id.com_zcwc);
        code_djs = (TextView) findViewById(R.id.code_djs);

        code_cxhq = (Button) findViewById(R.id.code_cxhq);
        code_tjyzm = (Button) findViewById(R.id.code_tjyzm);
    }

    private void setCode_cxhq() {
        code_cxhq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=60;
                code_djs.setText(i+"秒后重新获取验证码");
                code_cxhq.setVisibility(code_cxhq.GONE);
                code_djs.setVisibility(code_djs.VISIBLE);
            }
        });
    }
}
