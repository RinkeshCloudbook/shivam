package com.one.shivam.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.one.shivam.Model.CommonModel;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "demo_db";
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Note.CREATE_demotable);
        db.execSQL(Note.TABLE_NAME_table);
        db.execSQL(Note.TABLE_NAME_Sheet);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME_demotable);
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME_Timetable);
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME_clock);

    }


    public long InserDemoData(String callNumber,
                               String calldateTime,
                              String callType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_callNumber, callNumber);
        values.put(Note.COLUMN_calldateTime, calldateTime);
        values.put(Note.COLUMN_callType, callType);


        long id = db.insert(Note.TABLE_NAME_demotable, null, values);
        return id;

    }

    public long InsertableData(String number,
                              String timedata,
                              String actiondata) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_number, number);
        values.put(Note.COLUMN_time, timedata);
        values.put(Note.COLUMN_action, actiondata);


        long id = db.insert(Note.TABLE_NAME_Timetable, null, values);
        return id;

    }

    public long InserTime(String startTime, String endTime) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_startTime, startTime);
        values.put(Note.COLUMN_endTime, endTime);

        long id = db.insert(Note.TABLE_NAME_clock, null, values);
        return id;

    }

    public List<CommonModel> getShowRecord(){
        List<CommonModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = getReadableDatabase();

        Cursor  cursor = db.rawQuery("select * from "+Note.TABLE_NAME_Timetable,null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    CommonModel cm = new CommonModel();
                    cm.number =cursor.getString(cursor.getColumnIndex(Note.COLUMN_number));
                    cm.time =cursor.getString(cursor.getColumnIndex(Note.COLUMN_time));
                    cm.action =cursor.getString(cursor.getColumnIndex(Note.COLUMN_action));

                    list.add(cm);
                } while (cursor.moveToNext());
            }
        }
    db.close();
    return list;
    }


    public void deletedata() {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Note.TABLE_NAME_demotable, null, null);

    }

    public Cursor getdemoData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor  cursor = db.query(Note.TABLE_NAME_demotable, null, null, null, null, null, null);
        //Cursor  cursor = db.query(Note.TABLE_NAME_Save_Offlinedata, null, Note.COLUMN_DateOffline + "='" + date + "' and " + Note.COLUMN_DateOffline + "='" + 0 + "'", null, null, null, null);
        return cursor;
    }

    public long InsertableData(List<CommonModel> list) {
        long id = 0;
        for (int i = 0; i < list.size(); i++) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Note.COLUMN_number, list.get(i).number);
            values.put(Note.COLUMN_time, list.get(i).time);
            values.put(Note.COLUMN_action, list.get(i).action);

            id = db.insert(Note.TABLE_NAME_Timetable, null, values);
        }


        return id;
    }



    /*public long InserDataOfflineSave(String DateOffline,
                                     String SunriseOfDay,
                                     String SunsetOfDay,
                                     String DayChoghadiyaLenth,
                                     String NightChoghadiyaLenth,
                                     String CreateDate,
                                     String CityName,
                                     String TimeZone,
                                     String Latitude,
                                     String longitude)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_DateOffline, DateOffline);
        values.put(Note.COLUMN_SunriseOfDay, SunriseOfDay);
        values.put(Note.COLUMN_SunsetOfDay, SunsetOfDay);
        values.put(Note.COLUMN_DayChoghadiyaLenth, DayChoghadiyaLenth);
        values.put(Note.COLUMN_NightChoghadiyaLenth, NightChoghadiyaLenth);
        values.put(Note.COLUMN_CreateDate, CreateDate);
        values.put(Note.COLUMN_CityName, CityName);
        values.put(Note.COLUMN_TimeZone, TimeZone);
        values.put(Note.COLUMN_Latitude, Latitude);
        values.put(Note.COLUMN_longitudeD, longitude);
        long id = db.insert(Note.TABLE_NAME_Save_Offlinedata, null, values);
        return id;
    }
    public List<OfflineData> getofflinedata(String date){
        List<OfflineData> offlineDataList = new ArrayList<OfflineData>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor  cursor = db.query(Note.TABLE_NAME_Save_Offlinedata, null, Note.COLUMN_DateOffline + "='" + date + "'", null, null, null, null);
        //Cursor  cursor = db.query(Note.TABLE_NAME_Save_Offlinedata, null, Note.COLUMN_DateOffline + "='" + date + "' and " + Note.COLUMN_DateOffline + "='" + 0 + "'", null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                OfflineData offlineData=new OfflineData();
                offlineData.setDateOffline(cursor.getString(1));
                offlineData.setSunriseOfDay(cursor.getString(2));
                offlineData.setSunsetOfDay(cursor.getString(3));
                offlineData.setDayChoghadiyaLenth(cursor.getString(4));
                offlineData.setNightChoghadiyaLenth(cursor.getString(5));
                offlineData.setCreateDate(cursor.getString(6));
                offlineData.setCityName(cursor.getString(7));
                offlineData.setTimeZone(cursor.getString(8));
                offlineData.setLatitude(cursor.getString(9));
                offlineData.setLongitude(cursor.getString(10));
                offlineDataList.add(offlineData);
            } while (cursor.moveToNext());
        }
        return offlineDataList;
    }
    public boolean checkdate(String date,String city){
        boolean check;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor  cursor = db.query(Note.TABLE_NAME_Save_Offlinedata, null, Note.COLUMN_DateOffline + "='" + date + "' and " + Note.COLUMN_CityName + "='" + city + "'", null, null, null, null);
        if (cursor.getCount()<=0){
            check=false;
        }else {
            check=true;
        }
        return check;
    }
    public void deletedata(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME_Save_Offlinedata,null,null);
    }*/

}