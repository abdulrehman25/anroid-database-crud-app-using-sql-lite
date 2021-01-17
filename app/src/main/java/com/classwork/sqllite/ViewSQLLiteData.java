package com.classwork.sqllite;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewSQLLiteData extends AppCompatActivity {
    DbHelper dbHelper;
    EditText editTextId,editTextName,editTextEmail,editTextMobile;
    Button btnInsert,btnUpdate,btnDelete,btnSearch,btnView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_sqllite_data);
        TextView textView = findViewById(R.id.view_data);
        DbHelper dbHelper = new DbHelper(this);
        String data = dbHelper.getData();
        textView.setText(data);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
