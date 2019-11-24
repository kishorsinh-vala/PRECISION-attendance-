package com.mscit1.precision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Ins_modifi_stud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_modifi_stud);
    }


    public void insertRecord(View view) {
        //Toast.makeText(this, "Insert Record", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,Ins_stud.class);
        startActivity(intent);
    }

    public void updateRecord(View view) {
       // Toast.makeText(this, "Update Record", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,Update_stud.class);
        startActivity(intent);
    }

    public void deleteRecord(View view) {
      //  Toast.makeText(this, "Delete Record", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,DeleteStud.class);
        startActivity(intent);
    }
}
