package com.mscit1.precision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Atendance extends AppCompatActivity {

    Spinner spn,sp_sem;
    Button btn_get;
    GridView gv;
    ArrayList<HashMap<String,Object>> array=new ArrayList<>();
    DataBaseHelper dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendance);
        spn=findViewById(R.id.at_sp_stream);
        sp_sem=findViewById(R.id.at_sp_sem);
        btn_get=findViewById(R.id.at_btn_getData);
        gv=findViewById(R.id.at_gv_atd);
        dbs=new DataBaseHelper(Atendance.this);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp=parent.getItemAtPosition(position).toString();
                if(sp.equals("BBA")||sp.equals("BCA")||sp.equals("BCOM")||sp.equals("BscIT"))
                {
                    String[] semB=getResources().getStringArray(R.array.array_b_sem);;
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(Atendance.this,android.R.layout.simple_list_item_1,semB);
                    sp_sem.setAdapter(adapter);
                }
                else
                {
                    String[] semB=getResources().getStringArray(R.array.array_sem);;
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(Atendance.this,android.R.layout.simple_list_item_1,semB);
                    sp_sem.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st=spn.getSelectedItem().toString();
                String sem=sp_sem.getSelectedItem().toString();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String cd=  dateFormat.format(date);


//                Toast.makeText(Atendance.this, ""+mon, Toast.LENGTH_SHORT).show();

                array=dbs.GetAtd(st,sem);
                gv.setAdapter(new GetAdapterAtd(Atendance.this,array));
                if(array.isEmpty())
                {
                    Toast.makeText(Atendance.this, "Nothing Availablle", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
