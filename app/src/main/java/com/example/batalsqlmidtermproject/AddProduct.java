package com.example.batalsqlmidtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {
    Button cancelAddProductButton, addProductButton;
    EditText txtAddProductName, txtAddProductDesc,txtAddProductQty,txtAddProductPrice;
    MyDatabaseHelper db = new MyDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initialize();

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtAddProductName.getText().toString().equals("") || txtAddProductDesc.getText().toString().equals("") || txtAddProductQty.getText().toString().equals("") || txtAddProductPrice.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Error! Please Complete All Fields!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String productName = txtAddProductName.getText().toString();
                    String productDesc = txtAddProductDesc.getText().toString();
                    int productQty = Integer.parseInt(txtAddProductQty.getText().toString());
                    double productPrice = Double.parseDouble(txtAddProductPrice.getText().toString());
                    db.addProduct(productName,productDesc,productPrice,productQty);

                    Intent intent = new Intent(AddProduct.this, ProductInformation.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

        cancelAddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduct.this, ProductInformation.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void initialize() {
        txtAddProductName = findViewById(R.id.txtAddProductName);
        txtAddProductDesc = findViewById(R.id.txtAddProductDesc);
        txtAddProductQty = findViewById(R.id.txtAddProductQty);
        txtAddProductPrice = findViewById(R.id.txtAddProductPrice);
        addProductButton = findViewById(R.id.addProductButton);
        cancelAddProductButton = findViewById(R.id.cancelAddProductButton);

    }
}