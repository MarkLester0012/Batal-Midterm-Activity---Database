package com.example.batalsqlmidtermproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.MyViewHolder> {
    private ArrayList<Product> productList;

    public ProductRecyclerAdapter(ArrayList<Product> studentList){
        this.productList = studentList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView productIDTextView;
        private TextView productName;
        private ImageButton viewButton,editProductImageButton,deleteButton;
        public MyViewHolder(final View itemView) {
            super(itemView);
            productIDTextView = itemView.findViewById(R.id.product_id);
            productName = itemView.findViewById(R.id.product_name);
            viewButton = itemView.findViewById(R.id.viewProductImageButton);
            editProductImageButton = itemView.findViewById(R.id.editProductImageButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            viewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Core.ProductID = productList.get(position).getProductID();
                    Intent intent = new Intent(itemView.getContext(), ViewProduct.class);
                    itemView.getContext().startActivity(intent);
                }
            });
            editProductImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Core.ProductID = productList.get(position).getProductID();
                    Intent intent = new Intent(itemView.getContext(), EditProduct.class);
                    itemView.getContext().startActivity(intent);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext())
                    //set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle("Confirm Delete")
                    //set message
                    .setMessage("Do you want to delete this product?")
                    //set positive button
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MyDatabaseHelper db = new MyDatabaseHelper(itemView.getContext());
                            db.deleteRow(productList.get(position).getProductID());
                            productList.remove(position);
                            notifyDataSetChanged();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                    //set what should happen when negative button is clicked
                        Toast.makeText(itemView.getContext(),"Cancelled",Toast.LENGTH_LONG).show();
                        }
                    }).show();
                }
            }); //End of OnClick Listener
        }
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String productID = productList.get(position).getProductID();
        String name = productList.get(position).getProductName();
        holder.productIDTextView.setText("Product ID: " + productID + " ");
        holder.productName.setText("Name: " + name + " ");


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

}
