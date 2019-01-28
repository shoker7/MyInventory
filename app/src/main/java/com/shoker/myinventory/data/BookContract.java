package com.shoker.myinventory.data;

import android.provider.BaseColumns;

public class BookContract {

    /* Not instantiable */
    private BookContract() {
    }

    public static abstract class BookEntry implements BaseColumns {

        /* Not instantiable */
        private BookEntry() {
        }

        public static final String TABLE_NAME = "books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
        public static final String COLUMN_PRODUCT_IMAGE ="product_image";

        public static final String SQL_CREATE_BOOKS_TABLE =  "CREATE TABLE " +TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL DEFAULT 0, "
                + COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + COLUMN_SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL DEFAULT 0,"
                +COLUMN_PRODUCT_IMAGE + " INTEGER NOT NULL" + ");";
    }
}
