package com.mscit1.precision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

class DataBaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase database;
    public DataBaseHelper(Context mContext)
    {
        super(mContext,"info",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table BBA(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table MBA(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table BCA(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table MCA(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table BscIT(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table MScIT(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table BCOM(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table MCOM(RNo INTEGER PRIMARY KEY AUTOINCREMENT,Sem TEXT,FirstNm TEXT,LastNm TEXT, Address TEXT ,ContNo INTEGER,Stream TEXT,Activity INTEGER,Date TEXT)");
        db.execSQL("create table stud_act(Num INTEGER PRIMARY KEY AUTOINCREMENT,RNo INTEGER ,FirstNm TEXT,LastNm TEXT,Stream TEXT,Date TEXT,Act TEXT,Sem TEXT,Activity INTEGER)");
        db.execSQL("create table main_info(U_id INTEGER PRIMARY KEY AUTOINCREMENT, Password TEXT,Act TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS BBA");
        db.execSQL("DROP TABLE IF EXISTS MBA");
        db.execSQL("DROP TABLE IF EXISTS BCA");
        db.execSQL("DROP TABLE IF EXISTS MCA");
        db.execSQL("DROP TABLE IF EXISTS BscIT");
        db.execSQL("DROP TABLE IF EXISTS MscIT");
        db.execSQL("DROP TABLE IF EXISTS BCOM");
        db.execSQL("DROP TABLE IF EXISTS MCOM");
        db.execSQL("DROP TABLE IF EXISTS stud_act");
        db.execSQL("DROP TABLE IF EXISTS main_info");
        onCreate(db);
    }

    public long addData(String fnm, String lnm, String adr, String cn, String sp, String sem) {
        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstNm",fnm );
        contentValues.put("LastNm", lnm);
        contentValues.put("Address",adr );
        contentValues.put("ContNo", cn);
        contentValues.put("Stream",sp );
        contentValues.put("Sem",sem);
        contentValues.put("Activity",1 );
        long isDataInserted = database.insert(sp, null, contentValues);
        Log.e("datta","inserted");
        return isDataInserted;
    }
    public ArrayList<HashMap<String,Object>> getAllRecord(String sp,String sem)
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String,Object>> list =
                new ArrayList<HashMap<String, Object>>();
        String query = "select * from "+sp+" where Sem = '"+sem+"'and Activity = 1 ";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst())
        {
            do
            {
                int int_id = c.getInt(0);
                String str_name = c.getString(2);
                String str_lnm= c.getString(3);
                String str_Stream = c.getString(6);

                HashMap<String,Object> map =
                        new HashMap<String,Object>();
                map.put("RNo",int_id);
                map.put("my_name",str_name);
                map.put("my_lnm",str_lnm);
                map.put("my_Stream",str_Stream);

                list.add(map);
            }while(c.moveToNext());
        }
        db.close();
        return list;
    }

    public long addStud_act(String no, String nm,String lnm, String sp,String sem, String d,String act) {
        //Log.e("App actDAta :",no+nm+sp+cd+act);
        long isDataInserted=0;
        database=getReadableDatabase();
        String query = "select * from stud_act where Stream = '"+ sp + "' and Sem ='"+sem+"'and Date = '"+d+"'and Act = 'A' and Activity = 1";
        Cursor c = database.rawQuery(query,null);
        int data=0;
        if(c.moveToFirst())
        {
//            RNo INTEGER ,FirstNm TEXT,,LastNm TEXT,Stream TEXT,Date TEXT,Month TEXT,Year TEXT,Act TEXT
            do
            {
                String str_name = c.getString(2);
                String str_date = c.getString(5);
                if(str_date.equals(d) && str_name.equals(nm))
                {
                    data=1;
                }
            }while(c.moveToNext());
        }
        database.close();
        if(data==0) {
            database = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("RNo", Integer.parseInt(no));
            contentValues.put("FirstNm", nm);
            contentValues.put("LastNm", lnm);
            contentValues.put("Stream", sp);
            contentValues.put("Sem", sem);
            contentValues.put("Date", d);
            contentValues.put("Act", act);
            contentValues.put("Activity", 1);
            isDataInserted = database.insert("stud_act", null, contentValues);
        }
        return isDataInserted;

    }

    public void updateStud(String strm, String rno, String fnm, String lnm) {

        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstNm",fnm );
        contentValues.put("LastNm", lnm);
        int data=Integer.parseInt(rno);
        database.update(strm,contentValues,"RNo=" +rno,null);
        database.update("stud_act",contentValues,"RNo='" +rno+"' and Stream= '"+strm+"'",null);

    }

    public ArrayList<HashMap<String,Object>> GetAtd(String st, String sem) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String,Object>> list =
                new ArrayList<HashMap<String, Object>>();
        String query = "select * from stud_act where Stream = '"+ st + "' and Sem ='"+sem+"'and Act = 'A' and Activity = 1";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst())
        {
//            RNo INTEGER ,FirstNm TEXT,,LastNm TEXT,Stream TEXT,Date TEXT,Month TEXT,Year TEXT,Act TEXT
            do
            {
                int int_id = c.getInt(1);
                String str_name = c.getString(2);
                String str_lnm= c.getString(3);
                String str_date = c.getString(5);

                HashMap<String,Object> map =
                        new HashMap<String,Object>();
                map.put("RNo",int_id);
                map.put("my_name",str_name);
                map.put("my_lnm",str_lnm);
                map.put("my_date",str_date);

                list.add(map);
            }while(c.moveToNext());
        }
        db.close();
        return list;
    }

    public void delStud(String s, String rno) {

        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Activity", 0);

        int data=Integer.parseInt(rno);
        database.update(s,contentValues,"RNo=" +data,null);
        database.update("stud_act",contentValues,"RNo='" +data+"' and Stream= '"+s+"'",null);

    }

    public void chPWD(String olddata, String newdata)
    {
        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password", newdata);
        database.update("main_info",contentValues,"Password = '" +olddata+"'",null);
       // Log.e("update",newdata);
    }

    public void insert() {
            database = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Password", "hodPassword123");
            contentValues.put("Act", "0");
            database.insert("main_info", null, contentValues);
    }

    public String getData() {
        SQLiteDatabase db = getReadableDatabase();
        String list = null;
        String query = "select * from main_info";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()) {
//            RNo INTEGER ,FirstNm TEXT,,LastNm TEXT,Stream TEXT,Date TEXT,Month TEXT,Year TEXT,Act TEXT
            list = c.getString(c.getColumnIndex("Password"));
        }
            db.close();
        return list;
    }
    public String check() {
        SQLiteDatabase db = getReadableDatabase();

       String Act="0";
        String query = "select * from main_info";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()) {
//            RNo INTEGER ,FirstNm TEXT,,LastNm TEXT,Stream TEXT,Date TEXT,Month TEXT,Year TEXT,Act TEXT
            Act= c.getString(c.getColumnIndex("Act"));
        }
        db.close();
   return Act;
    }

    public void updLog() {
        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Act", "0");
        database.update("main_info",contentValues,"Act = '1' ",null);
    }

    public void updateRecord() {
        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Act", "1");
        database.update("main_info",contentValues,"Act = '0' ",null);
    }
}
