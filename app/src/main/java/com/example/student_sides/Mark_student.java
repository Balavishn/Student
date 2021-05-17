package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.staff_navigation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Mark_student extends AppCompatActivity {
    RecyclerView present;
    DatabaseReference studentref,staffref;
    String class_sec,sub;
    List<String> student_name;
    List<Integer> present_listt;
    int clickcount;
    Button ok;

    Workbook wb=new HSSFWorkbook();
    Cell cell=null,cell1=null;

    StorageReference refst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_student);
        present=findViewById(R.id.mark_rec);
        present.setLayoutManager(new LinearLayoutManager(this));
        student_name=new ArrayList<>();
        present_listt=new ArrayList<>();
        ok=findViewById(R.id.ok_mark);
        class_sec=getIntent().getStringExtra("class");
        sub=getIntent().getStringExtra("subject");
        String classs=class_sec.substring(0,class_sec.indexOf("-"));
        refst= FirebaseStorage.getInstance().getReference("Academic").child("Mark").child(classs).child(class_sec).child(sub);
        staffref= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic").child(classs).child("Mark").child(class_sec);
        studentref= FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");
//        Task<Uri> uri=refst.child("mark.xls").getDownloadUrl();

        studentref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s:snapshot.getChildren()){
                    student_name.add(s.child("Student_Name").getValue().toString());
                    present_listt.add(0);
                    Log.d("student_name",s.child("Student_Name").getValue().toString());
                }
                Mark_adapter a=new Mark_adapter(student_name,Mark_student.this);
                present.setAdapter(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < student_name.size(); i++) {
                    staffref.child(student_name.get(i)).child(sub).child(student_name.get(i)).setValue(present_listt.get(i));
                    Log.d("Last_present", student_name.get(i) + " " + present_listt.get(i));
                }
                export();
                Toast.makeText(getApplicationContext(),"Mark Update",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void presentt(String name,int pos,String present){
        present_listt.set(pos,Integer.parseInt(present));
        Log.d("present_student",name+" "+present);
    }
    public void export(){
        String filename="mark";
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        Sheet sheet = wb.createSheet("Details");
        for(int i=1;i<=student_name.size();i++){
            Row row = sheet.createRow(i);
            cell=row.createCell(0);
            cell1=row.createCell(1);
            cell.setCellValue(student_name.get(i-1));
            cell1.setCellValue(present_listt.get(i-1));
            cell.setCellStyle(cellStyle);
            cell1.setCellStyle(cellStyle);
        }

        File file = new File(getExternalFilesDir(null),filename + ".xls");
        Log.d("filestore", file.getAbsolutePath().toString());
        if (!file.isDirectory()) {
            Log.d("not exist", "notexits");
        }
        if (true) {
            Log.d("crete exist", "creat exits");
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            wb.write(outputStream);
            InputStream inputStream=new FileInputStream(file);
            Toast.makeText(getApplicationContext(), "File Stored in" + file.getAbsolutePath().toString(), Toast.LENGTH_LONG).show();
            refst.child("mark.xls").putStream(inputStream).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(),"file Upload",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"file not Upload",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}