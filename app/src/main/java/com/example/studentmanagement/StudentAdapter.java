package com.example.studentmanagement;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    List<Students> studentsList;
    DaoStudents daoStudents;
    Context context;
    int layout;

    public StudentAdapter(List<Students> studentsList, Context context, int layout) {
        this.studentsList = studentsList;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        daoStudents = new DaoStudents(context);
        final Students student = studentsList.get(position);
        holder.tvName.setText(student.getStudentID() + " - " + student.getName());
        holder.cbSelect.setChecked(student.isSelected());
        holder.tvEmail.setText(student.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == RecyclerView.NO_POSITION) return;
                Students studentTemp = studentsList.get(position);
                //show transaction's info when click on item
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.student_info);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                TextView tvStudentID, tvStudentName, tvStudentEmail, tvStudentDOB, tvStudentAddress;
                tvStudentID = dialog.findViewById(R.id.tvStudentID);
                tvStudentName = dialog.findViewById(R.id.tvStudentName);
                tvStudentDOB = dialog.findViewById(R.id.tvStudentDOB);
                tvStudentEmail = dialog.findViewById(R.id.tvStudentEmail);
                tvStudentAddress = dialog.findViewById(R.id.tvStudentAddress);
                tvStudentID.setText(String.valueOf(studentTemp.getStudentID()));
                tvStudentName.setText(studentTemp.getName());
                tvStudentDOB.setText(studentTemp.getDob());
                tvStudentEmail.setText(studentTemp.getEmail());
                tvStudentAddress.setText(studentTemp.getAddress());
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private CheckBox cbSelect;
        private TextView tvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItemName);
            cbSelect = itemView.findViewById(R.id.cbSelect);
            tvEmail = itemView.findViewById(R.id.tvItemEmail);
        }
    }
}
