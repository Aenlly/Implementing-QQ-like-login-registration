package com.example.loginregistration;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MobilephoneActivity extends AppCompatActivity {

    Button fh,btn_xg,btn_yzm;

    EditText ed_sjh;

    TextView txt_dz,txt_tk;

    CheckBox cb_yd;

    String[] str={"+86中国大陆","+852香港","+853澳门","+886台湾","+81日本","+1美国","+1加拿大","+66泰国","+7俄罗斯"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilephone);

        findBy();
        setBtn_xg();
        setEd_sjh();
        setCb_yd();//单击按钮事件
        setBtn_yzm();

        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("Login");
                startActivity(intent);
            }
        });
    }

    private void findBy(){
        btn_xg=(Button)findViewById(R.id.phone_xg);
        btn_yzm=(Button)findViewById(R.id.phone_yzm);

        fh=(Button)findViewById(R.id.zcfh);

        ed_sjh=(EditText)findViewById(R.id.phone);

        txt_dz=(TextView) findViewById(R.id.phone_dz);
        txt_tk=(TextView)findViewById(R.id.phone_tk);

        cb_yd=(CheckBox)findViewById(R.id.phone_yd);

    }

    //手机号的判断，是否为空，是否为有效
    private void setEd_sjh() {
        ed_sjh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
                String str = ed_sjh.getText().toString();
                if (cb_yd.isChecked() == true && str.length() != 0) {
                    btn_yzm.setEnabled(true);
                } else {
                    btn_yzm.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
                String str = ed_sjh.getText().toString();
                if (cb_yd.isChecked() == true && str.length() != 0) {
                    btn_yzm.setEnabled(true);
                } else {
                    btn_yzm.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //s:变化后的所有字符
                String str = ed_sjh.getText().toString();
                if (cb_yd.isChecked() == true && str.length() != 0) {
                    btn_yzm.setEnabled(true);
                } else {
                    btn_yzm.setEnabled(false);
                }
            }
        });
    }

    //协议的确认判断
    private void setCb_yd(){
        cb_yd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=ed_sjh.getText().toString();
                if (cb_yd.isChecked()==true && str.length()!=0) {
                    btn_yzm.setEnabled(true);
                } else {
                    btn_yzm.setEnabled(false);
                }
            }
        });
    }

    //验证码按钮判断事件
    private void setBtn_yzm(){
        btn_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = ed_sjh.getText().toString();
                boolean judge = isMobile(number);

                if (judge == false){
                    sjh_ts();
                }
                else{
                    Intent intent=new Intent();
                    intent.setAction("code");
                    startActivity(intent);
                }
            }
        });
    }

    //手机号在正则表达式的方法
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、178(新)、182、184、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、170、173、177、180、181、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    //手机号不正确的提示对话框
    private void sjh_ts(){
        AlertDialog dialog;
        dialog=new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("请填写正确的手机号")
                .setPositiveButton("确认",null)
                .create();
        dialog.show();
    }

    //自定义修改按钮的对话框
    private void xg(){
        new AlertDialog.Builder(this)
                .setTitle("请选择地区")
                .setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txt_dz.setText(str[which]+"");
                    }
                })
                .setPositiveButton("确认",null)
                .show();//输出
    }

    //修改按钮事件
    private void setBtn_xg(){
        btn_xg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                xg();
            }
        });
    }
}
