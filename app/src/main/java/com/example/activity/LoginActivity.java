package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entity.MinePagePerson;
import com.example.news.R;
import com.service.MinePagePersonService;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText phonenumber;
    EditText password;
    String strphonenumber;
    String strpassword;
    List<MinePagePerson> list;
    int flag_phonenumber=0;
    MinePagePersonService minePagePersonService = MinePagePersonService.getInstance();
    private final  static int REQUEST_CODE_LOGIN =520;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password=findViewById(R.id.login_password);
        phonenumber=findViewById(R.id.login_phone_number);
        list =  LitePal.findAll(MinePagePerson.class);
    }

    @Override protected  void onStart() {

        super.onStart();
    }

    public void On_Login(View view) {
        int id = 0;
        strpassword = password.getText().toString();
        strphonenumber = phonenumber.getText().toString();
        if(list.size()==0) {
            Toast.makeText(this,"请先注册",Toast.LENGTH_SHORT).show();
            return;
        }
        for(MinePagePerson minePagePerson: list)
        {
            id++;
            if ( minePagePerson.getPhonenumber().equals(strphonenumber))
            {
                flag_phonenumber=1;
                if(minePagePerson.getPassword().equals(strpassword))
                {
                    minePagePersonService.Login(strphonenumber,strpassword);
                    minePagePersonService.getMinePagePerson().setId(id);
                    minePagePersonService.setMinePagePerson(minePagePerson);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("page_id",3);
                    startActivityForResult(intent,REQUEST_CODE_LOGIN);
                    return;
                }
            }
        }
        if(flag_phonenumber==1) {
            Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this,"手机号码不存在",Toast.LENGTH_SHORT).show();
        }
    }

    public void On_Quit_Login(View view) {
        finish();
    }

    public void On_GO_Register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);  //REQUEST_CODE
    }
}



