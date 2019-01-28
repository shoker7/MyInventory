package com.shoker.myinventory;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.shoker.myinventory.data.BookDBHelper;

public class EditorActivity extends AppCompatActivity {


    private BookDBHelper dbHelper;
    EditText nameEdit;
    EditText priceEdit;
    EditText quantityEdit;
    EditText supplierNameEdit;
    EditText supplierPhoneEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        dbHelper = new BookDBHelper(this);

        nameEdit =findViewById(R.id.et_book_name);
        priceEdit =findViewById(R.id.et_price);
        quantityEdit =findViewById(R.id.et_quantity);
        supplierNameEdit =findViewById(R.id.et_supplier_name);
        supplierPhoneEdit =findViewById(R.id.et_supplier_phone);

    }

    public void decreaseQuantity(View view) {
        String previousValueString = quantityEdit.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            return;
        } else if (previousValueString.equals("0")) {
            return;
        } else {
            previousValue = Integer.parseInt(previousValueString);
            quantityEdit.setText(String.valueOf(previousValue - 1));
        }
    }

    public void increaseQuantity(View view) {
        String previousValueString = quantityEdit.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            previousValue = 0;
        } else {
            previousValue = Integer.parseInt(previousValueString);
        }
        quantityEdit.setText(String.valueOf(previousValue + 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                dbHelper.insertData();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
