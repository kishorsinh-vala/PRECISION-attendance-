package com.mscit1.precision;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Display extends AppCompatActivity {
    Spinner d_spn_strm,d_spn_sem;
    ImageView d_img_btn;
    GridView d_gridView_stud;
    Button btn_ins,btn_getData;
    DataBaseHelper dbs;

    ArrayList<HashMap<String,Object>> array=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        d_spn_strm=findViewById(R.id.d_sp_stream);
        d_spn_sem=findViewById(R.id.d_sem_stream);
        dbs=new DataBaseHelper(this);
        String d=dbs.check();
    //    Toast.makeText(this, ""+d, Toast.LENGTH_SHORT).show();
        if(d.equals("0"))
        {
            Intent intent=new Intent(Display.this,HomeActivity.class);
            finish();
            startActivity(intent);

        }
        else {
            d_spn_strm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String sp = parent.getItemAtPosition(position).toString();
                    if (sp.equals("BBA") || sp.equals("BCA") || sp.equals("BCOM") || sp.equals("BscIT")) {
                        String[] semB = getResources().getStringArray(R.array.array_b_sem);
                        ;
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(Display.this, android.R.layout.simple_list_item_1, semB);
                        d_spn_sem.setAdapter(adapter);
                    } else {

                        String[] semB = getResources().getStringArray(R.array.array_sem);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(Display.this, android.R.layout.simple_list_item_1, semB);
                        d_spn_sem.setAdapter(adapter);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            dbs = new DataBaseHelper(this);
            d_gridView_stud = findViewById(R.id.d_gv_stud);
            btn_getData = findViewById(R.id.d_btn_getData);
            btn_ins = findViewById(R.id.d_btn_ins);

            d_img_btn = findViewById(R.id.d_btn_addData);
            btn_ins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Display.this, Ins_modifi_stud.class);
                    startActivity(intent);
                }
            });

            btn_getData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sp = d_spn_strm.getSelectedItem().toString();
                    String sem = d_spn_sem.getSelectedItem().toString();
                    array = dbs.getAllRecord(sp, sem);
                    d_gridView_stud.setAdapter(new MyDisAdapter(Display.this, array));
//                Log.e("Data",""+array);
                    if (array.isEmpty()) {
                        Toast.makeText(Display.this, "Opps!! Records Not Available Please Enter RECORD", Toast.LENGTH_SHORT).show();
                        Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.blink);
                        // blink
                        btn_ins.setVisibility(View.VISIBLE);
                        btn_ins.startAnimation(animBlink);
                    }
                }
            });
            d_img_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (array.isEmpty()) {
                        Toast.makeText(Display.this, "Please Select Stream And Sem", Toast.LENGTH_SHORT).show();
                    } else {
                        int count = d_gridView_stud.getAdapter().getCount();
                        //Toast.makeText(Display.this, count+"", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < count; i++) {
                            RelativeLayout itemLayout = (RelativeLayout) d_gridView_stud.getChildAt(i);
                            CheckBox checkBox = itemLayout.findViewById(R.id.p_chk);
                            TextView txtView = itemLayout.findViewById(R.id.p_txt_name);
                            TextView txtViewlnm = itemLayout.findViewById(R.id.p_txt_lname);
                            TextView txtView_no = itemLayout.findViewById(R.id.p_txt_no);
                            String nm = txtView.getText().toString();
                            String lnm = txtViewlnm.getText().toString();
                            String no = txtView_no.getText().toString();
                            String sp = d_spn_strm.getSelectedItem().toString();
                            String sem = d_spn_sem.getSelectedItem().toString();
                            if (checkBox.isChecked()) {
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                String cd = dateFormat.format(date);
                                dbs.addStud_act(no, nm, lnm, sp, sem, cd, "P");

//                            Toast.makeText(Display.this,txtView.getText(),Toast.LENGTH_LONG).show();
                            } else if (!checkBox.isChecked()) {

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                String cd = dateFormat.format(date);
                                dbs.addStud_act(no, nm, lnm, sp, sem, cd, "A");
                            }
                        }
                        Toast.makeText(Display.this, "Attendance DONE...", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public void AtendanceDisp(MenuItem item) {
        Intent intent=new Intent(Display.this,Atendance.class);
        startActivity(intent);
    }

    public void LogOut(MenuItem item) {
        dbs.updLog();
        Intent intent=new Intent(Display.this,HomeActivity.class);
        finish();
        startActivity(intent);
    }

    public void ChangePassword(MenuItem item) {
        Intent intent=new Intent(Display.this,ChangePassword.class);
        finish();
        startActivity(intent);
    }
}
