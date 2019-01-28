package com.shoker.myinventory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoker.myinventory.data.BookContract.BookEntry;
import com.shoker.myinventory.data.BookDBHelper;

public class CatalogActivity extends AppCompatActivity {


    private BookDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new BookDBHelper(this);
        displayDatabaseInfo();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                mDbHelper.insertData();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void displayDatabaseInfo() {
        TextView displayView =findViewById(R.id.tv_book);
        Cursor cursor =mDbHelper.queryData();
        try {

            displayView.setText("The Books table contains " + cursor.getCount()+"Books .\n\n");
            displayView.append(BookEntry._ID+"-"
                    +BookEntry.COLUMN_PRODUCT_NAME+"-"
                    +BookEntry.COLUMN_PRODUCT_QUANTITY+"-"
                    +BookEntry.COLUMN_SUPPLIER_NAME+"-"
                    +BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            int idColumnIndex =cursor.getColumnIndex(BookEntry._ID);
            int nameColumnIndex =cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
            int quantityColumnIndex =cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex =cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME);
            int supplierNumberColumnIndex =cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
            int imageColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_IMAGE);

            while (cursor.moveToNext()){
                int currentID = cursor.getInt(idColumnIndex);
                String currentName =cursor.getString(nameColumnIndex);
                int currentQuantity =cursor.getInt(quantityColumnIndex);
                String currentSupplierName =cursor.getString(supplierNameColumnIndex);
                int currentSupplierNumber =cursor.getInt(supplierNumberColumnIndex);
                int currentImage =cursor.getInt(imageColumnIndex);
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierNumber));
            }
        } finally {
            cursor.close();
        }
        }

    @Override
    protected void onStart() {
        displayDatabaseInfo();
        super.onStart();
    }
}
