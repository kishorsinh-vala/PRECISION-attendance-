package com.mscit1.precision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteRecord extends AppCompatActivity {


    DataBaseHelper dbs;
    TextView txt_lnm,txt_fnm,txt_str;
    Button btn_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_record);
        dbs=new DataBaseHelper(DeleteRecord.this);

        Intent intent=getIntent();
        final String value = intent.getStringExtra("array");

//         Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();



        txt_fnm=findViewById(R.id.del_txt_oldfnm);
        txt_str=findViewById(R.id.del_txt_str);
        txt_lnm=findViewById(R.id.del_txt_oldlnm);
//EditText For  Write New NAME//Button For Clear and Update
        btn_up=findViewById(R.id.del_btn_del);
        //  txt_data.setText(value);

//First Name
        final String[] fnm=value.split("=");
        String[] nm=fnm[1].split(",");
        final String fnm1=nm[0];
        txt_fnm.setText(nm[0]);

//Rno
        String  data=fnm[2];
        final String[] reportRno=data.split(",");
        final String rno=reportRno[0];
//        Toast.makeText(this, rno, Toast.LENGTH_SHORT).show();
//Streem

        String str=fnm[3];
        final String[] fstr=str.split(",");
        txt_str.setText(fstr[0]);

//Last Name

        final String lnm=fnm[4];
        final String s=lnm.replace('}',' ');
        txt_lnm.setText(s.trim());


        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(DeleteRecord.this, value, Toast.LENGTH_SHORT).show();
//                    dbs.updateStud(fstr[0],reportRno);
                dbs.delStud(fstr[0],rno);
                    Intent intent1=new Intent(DeleteRecord.this,DeleteStud.class);
                    finish();
                    startActivity(intent1);
                Toast.makeText(DeleteRecord.this, "Record Deleted...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
