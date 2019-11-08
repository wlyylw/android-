package com.example.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.entity.MinePagePerson;
import com.example.news.R;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    List<MinePagePerson> list;
    EditText phoneNumber;
    EditText vCode;
    EditText password;
    Button buttonGetVCode;
    Button buttonRegister;
    EventHandler eventHandler;
    String strPhoneNumber;
    String strPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SetObj();
        list =  LitePal.findAll(MinePagePerson.class);
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);
    }



    public void onClick(View view) {
        if (view.getId() == R.id.button_register) {
            String strCode = vCode.getText().toString();
            if (null != strCode && strCode.length() == 4) {
                SMSSDK.submitVerificationCode("86", strPhoneNumber, vCode.getText().toString());
            } else {
                Toast.makeText(this, "密码长度不正确", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.button_send_verification_code) {
            strPhoneNumber = phoneNumber.getText().toString();
            if (null == strPhoneNumber || "".equals(strPhoneNumber) || strPhoneNumber.length() != 11) {
                Toast.makeText(this, "电话号码输入有误", Toast.LENGTH_SHORT).show();
                return;
            }
            //TODO:手机号已被注册的逻辑
            for(MinePagePerson mPerson: list) {
                if ( mPerson.getPhonenumber().equals(strPhoneNumber))
                {
                    Toast.makeText(this,"该手机号码已经被注册",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            SMSSDK.getVerificationCode("86", strPhoneNumber);
            buttonGetVCode.setClickable(false);
            //开启线程去更新button的text
            new Thread() {
                @Override
                public void run() {
                    int totalTime = 60;
                    for (int i = 0; i < totalTime; i++) {
                        Message message = myHandler.obtainMessage(0x01);
                        message.arg1 = totalTime - i;
                        myHandler.sendMessage(message);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    myHandler.sendEmptyMessage(0x02);
                }
            }.start();
        }
    }

    @SuppressLint("HandlerLeak")
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(RegisterActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            strPassword = password.getText().toString();    //
                            strPhoneNumber = phoneNumber.getText().toString();
                            MinePagePerson minePagePerson = new MinePagePerson();
                            minePagePerson.setPhonenumber(strPhoneNumber);
                            minePagePerson.setPassword(strPassword);
                            minePagePerson.save();
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(RegisterActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    buttonGetVCode.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    buttonGetVCode.setText("获取验证码");
                    buttonGetVCode.setClickable(true);
                    break;
            }
        }
    };
    private void SetObj() {
        phoneNumber =  findViewById(R.id.phone_number);
        vCode =  findViewById(R.id.verification_code);
        password = findViewById(R.id.register_password);
        buttonGetVCode =  findViewById(R.id.button_send_verification_code);
        buttonRegister = findViewById(R.id.button_register);
        buttonGetVCode.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

    }
    public void On_Quit_Register(View view) {
        finish();
    }

    public void On_GO_Login(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);  //REQUEST_CODE
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

}




