package com.example.studentmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

import io.bloco.faker.Faker;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "STUDENT_MANAGEMENT", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE STUDENTS(" +
                "studentID integer PRIMARY KEY AUTOINCREMENT," +
                "name text," +
                "dob String," +
                "email text," +
                "address text)";
        db.execSQL(sql);
        Faker faker = new Faker();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sql = "INSERT INTO STUDENTS VALUES (null,'" +
                    faker.name.name() + "','" +
                    random.nextInt(30) + 1 + "/" + random.nextInt(12)  + 1 + "/" + random.nextInt(7) + 1 + 1995 + "','" +
                    faker.name.firstName() + "." + faker.number.between(20170001, 20207000) + "@gmail.com','" +
                    faker.address.streetName() + "," +
                    faker.address.city() + "," +
                    faker.address.country() + "')";
            db.execSQL(sql);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTS");
        onCreate(db);
    }
}
