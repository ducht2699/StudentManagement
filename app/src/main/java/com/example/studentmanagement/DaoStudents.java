package com.example.studentmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DaoStudents {
    Database database;
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
    SQLiteDatabase sqLiteDatabase;

    public DaoStudents(Context context) {
        database = new Database(context);
    }

    public void studentSetSelected(int id, boolean selected) {
    }

    public List<Students> getAllStudent() {
        String sql = "SELECT * FROM STUDENTS";
        List<Students> list = new ArrayList<>();
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cs = sqLiteDatabase.rawQuery(sql, new String[]{});
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                int studentID = cs.getInt(0);
                String name = cs.getString(1);
                String dob = cs.getString(2);
                String email = cs.getString(3);
                String address = cs.getString(4);
                Students student = new Students(studentID, name, dob, email, address);
                list.add(student);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        cs.close();
        return list;
    }
}
