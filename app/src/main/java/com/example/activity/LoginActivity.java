package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private final  static int REQUEST_CODE_LOGIN =520;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        list =  LitePal.findAll(MinePagePerson.class);
        SetObj();
    }

    public void On_Login(View view) {
        strpassword = password.getText().toString();
        strphonenumber = phonenumber.getText().toString();
        int flag_phonenumber=0;
        if(list.size()==0)
        {
            Toast.makeText(this,"请先注册",Toast.LENGTH_SHORT).show();
            return;
        }
        for(MinePagePerson minePagePerson: list)
        {
            Log.d("TestLitePal","密码: "+ minePagePerson.getPassword());
            Log.d("TestLitePal","电话: "+ minePagePerson.getPhonenumber());
            if ( minePagePerson.getPhonenumber().equals(strphonenumber))
            {
                flag_phonenumber=1;
                if(minePagePerson.getPassword().equals(strpassword))
                {
                    MinePagePersonService minePagePersonService = MinePagePersonService.getInstance();
                    minePagePersonService.setMinePagePerson(minePagePerson);//一次运行期间操作该实例
                    minePagePersonService.Login(strphonenumber,strpassword);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("page_id",3);
                    startActivityForResult(intent,REQUEST_CODE_LOGIN);  //REQUEST_CODE
                    return;
                }
            }
        }
        if(flag_phonenumber==1)
        {
            Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(LoginActivity.this,"手机号码不存在",Toast.LENGTH_SHORT).show();
        }
    }
    private void SetObj()
    {
        password=findViewById(R.id.login_password);
        phonenumber=findViewById(R.id.login_phone_number);
    }
    public void On_Quit_Login(View view) {
        finish();
    }

    public void On_GO_Register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);  //REQUEST_CODE
    }
}



