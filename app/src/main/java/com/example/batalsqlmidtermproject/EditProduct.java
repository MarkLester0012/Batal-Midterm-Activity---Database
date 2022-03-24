package com.example.batalsqlmidtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProduct extends AppCompatActivity {
    Button editCancelButton,editButton;
    EditText editTextProductID, editTextProductName, editTextProductDesc, editTextProductPrice,editTextProductQty;
    MyDatabaseHelper db = new MyDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        initialize();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextProductID.getText().toString().equals("") || editTextProductName.getText().toString().equals("") || editTextProductDesc.getText().toString().equals("") || editTextProductPrice.getText().toString().equals("") || editTextProductQty.getText().toString().equals("")) {
                    Toast.makeText(EditProduct.this, "Error! Please Complete All Fields!", Toast.LENGTH_LONG).show();
                } else {
                    db.updateData(editTextProductID.getText().toString(), editTextProductName.getText().toString(), editTextProductDesc.getText().toString(), editTextProductPrice.getText().toString(), editTextProductQty.getText().toString());
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Update Complete!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(EditProduct.this, ProductInformation.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        editCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProduct.this, ProductInformation.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void initialize() {
        editButton = findViewById(R.id.editButton);
        editCancelButton = findViewById(R.id.editCancelButton);
        editTextProductID = findViewById(R.id.editTextProductID);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductDesc = findViewById(R.id.editTextProductDesc);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        editTextProductQty = findViewById(R.id.editTextProductQty);

        Cursor data = db.getdata();
        StringBuffer buffer = new StringBuffer();
        while(data.moveToNext()){
            if(data.getString(0).equals(Core.ProductID)) {
                editTextProductID.setText(data.getString(0));
                editTextProductName.setText(data.getString(1));
                editTextProductDesc.setText(data.getString(2));
                editTextProductPrice.setText(data.getString(3));
                editTextProductQty.setText(data.getString(4));
            }
        }
    }
}