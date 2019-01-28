package com.shoker.myinventory.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.shoker.myinventory.R;
import com.shoker.myinventory.data.BookContract.BookEntry;

public class BookDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;

    Context context;


    public BookDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Execute the SQL statement
        db.execSQL(BookEntry.SQL_CREATE_BOOKS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void insertData(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put(BookEntry.COLUMN_PRODUCT_NAME,"Gone Girl");
        values.put(BookEntry.COLUMN_PRODUCT_PRICE,20);
        values.put(BookEntry.COLUMN_PRODUCT_QUANTITY,50);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME,"The books");
        values.put(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER,0252352555);
        values.put(BookEntry.COLUMN_PRODUCT_IMAGE, R.drawable.cola);

        long newRowId = db.insert(BookEntry.TABLE_NAME,null,values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(context, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(context, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public void insertData(String productname,int productprice,int productquantity,String suppliername,int suppliernumber,int productimage){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put(BookEntry.COLUMN_PRODUCT_NAME,productname);
        values.put(BookEntry.COLUMN_PRODUCT_PRICE,productprice);
        values.put(BookEntry.COLUMN_PRODUCT_QUANTITY,productquantity);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME,suppliername);
        values.put(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER,suppliernumber);
        values.put(BookEntry.COLUMN_PRODUCT_IMAGE, productimage);

        long newRowId =  db.insert(BookEntry.TABLE_NAME,null,values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(context, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(context, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor queryData(){
        SQLiteDatabase db = getReadableDatabase();
        String [] columns = {BookEntry._ID,
                BookEntry.COLUMN_PRODUCT_NAME,
                BookEntry.COLUMN_PRODUCT_QUANTITY,
                BookEntry.COLUMN_SUPPLIER_NAME,
                BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
                BookEntry.COLUMN_PRODUCT_IMAGE};
        Cursor cursor = db.query(BookEntry.TABLE_NAME,columns,null,null,null,null,null);
        return cursor;
    }

    public Cursor queryData(String ...columnstoquery){
        SQLiteDatabase db = getReadableDatabase();
        String [] columns =columnstoquery;
        Cursor cursor = db.query(BookEntry.TABLE_NAME,columns,null,null,null,null,null);
        return cursor;
    }
}
