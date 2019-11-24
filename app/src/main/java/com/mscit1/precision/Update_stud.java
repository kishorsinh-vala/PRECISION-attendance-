package com.mscit1.precision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Update_stud extends AppCompatActivity {
    Spinner upd_spn_strm,upd_spn_sem;
    GridView upd_gridView_stud;
    Button btn_upd;
    DataBaseHelper dbs;
    ArrayList<HashMap<String,Object>> array=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stud);
        dbs=new DataBaseHelper(Update_stud.this);
        btn_upd=findViewById(R.id.upd_btn_getData);
        upd_gridView_stud=findViewById(R.id.upd_gv);
        upd_spn_strm=findViewById(R.id.upd_sp_stream);
        upd_spn_sem=findViewById(R.id.upd_sp_sem);
        upd_spn_strm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp=parent.getItemAtPosition(position).toString();
                if(sp.equals("BBA")||sp.equals("BCA")||sp.equals("BCOM")||sp.equals("BscIT"))
                {
                    String[] semB=getResources().getStringArray(R.array.array_b_sem);;
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(Update_stud.this,android.R.layout.simple_list_item_1,semB);
                    upd_spn_sem.setAdapter(adapter);
                }
                else
                {
                    String[] semB=getResources().getStringArray(R.array.array_sem);;
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(Update_stud.this,android.R.layout.simple_list_item_1,semB);
                    upd_spn_sem.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sp=upd_spn_strm.getSelectedItem().toString();
                String sem=upd_spn_sem.getSelectedItem().toString();
                array = dbs.getAllRecord(sp,sem);
                upd_gridView_stud.setAdapter(new MyUpdateAdapter(Update_stud.this,array));
                if(array.isEmpty())
                    Toast.makeText(Update_stud.this, "OPPS!! No Records Are Available...", Toast.LENGTH_SHORT).show();
            }
        });
        upd_gridView_stud.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l)
            {
                Intent intent=new Intent(Update_stud.this,Update_record.class);
                intent.putExtra("array", parent.getItemAtPosition(position).toString());
                startActivity(intent);


            }
        });

    }
}
