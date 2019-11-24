package com.mscit1.precision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    EditText edPwd,edOld;
    DataBaseHelper dbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edPwd=findViewById(R.id.cPwd_ed_newPwd);
        edOld=findViewById(R.id.cPwd_ed_oldPwd);
        dbs=new DataBaseHelper(this);
        String data=dbs.getData();
        edOld.setText(data);

    }

    public void change(View view) {
        String newdata=edPwd.getText().toString();
        String olddata=edOld.getText().toString();
        if(newdata.equals("") && olddata.equals(""))
        {
            Toast.makeText(this, "Please Enter Old Pawword And New Password", Toast.LENGTH_SHORT).show();
        }
        else if (newdata.equals("") )
        {
            Toast.makeText(this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            dbs.chPWD(olddata,newdata);
            Intent intent=new Intent(ChangePassword.this,HomeActivity.class);
            finish();
            startActivity(intent);
        }
    }

    public void clear(View view) {
        edPwd.setText("");
    }
}
