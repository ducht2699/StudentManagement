package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvStudentList;
    List<Students> studentsList;
    DaoStudents daoStudents;
    StudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.d("TAG", "student - " + studentsList);
    }

    private void init() {
        daoStudents = new DaoStudents(this);
        rcvStudentList = findViewById(R.id.rcvStudentList);
        studentsList = daoStudents.getAllStudent();
        //set adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvStudentList.setLayoutManager(linearLayoutManager);
        adapter = new StudentAdapter(studentsList, this, R.layout.item_student_list);
        rcvStudentList.setAdapter(adapter);
    }
}