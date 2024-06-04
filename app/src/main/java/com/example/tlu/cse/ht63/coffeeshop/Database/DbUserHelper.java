package com.example.tlu.cse.ht63.coffeeshop.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tlu.cse.ht63.coffeeshop.Models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbUserHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "MyDatabase.db";
    private static final int DATABASEVERSION = 1;
    private final String TABLE_NAME = "User";
    private final String COLUMN_ID = "id";
    private final String COLUMN_FULLNAME = "full_name";
    private final String COLUMN_USERNAME = "user_Name";
    private final String COLUMN_PASSWORD = "password";
    private final String COLUMN_IS_EMPLOYEE = "is_employee";
    private final String COLUMN_STATUS = "status";
    private final String COLUMN_CREATED_AT = "created_at";

    public DbUserHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( String.format("CREATE TABLE %s(\n" +
                "%s Integer  PRIMARY KEY AUTOINCREMENT,\n" +
                "%s NVARCHAR(255) not null,\n" +
                "%s NVARCHAR(255) not NULL UNIQUE,\n" +
                "%s NVARCHAR(255) not null,\n" +
                "%s bit not null DEFAULT(0),\n" +
                "%s bit not NULL DEFAULT(1),\n" +
                "%s DATETIME not null DEFAULT(CURRENT_TIMESTAMP)\n" +
                ")", TABLE_NAME, COLUMN_ID,
                COLUMN_FULLNAME, COLUMN_USERNAME, COLUMN_PASSWORD,
                COLUMN_IS_EMPLOYEE, COLUMN_STATUS, COLUMN_CREATED_AT) );
    }

    public boolean insertUser(User user){

        ContentValues values = new ContentValues();
        values.put( COLUMN_FULLNAME, user.getFullName() );
        values.put( COLUMN_USERNAME, user.getUserName() );
        values.put( COLUMN_PASSWORD, user.getPassword() );
        values.put( COLUMN_IS_EMPLOYEE, user.getIs_employee() );

        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, values);
        return result > 0;
    }
    public boolean updateUser(User user){

        ContentValues values = new ContentValues();
        values.put( COLUMN_FULLNAME, user.getFullName() );
        values.put( COLUMN_USERNAME, user.getUserName() );
        values.put( COLUMN_PASSWORD, user.getPassword() );
        values.put( COLUMN_IS_EMPLOYEE, user.getIs_employee() );
        values.put( COLUMN_STATUS, user.getStatus() );

        SQLiteDatabase db = getWritableDatabase();
        long result = db.update(TABLE_NAME, values, String.format("%s", COLUMN_ID),
                new String[]{Integer.toString(user.getId())}
                );
        return result > 0;
    }

    public boolean deleteUser(User user){

        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_NAME, "id = ? ",
        new String[]{Integer.toString(user.getId())});

        return result > 0;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<User> getAll(){
        List<User> userList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() == 0){
            return userList;
        }
        while(cursor.moveToNext()){
            int indexID = cursor.getColumnIndex(COLUMN_ID);
            int indexFullName = cursor.getColumnIndex(COLUMN_FULLNAME);
            int indexUserName = cursor.getColumnIndex(COLUMN_USERNAME);
            int indexPassword = cursor.getColumnIndex(COLUMN_PASSWORD);
            int indexIsEmployee = cursor.getColumnIndex(COLUMN_IS_EMPLOYEE);
            int indexStatus = cursor.getColumnIndex(COLUMN_STATUS);
            int indexCreatedAt = cursor.getColumnIndex(COLUMN_CREATED_AT);

            int id = cursor.getInt(indexID);
            String fullName = cursor.getString(indexFullName);
            String userName = cursor.getString(indexUserName);
            String password = cursor.getString(indexPassword);
            boolean isEmployee = cursor.getInt(indexIsEmployee) == 1;
            boolean status = cursor.getInt(indexStatus) == 1;
            Date createdAt = null;
            try {
                createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( cursor.getString(indexCreatedAt) );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            User user = new User(id, fullName, userName, password, isEmployee, status, createdAt);
            userList.add(user);
        }

        cursor.close();
        return userList;

    }

    public User getUser(String userName, String password){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?", TABLE_NAME, COLUMN_USERNAME, COLUMN_PASSWORD);
        Cursor cursor = db.rawQuery(query, new String[]{userName, password});

        if(cursor.getCount() == 0){
            return null;
        }
        cursor.moveToNext();
        int indexID = cursor.getColumnIndex(COLUMN_ID);
        int indexFullName = cursor.getColumnIndex(COLUMN_FULLNAME);
        int indexUserName = cursor.getColumnIndex(COLUMN_USERNAME);
        int indexPassword = cursor.getColumnIndex(COLUMN_PASSWORD);
        int indexIsEmployee = cursor.getColumnIndex(COLUMN_IS_EMPLOYEE);
        int indexStatus = cursor.getColumnIndex(COLUMN_STATUS);
        int indexCreatedAt = cursor.getColumnIndex(COLUMN_CREATED_AT);

        int id = cursor.getInt(indexID);
        String fullName = cursor.getString(indexFullName);
        boolean isEmployee = cursor.getInt(indexIsEmployee) == 1;
        boolean status = cursor.getInt(indexStatus) == 1;
        Date createdAt = null;
        try {
            createdAt = new SimpleDateFormat().parse( cursor.getString(indexCreatedAt) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        User user = new User(id, fullName, userName, password, isEmployee, status, createdAt);
        return user;
    }
}
