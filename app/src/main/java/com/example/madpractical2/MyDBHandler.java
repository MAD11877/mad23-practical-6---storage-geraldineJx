package com.example.madpractical2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyDBHandler extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "user";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOLLOWED = "followed";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TITLE = "MyDBHandler";

    public MyDBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " BOOLEAN" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUsers(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                boolean followed = cursor.getInt(cursor.getColumnIndex(COLUMN_FOLLOWED)) == 1;

                User user = new User(name, description, id, followed);
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return users;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("description", user.getDescription());
        values.put("followed", user.getFollowed() ? 1 : 0);

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(user.getId())};

        db.update("user", values, whereClause, whereArgs);

        db.close();
    }

    public User getUsersId(int userId) {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(userId) };
        Cursor cursor = db.query(TABLE_USERS, null, whereClause, whereArgs, null, null, null);
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                boolean followed = cursor.getInt(cursor.getColumnIndex(COLUMN_FOLLOWED)) == 1;

                user = new User(name, description, id, followed);
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return user;
    }

    public void createUsers(int numOfUsers) {
        Log.i(TITLE, "creating " + numOfUsers + " users!");
        for (int i = 0; i < numOfUsers; i++) {
            User user = new User("Name" + randomNum(), "Description" + randomNum()
                    , null, randomBoolean());
            addUsers(user);
        }
    }

    //Generate num for name
    private int randomNum() {
        Random ran = new Random();
        int myNumber = ran.nextInt(999999999);
        return myNumber;
    }

    public boolean randomBoolean() {
        Random rd = new Random(); // creating Random object
        boolean myBoolean = rd.nextBoolean();
        return myBoolean;
    }

}

