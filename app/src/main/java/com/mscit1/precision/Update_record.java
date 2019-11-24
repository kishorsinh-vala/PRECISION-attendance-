package com.mscit1.precision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update_record extends AppCompatActivity {

    DataBaseHelper dbs;
    TextView txt_lnm,txt_fnm,txt_str;
    EditText ed_lnm,ed_fnm;
    Button btn_up,btn_clr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);

        dbs=new DataBaseHelper(Update_record.this);

        Intent intent=getIntent();
        String value = intent.getStringExtra("array");

//         Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();



        txt_fnm=findViewById(R.id.ur_txt_oldfnm);
        txt_str=findViewById(R.id.ur_txt_str);
        txt_lnm=findViewById(R.id.ur_txt_oldlnm);
//EditText For  Write New NAME
        ed_fnm=findViewById(R.id.ur_ed_newfnm);
        ed_lnm=findViewById(R.id.ur_ed_newlnm);
//Button For Clear and Update
        btn_clr=findViewById(R.id.ur_btn_clr);
        btn_up=findViewById(R.id.ur_btn_upd);
      //  txt_data.setText(value);

//First Name
        String[] fnm=value.split("=");
        String[] nm=fnm[1].split(",");
        final String fnm1=nm[0];
        txt_fnm.setText(nm[0]);

//Rno
        String  data=fnm[2];
        final String[] reportRno=data.split(",");
        String rno=reportRno[0];
//        Toast.makeText(this, rno, Toast.LENGTH_SHORT).show();
//Streem

        String str=fnm[3];
        final String[] fstr=str.split(",");
        txt_str.setText(fstr[0]);

//Last Name

        String lnm=fnm[4];
        final String s=lnm.replace('}',' ');
        txt_lnm.setText(s.trim());

        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_fnm.setText("");
                ed_lnm.setText("");
            }
        });
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnm,lnm;

                int reportOfdata = 0;
                fnm=ed_fnm.getText().toString();
                lnm=ed_lnm.getText().toString();
               // Toast.makeText(Update_record.this, "Update Record...", Toast.LENGTH_SHORT).show();
                if(fnm.equals("") && lnm.equals(""))
                {
                    reportOfdata=1;
                    Toast.makeText(Update_record.this, "Please Enter Data For Update", Toast.LENGTH_SHORT).show();
                }
                else if(lnm.equals(""))
                {
                    lnm=s.trim();
                }
                else if (fnm.equals(""))
                {
                    fnm=fnm1;
                }
                if(reportOfdata==0) {
                    //  Toast.makeText(Update_record.this, fnm+"\t"+lnm, Toast.LENGTH_SHORT).show();
                    dbs.updateStud(fstr[0],reportRno[0],fnm,lnm);
                    Intent intent1=new Intent(Update_record.this,Update_stud.class);
                    finish();
                    startActivity(intent1);
                    Toast.makeText(Update_record.this, "Record Updated...", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
