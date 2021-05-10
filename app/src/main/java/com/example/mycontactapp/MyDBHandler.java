package com.example.mycontactapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {
    //Σταθερές για τη ΒΔ (όνομα ΒΔ, έκδοση, πίνακες κλπ)
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "customersDB.db";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_BIRTHDAY = "birthday";

    //Constructor
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Δημιουργία του σχήματος της ΒΔ (πίνακας products)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " +
                TABLE_CONTACTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_LASTNAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_NUMBER + " INTEGER," +
                COLUMN_BIRTHDAY + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //Αναβάθμιση ΒΔ: εδώ τη διαγραφώ και τη ξαναδημιουργώ ίδια
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    //Μέθοδος για προσθήκη ενός προϊόντος στη ΒΔ
    public void addContact(CustomerInfo customer) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, customer.getName());
        values.put(COLUMN_LASTNAME,customer.getLastName());
        values.put(COLUMN_EMAIL,customer.getEmail());
        values.put(COLUMN_NUMBER,customer.getNumber());
        values.put(COLUMN_BIRTHDAY,customer.getBirthday());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    //Μέθοδος για εύρεση προϊόντος βάσει ονομασίας του
    public CustomerInfo findCustomer (String name) {
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " +
                COLUMN_NAME + " = '" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CustomerInfo customer = new CustomerInfo();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            customer.setID(Integer.parseInt(cursor.getString(0)));
            customer.setName(cursor.getString(1));
            customer.setLastName(cursor.getString(2));
            customer.setEmail(cursor.getString(3));
            customer.setNumber(Integer.parseInt(cursor.getString(4)));
            customer.setBirthday(cursor.getString(5));
            cursor.close();
        } else {
            customer = null;
        }
        db.close();
        return customer;
    }

    //Μέθοδος για διαγραφή προϊόντος βάσει ονομασίας του
    public boolean deleteCustomer(String name) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " +
                COLUMN_NAME + " = '" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CustomerInfo customer = new CustomerInfo();
        if (cursor.moveToFirst()) {
            customer.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_CONTACTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(customer.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}