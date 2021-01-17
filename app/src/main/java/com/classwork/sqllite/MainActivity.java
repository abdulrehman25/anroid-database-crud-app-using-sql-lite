package com.classwork.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    EditText editTextId,editTextName,editTextEmail,editTextMobile;
    Button btnInsert,btnUpdate,btnDelete,btnSearch,btnView;

    String id,name,email,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.txtId);
        editTextName = findViewById(R.id.txtName);
        editTextEmail= findViewById(R.id.txtEmail);
        editTextMobile = findViewById(R.id.txtMobile);

        btnInsert = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnView = findViewById(R.id.btnView);
        btnDelete = findViewById(R.id.btnDelete);
        btnSearch = findViewById(R.id.btnSearch);

        dbHelper = new DbHelper(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnSave:
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                mobile = editTextMobile.getText().toString();
                if (name.equals("") | email.equals("") | mobile.equals("")){
                    Toast.makeText(this,"Please Fill the Fields",Toast.LENGTH_LONG).show();
                }
                else{
                    dbHelper.insertStudent(name,email,mobile);
                    editTextId.setText("");
                    editTextName.setText("");
                    editTextEmail.setText("");
                    editTextMobile.setText("");
                    Toast.makeText(this,"Saved Successfully",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnUpdate:
                id = editTextId.getText().toString().trim();
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                mobile = editTextMobile.getText().toString();
                if (id.equals("") | name.equals("") | email.equals("") | mobile.equals("")){
                    Toast.makeText(this,"Please Fill the Fields",Toast.LENGTH_LONG).show();
                }
                else{
                    long studentId = Long.parseLong(id);
                    dbHelper.updateStudent(studentId,name,email,mobile);
                    editTextId.setText("");
                    editTextName.setText("");
                    editTextEmail.setText("");
                    editTextMobile.setText("");
                    Toast.makeText(this,"Saved Successfully",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnDelete:
                id = editTextId.getText().toString();
                if (id.equals("")){
                    Toast.makeText(this,"Please Fill the ID",Toast.LENGTH_LONG).show();
                }
                else{
                    long studentId = Long.parseLong(id);
                    dbHelper.deleteStudent(studentId);
                    editTextId.setText("");
                    editTextName.setText("");
                    editTextEmail.setText("");
                    editTextMobile.setText("");
                    Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnView:
                Intent intent = new Intent(getApplicationContext(),ViewSQLLiteData.class);
                startActivity(intent);
                break;
            case R.id.btnSearch:
                id = editTextId.getText().toString().trim();
                if (id.equals("")){
                    Toast.makeText(this,"Please Fill the ID",Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        long studentId = Long.parseLong(id);
                        name = dbHelper.getName(studentId);
                        email = dbHelper.getEmail(studentId);
                        mobile = dbHelper.getMobile(studentId);
                        editTextId.setText(String.valueOf(studentId));
                        editTextName.setText(name);
                        editTextEmail.setText(email);
                        editTextMobile.setText(mobile);
                        Toast.makeText(this,"Search Successfully",Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e){
                        Toast.makeText(this,"Id is not available or Invalid",Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }
}