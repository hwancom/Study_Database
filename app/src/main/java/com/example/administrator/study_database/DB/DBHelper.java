package com.example.administrator.study_database.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "MyDatabase";

    public static final String DATABASE_NAME = "Contact.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "person";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME_01 = "name";
    public static final String COLUMN_NAME_02 = "mobile";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                        COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_01 + " TEXT, " +
                        COLUMN_NAME_02 + " TEXT" +
                    ")";

    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    // DatabaseHelper Singleton 패턴으로 구현
    // Singleton 패턴은 프로그램 내에서 객체의 개수가 1개로 고정되게 하는 패턴
    private static DBHelper dbHelper = null;

    // 인스턴스 가져오기
    // Singleton 패턴을 구현할 때, 주로 메소드를 getInstance 로 명명한다.
    // 여러 곳에서 동시에 DB를 열면 동기화 문제가 생길 수 있기 때문에 synchronized 키워드를 이용
    public static synchronized DBHelper getInstance(Context context) {
        // 객체가 없을 경우에만 객체를 생성
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        // 객체가 이미 존재할 경우, 기존 객체를 리턴
        return dbHelper;
    }

    // Singleton 패턴을 구현할 때, 해당 클래스의 생성자는 private 로 선언하여
    // 외부에서의 직접 접근을 막아야 한다.
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Drop existing Table
        try {
            db.execSQL(SQL_DROP_TABLE);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in SQL_DROP_TABLE", ex);
        }

        // Create Table
        try {
            db.execSQL(SQL_CREATE_TABLE);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in SQL_CREATE_TABLE", ex);
        }

        println("creating database [" + DATABASE_NAME + "].");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE);
        }

        onCreate(db);

        println("Upgrading database from version " + oldVersion + " to " + newVersion + ".");
    }

    private void println(String msg) {
        Log.d(TAG, msg);
    }
}
