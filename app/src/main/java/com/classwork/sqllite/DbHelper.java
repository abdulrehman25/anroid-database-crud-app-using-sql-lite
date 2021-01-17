package com.classwork.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    SQLiteDatabase database;
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENT = "_student";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query_Create_table = "CREATE TABLE "+ TABLE_STUDENT + "("
                +KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_NAME + " TEXT," +
                KEY_EMAIL + " TEXT,"+
                KEY_MOBILE+" TEXT)";
        sqLiteDatabase.execSQL(query_Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(database);
    }

    public long insertStudent(String name, String email, String mobile){
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_EMAIL,email);
        contentValues.put(KEY_MOBILE,mobile);

        return  database.insert(TABLE_STUDENT,null,contentValues);
    }

    public String getData(){
        database = this.getReadableDatabase();
        String [] columns = new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor = database.query(TABLE_STUDENT,columns,null,null,null,null,null);

        int studentIdIndex = cursor.getColumnIndex(KEY_ID);
        int studentNameIndex = cursor.getColumnIndex(KEY_NAME);
        int studentEmailIndex = cursor.getColumnIndex(KEY_EMAIL);
        int studentMobileIndex = cursor.getColumnIndex(KEY_MOBILE);
        String result = "";
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            result += "Id : "+cursor.getString(studentIdIndex)+"\n" +
                    "Name : "+cursor.getString(studentNameIndex)+"\n" +
                    "Email : "+cursor.getString(studentEmailIndex)+"\n" +
                    "Mobile : "+cursor.getString(studentMobileIndex)+"\n"
            ;

        }
        database.close();
        return result;
    }

    public void deleteStudent(long id){
        database = this.getWritableDatabase();
        database.delete(TABLE_STUDENT,KEY_ID+"="+id,null
        );
    }

    public void updateStudent(long id,String name, String email, String mobile){
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_EMAIL,email);
        contentValues.put(KEY_MOBILE,mobile);

        database.update(TABLE_STUDENT,contentValues,KEY_ID+"="+id,null);
        database.close();
    }

    public String getName(long id){
        database = this.getReadableDatabase();

        String [] columns = new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor = database.query(TABLE_STUDENT,columns,KEY_ID+"="+id,null,null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
            String name = cursor.getString(1);
            return name;
        }
        return null;
    }

    public String getEmail(long id){
        database = this.getReadableDatabase();

        String [] columns = new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor = database.query(TABLE_STUDENT,columns,KEY_ID+"="+id,null,null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
            String email = cursor.getString(2);
            return email;
        }
        return null;
    }

    public String getMobile(long id){
        database = this.getReadableDatabase();

        String [] columns = new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor = database.query(TABLE_STUDENT,columns,KEY_ID+"="+id,null,null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
            String mobile = cursor.getString(3);
            return mobile;
        }
        return null;
    }
}
