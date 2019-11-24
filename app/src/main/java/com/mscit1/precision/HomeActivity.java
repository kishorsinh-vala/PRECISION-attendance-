package com.mscit1.precision;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class HomeActivity extends AppCompatActivity {
    Animation animation;
    EditText ed_p;
    Button btn_login;
    DataBaseHelper dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ed_p=findViewById(R.id.h_ed_pwd);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sample_anim);
        ed_p.startAnimation(animation);
        btn_login=findViewById(R.id.h_btn_login);
        dbs=new DataBaseHelper(this);
        dbs.insert();

    }

    public void check(View view) {
//        ArrayList<HashMap<String,Object>> list;
        String pwd=ed_p.getText().toString();
        String list=dbs.getData();
//        Toast.makeText(this, ""+list, Toast.LENGTH_SHORT).show();
        if(pwd.equals(list))
        {
            dbs.updateRecord();
            Intent i= new Intent(HomeActivity.this,Display.class);
            finish();
            startActivity(i);
        }
        else if(pwd.equals(""))
        {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Sorry Password Didn't Match...", Toast.LENGTH_SHORT).show();
        }
    }
}
