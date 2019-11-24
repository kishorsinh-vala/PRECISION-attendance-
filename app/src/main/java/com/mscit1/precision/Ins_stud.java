package com.mscit1.precision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Ins_stud extends AppCompatActivity {

    EditText ed_fnm, ed_lnm, ed_con, ed_adr;
    Spinner sp_str,sp_sem;
    DataBaseHelper dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_stud);
        ed_fnm = findViewById(R.id.ins_stud_edit_fnm);
        ed_lnm = findViewById(R.id.ins_stud_edit_lnm);
        ed_adr = findViewById(R.id.ins_stud_edit_add);
        ed_con = findViewById(R.id.ins_stud_edit_Cn);
        sp_str = findViewById(R.id.ins_stud_spn_str);
        sp_sem = findViewById(R.id.ins_stud_spn_sem);
        dbs = new DataBaseHelper(this);

    sp_str.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String sp=parent.getItemAtPosition(position).toString();
            if(sp.equals("BBA")||sp.equals("BCA")||sp.equals("BCOM")||sp.equals("BscIT"))
            {
                String[] semB=getResources().getStringArray(R.array.array_b_sem);;
                ArrayAdapter<String> adapter=new ArrayAdapter<>(Ins_stud.this,android.R.layout.simple_list_item_1,semB);
                sp_sem.setAdapter(adapter);
            }
            else
            {
                String[] semB=getResources().getStringArray(R.array.array_sem);;
                ArrayAdapter<String> adapter=new ArrayAdapter<>(Ins_stud.this,android.R.layout.simple_list_item_1,semB);
                sp_sem.setAdapter(adapter);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
    }


    public void CLear(View view) {
        ed_con.setText("");
        ed_fnm.setText("");
        ed_adr.setText("");
        ed_lnm.setText("");
        sp_str.setSelection(0);
        sp_sem.setSelection(0);
    }

    public void insData(View view) {
        String fnm = ed_fnm.getText().toString();
        String lnm = ed_lnm.getText().toString();
        String adr = ed_adr.getText().toString();
        String cn = ed_con.getText().toString();
        String sp = sp_str.getSelectedItem().toString();
        String sem = sp_sem.getSelectedItem().toString();
        //Toast.makeText(this, sp, Toast.LENGTH_SHORT).show();
        if(fnm.equals("") ||lnm.equals("")||adr.equals("")||cn.equals("")||sp.equals("")||sem.equals(""))
        {
            Toast.makeText(this, "Full Fill All FIELD", Toast.LENGTH_SHORT).show();
        }
        else {
//        long data = dbs.addData(fnm, lnm, adr, cn, sp, sem);
            long data = dbs.addData(fnm, lnm, adr, cn, sp, sem);
            if (data == -1) {
                Toast.makeText(Ins_stud.this, "Please Enter Data Properly", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(Ins_stud.this, "Inserted....", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Display.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
