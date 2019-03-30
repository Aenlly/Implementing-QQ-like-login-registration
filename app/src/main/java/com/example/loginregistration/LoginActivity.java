package com.example.loginregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText qqNum;
    EditText qqPwd;

    Button btn_Login;
    Button btn_register;

    TextView tv_more;
    LinearLayout Laymore;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewID();
        Btn_Login();
        Btn_register();
        More();
    }

    private void findViewID() {

        qqNum = (EditText) findViewById(R.id.et_qqNum);
        qqPwd = (EditText) findViewById(R.id.et_qqPwd);

        btn_Login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_login_regist);

        tv_more = (TextView) findViewById(R.id.tv_login_more);

        Laymore = (LinearLayout) findViewById(R.id.menu_more);

    }

    /*登录按钮*/
    private void Btn_Login() {
        findViewID();
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /*注册按钮*/
    private void Btn_register() {
        findViewID();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("Mobile");
                startActivity(intent);
            }
        });
    }

    /*更多选项按钮*/
    private void More() {
        findViewID();
        tv_more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (count == 0) {
                    Laymore.setVisibility(Laymore.VISIBLE);//让隐藏的部分显示
                    count++;
                } else {
                    Laymore.setVisibility(Laymore.GONE);//隐藏
                    count--;
                }
            }
        });
    }
}
