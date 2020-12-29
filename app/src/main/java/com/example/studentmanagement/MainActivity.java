package com.example.studentmanagement;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menuSearch),
                addItem = menu.findItem(R.id.menuAdd);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Tìm kiếm theo tên và mssv");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        addItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.form_add_student);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                }
                EditText edtID, edtName, edtDOB, edtEmail, edtAddress;
                edtID = dialog.findViewById(R.id.edtStudentID);
                edtName = dialog.findViewById(R.id.edtStudentName);
                edtDOB = dialog.findViewById(R.id.edtStudentDOB);
                edtEmail = dialog.findViewById(R.id.edtStudentEmail);
                edtAddress = dialog.findViewById(R.id.edtStudentAddress);
                Button btnAdd, btnCancel;
                btnAdd = dialog.findViewById(R.id.btnAddStudent);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Students students = new Students(Integer.valueOf(edtID.getText().toString()), edtName.getText().toString(), edtDOB.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString());
                        daoStudents.addStudent(students);
                        studentsList.add(students);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Add thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
        return true;
    }
}