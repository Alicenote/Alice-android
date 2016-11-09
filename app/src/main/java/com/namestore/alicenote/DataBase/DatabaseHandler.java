package com.namestore.alicenote.DataBase;

/**
 * Created by Nhocnhinho on 5/26/2015.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_ADDR = "address";
    private static final String KEY_EMAIL = "email";


    private static final String KEY_COMMENT = "comment";
/*    private static final String KEY_NOWTIME = "now_time";
    private static final String KEY_NOTIFICATION = "notification";*/

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_BIRTHDAY + " TEXT," + KEY_PH_NO + " TEXT," + KEY_EMAIL + " TEXT,"
               /* + KEY_NOWTIME + " TEXT,"*/ + KEY_ADDR + " TEXT," + KEY_COMMENT + " TEXT" /*+ KEY_NOTIFICATION + " TEXT"*/ + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }


    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_BIRTHDAY, contact.getBirthday());
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

    //    values.put(KEY_NOWTIME, contact.getNowTime());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_ADDR, contact.getAddress());
       values.put(KEY_COMMENT, contact.getComment());
    //    values.put(KEY_NOTIFICATION, contact.getNotification());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO, KEY_BIRTHDAY, KEY_ADDR,/* KEY_NOWTIME,*/
                KEY_EMAIL, KEY_COMMENT/*, KEY_NOTIFICATION*/}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)/*, cursor.getString(7), cursor.getString(8)*/);


        // return contact
        return contact;
    }


    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setBirthday(cursor.getString(2));
                contact.setPhoneNumber(cursor.getString(3));
                contact.setEmail(cursor.getString(4));
              //  contact.setNowTime(cursor.getString(5));
                contact.setAddress(cursor.getString(5));
               contact.setComment(cursor.getString(6));
              //  contact.setNotification(cursor.getString(8));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToPrevious());
        }

        // return contact list
        return contactList;
    }

    public int getID() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        cursor.moveToLast();
        int id = Integer.parseInt(cursor.getString(0));
        return id;
    }


    // Updating single contact
 /*   public int updateContact(int id, String smsBody, String phoneNumber,String time,String interval,String nowTime,String rule,String pending,String notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, smsBody); // Contact Name
        values.put(KEY_PH_NO,  phoneNumber); // Contact Phone
        values.put(KEY_BIRTHDAY, time);
        values.put(KEY_ADDR, interval);
        values.put(KEY_NOWTIME, contact.getNowTime());
        values.put(KEY_EMAIL, contact.getRule());
        values.put(KEY_PENDING, contact.getComment());
        values.put(KEY_NOTIFICATION, contact.getNotification());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }*/

    // Deleting single contact
    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


    public void deleteAllContact()
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(TABLE_CONTACTS, null, null);

    }

}