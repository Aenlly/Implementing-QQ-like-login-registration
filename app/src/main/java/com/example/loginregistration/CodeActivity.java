package com.example.loginregistration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLTransactionRollbackException;
import java.util.Timer;
import java.util.TimerTask;

public class CodeActivity extends AppCompatActivity {

    TextView com_mm, com_zcwc, code_djs;

    EditText code_sryzm;

    Button code_cxhq, code_tjyzm,fh;

    Handler handler = new Handler();

    String[] str={"AXBZ","B1DZ","MXZR","1F4G"};
    int i = 60,j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        findBy();

        com_mm.setTextColor(com_mm.getResources().getColor(R.color.colorheise));
        com_zcwc.setTextColor(com_zcwc.getResources().getColor(R.color.colorheise));

        setCode_cxhq();
        setFh();

        if (code_djs.getVisibility()==View.VISIBLE) {
            handler.postDelayed(runnable, 0);//延迟0s执行runnable.
        }
        setCode_tjyzm();
    }


    //定时器事件，包括弹出验证码对话框
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //自动生成方法
            //要执行的内容
            if(i>0) {
                code_djs.setText(i + "秒后重新获取验证码");
                i--;
                if (i==55)
                {
                    set_code();//执行验证码弹窗
                }
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

    //弹出验证码窗口对话框
    private void set_code(){
        AlertDialog dialog;
        switch (j) {
            case 0:
                dialog = new AlertDialog.Builder(this).setTitle("验证码")
                        .setMessage(str[j])
                        .setPositiveButton("已读", null)
                        .create();

                dialog.show();
                break;
            case 1:
                dialog = new AlertDialog.Builder(this).setTitle("验证码")
                        .setMessage(str[j])
                        .setPositiveButton("已读", null)
                        .create();

                dialog.show();
                break;
            case 2:
                dialog = new AlertDialog.Builder(this).setTitle("验证码")
                        .setMessage(str[j])
                        .setPositiveButton("已读", null)
                        .create();

                dialog.show();
                break;
        }


    }

    //控件实例化
    private void findBy() {
        com_mm = (TextView) findViewById(R.id.com_mm);
        com_zcwc = (TextView) findViewById(R.id.com_zcwc);
        code_djs = (TextView) findViewById(R.id.code_djs);

        code_sryzm=(EditText)findViewById(R.id.code_sryzm);

        fh=(Button)findViewById(R.id.zcfh);
        code_cxhq = (Button) findViewById(R.id.code_cxhq);

        code_tjyzm = (Button) findViewById(R.id.code_tjyzm);
    }

    //返回按钮事件
    private void setFh(){
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("Login");
                startActivity(intent);
            }
        });
    }

    //重新获取验证码的按钮事件
    private void setCode_cxhq() {
        code_cxhq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=60;
                if (j<3){
                    j++;
                }
                else
                {
                    j=0;
                }
                code_djs.setText(i+"秒后重新获取验证码");
                code_cxhq.setVisibility(code_cxhq.GONE);
                code_djs.setVisibility(code_djs.VISIBLE);
            }
        });
    }

    //提交验证码按钮事件
    private void setCode_tjyzm(){
        code_tjyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_l=code_sryzm.getText().toString();
                if (str_l.equals(str[j])) {
                    dhk_yzmzq();
                }
                else {
                        dhk_yzmcw();
                }
                Log.i("eor",str_l);
                Log.i("eor",str[j]);
            }
        });

    }

    //验证码输入正确对话框
    private void dhk_yzmzq(){
        AlertDialog dialog;
        dialog = new AlertDialog.Builder(this).setTitle("注册成功")
                .setMessage("您的初始密码为123456")
                .setPositiveButton("取消",null)
                .setNegativeButton("立即登录", new DialogInterface.OnClickListener() {
                    //设置返回登录的按钮事件
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent();
                        intent.setAction("Login");
                        startActivity(intent);
                    }
                })
                .create();

        dialog.show();
    }

    //验证码错误对话框
    private void dhk_yzmcw(){
        AlertDialog dialogl;
        dialogl = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("您输入的验证码不正确，请重新输入")
                .setPositiveButton("确定", null)
                .create();

        dialogl.show();
    }
}
