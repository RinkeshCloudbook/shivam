package com.one.shivam.Database;

public class Note {

    public static final String TABLE_NAME_demotable = "demotable";
    public static final String TABLE_NAME_Timetable = "Timetable";
    public static final String TABLE_NAME_clock = "timeSheet";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_callNumber = "callNumber";
    public static final String COLUMN_calldateTime = "calldateTime";
    public static final String COLUMN_callType = "callType";

    public static final String COLUMN_tID = "t_id";
    public static final String COLUMN_number = "number";
    public static final String COLUMN_time = "time";
    public static final String COLUMN_action = "Action";

    public static final String COLUMN_timeID = "time_id";
    public static final String COLUMN_startTime = "startTime";
    public static final String COLUMN_endTime = "endTime";


    public static final String CREATE_demotable =
            "CREATE TABLE " + TABLE_NAME_demotable + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_callNumber + " TEXT,"
                    + COLUMN_calldateTime + " TEXT,"
                    + COLUMN_callType + " TEXT"

                    + ")";

    public static final String TABLE_NAME_table =
            "CREATE TABLE " + TABLE_NAME_Timetable + "("
                    + COLUMN_tID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_number + " TEXT,"
                    + COLUMN_time + " TEXT,"
                    + COLUMN_action + " TEXT"

                    + ")";

    public static final String TABLE_NAME_Sheet =
            "CREATE TABLE " + TABLE_NAME_clock + "("
                    + COLUMN_timeID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_startTime + " TEXT,"
                    + COLUMN_endTime + " TEXT"
                    + ")";

}