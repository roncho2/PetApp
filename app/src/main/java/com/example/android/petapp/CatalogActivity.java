package com.example.android.petapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.android.petapp.data.PetDbHelper;
import com.example.android.petapp.data.PetContract.petEntry;

//Displays list of pets that were entered and stored in the app.
public class CatalogActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private PetDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_activity);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        PetDbHelper mDbHelper = new PetDbHelper(this);

        displayDatabaseInfo();


    }


    //Temporary helper method to display information in
    //In the onscreen TextView about the state of the
    //Pet database.
    private void displayDatabaseInfo() {

        PetDbHelper mDbHelper = new PetDbHelper(this);

        // Create and/ or open a database to read from it
        SQLiteDatabase database = mDbHelper.getReadableDatabase();


        // Perform this raw SQL query "SELECT * FROM pets
        // To get a Cursor that contain all raw from the pets TABLE.
        Cursor cursor = database.rawQuery(" SELECT * FROM " + petEntry.TABLE_NAME, null);


        try {

            // Display the Number of rows in the Cursor ,
            // Which reflect the numbers of rows in the pet table in the database.
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            displayView.setText("Numbers of row in the pets d  atabase table: " + cursor.getCount());

        } finally {

            // Always close the Cursor when your done reading from it.
            // This releases all its resource and make it valid.
            cursor.close();


        }


    }

    private void insertPet() {


    // Gets the data Repository in write mode.
    SQLiteDatabase database = mDbHelper.getWritableDatabase();

    // Create a ContentValues object where column names are the keys,
    // and Toto's pet attributes are the values.
    ContentValues values = new ContentValues();

        values.put(petEntry.COLUMN_PET_NAME,"Toto");
        values.put(petEntry.COLUMN_PET_BREED,"Terrier");
        values.put(petEntry.COLUMN_PET_GENDER,petEntry.GENDER_MALE);
        values.put(petEntry.COLUMN_PET_WEIGHT,7);


    // Insert a new row for Toto in the database, returning the ID of that new row.
    // The first argument for db.insert() is the pets table name.
    // The second argument provides the name of a column in which the framework
    // can insert NULL in the event that the ContentValues is empty (if
    // this is set to "null", then the framework will not insert a row when
    // there are no values).
    // The third argument is the ContentValues object containing the info for Toto.
    long newRowId = database.insert(petEntry.TABLE_NAME, null, values);


        Log.v("CatalogActivity", "New row ID " + "newRowId" );



}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);


            }

}







