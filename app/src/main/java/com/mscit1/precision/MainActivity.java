package com.mscit1.precision;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dbs=new DataBaseHelper(MainActivity.this);
                String d=dbs.check();
                //    Toast.makeText(this, ""+d, Toast.LENGTH_SHORT).show();
                if(d.equals("0"))
                {
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    finish();
                    startActivity(intent);

                }
                else {
                    Intent homeIntent = new Intent(MainActivity.this, Display.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        },4000);
    }
}
