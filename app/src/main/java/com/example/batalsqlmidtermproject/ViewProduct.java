package com.example.batalsqlmidtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewProduct extends AppCompatActivity {
    Button cancelButton, viewEditButton;
    EditText viewTxtProductID, viewTxtProductName, viewTxtProductDesc, iewTxtProductQty, viewTxtProductPrice;
    MyDatabaseHelper db = new MyDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        initialize();
        viewEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Core.ProductID = viewTxtProductID.getText().toString();
                Intent intent = new Intent(ViewProduct.this, EditProduct.class);
                startActivity(intent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProduct.this, ProductInformation.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void initialize() {
        viewEditButton = findViewById(R.id.viewEditButton);
        cancelButton = findViewById(R.id.cancelButton);
        viewTxtProductID = findViewById(R.id.viewTxtProductID);
        viewTxtProductName = findViewById(R.id.viewTxtProductName);
        viewTxtProductDesc = findViewById(R.id.viewTxtProductDesc);
        iewTxtProductQty = findViewById(R.id.viewTxtProductQty);
        viewTxtProductPrice = findViewById(R.id.viewTxtProductPrice);
        Cursor data = db.getdata();
        StringBuffer buffer = new StringBuffer();
        while(data.moveToNext()){
            if(data.getString(0).equals(Core.ProductID)) {
                viewTxtProductID.setText(data.getString(0));
                viewTxtProductName.setText(data.getString(1));
                viewTxtProductDesc.setText(data.getString(2));
                iewTxtProductQty.setText(data.getString(3));
                viewTxtProductPrice.setText(data.getString(4));
            }
        }

    }
}