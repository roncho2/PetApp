package com.example.android.petapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.petapp.data.PetContract.petEntry;
public class PetDbHelper extends SQLiteOpenHelper {

    // Name of the Database class
    private static final String DATABASE_NAME = "shelter.database";


    // Database Version, If you change the database schema ,
    // then the version must be changed also.
    private static final int DATABASE_VERSION = 1;


    public PetDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // CREATE TABLE pets ( int PRIMARY KEY, name TEXT, weight INTEGER );
        // Create a String that contains the SQL statement to create the pets table.
     String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + petEntry.TABLE_NAME + " ("
                + petEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + petEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + petEntry.COLUMN_PET_BREED + " TEXT,"
                + petEntry.COLUMN_PET_GENDER + " TEXT NOT NULL, "
                + petEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

//        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
//                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
//                + PetEntry.COLUMN_PET_BREED + " TEXT, "
//                + PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
//                + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";


     // Execute the SQL statement
     database.execSQL(SQL_CREATE_PETS_TABLE);

    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}