package com.example.administrator.study_database.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBFacade {

    private DBHelper dbHelper;
    private Context mContext;

    // Context를 받아서 Helper 객체를 생성
    public DBFacade(Context context) {
        dbHelper = DBHelper.getInstance(context);
        mContext = context;
    }

    public void insertData(String name, String mobile) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "INSERT INTO " + DBHelper.TABLE_NAME +
                "(" +
                DBHelper.COLUMN_NAME_01 + "," +
                DBHelper.COLUMN_NAME_02 +
                ") VALUES (?, ?)";
        Object[] params = {name, mobile};

        db.execSQL(sql, params);
        db.close();
    }

    public ArrayList<String> selectData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<String> list = new ArrayList<>();

        String result = "";
        String sql = "SELECT * FROM " + DBHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            result += cursor.getString(0) + " : "
                    + cursor.getString(1) + "   "
                    + cursor.getString(2) + "\n";
        }

        list.add(result);
        cursor.close();

        return list;
    }

    public void updateData(String name, String mobile) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 입력한 이름과 일치하는 행의 전화번호 수정
        String sql = "UPDATE " + DBHelper.TABLE_NAME +
                     " SET "   + DBHelper.COLUMN_NAME_02 + " = '" + mobile + "'" +
                     " WHERE " + DBHelper.COLUMN_NAME_01 + " = '" + name + "'";

        db.execSQL(sql);
        db.close();
    }

    public void deleteData(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 입력한 이름과 일치하는 행 삭제
        String sql = "DELETE FROM " + DBHelper.TABLE_NAME +
                     " WHERE "      + DBHelper.COLUMN_NAME_01 + " = '" + name + "'";

        db.execSQL(sql);
        db.close();
    }

    /**
     * CursorAdapter에 data를 제공하기 위한 method
     * DB의 모든 data를 리턴한다.
     * CursorAdapter에 들어가는 cursor 객체는 반드시 _ID column을 포함해야 한다.
     * */
    public Cursor getCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return db.query(DBHelper.TABLE_NAME,
                new String[] {
                        DBHelper.COLUMN_ID,
                        DBHelper.COLUMN_NAME_01,
                        DBHelper.COLUMN_NAME_02,
                }, null, null, null, null, null);
    }

}
