package com.imam2trk.belajarsqlite.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imam2trk.belajarsqlite.Product;
import com.imam2trk.belajarsqlite.R;
import com.imam2trk.belajarsqlite.database.SqliteDatabase;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private Context context;
    private List<Product> listProducts;
    private SqliteDatabase mDatabase;

    public ProductAdapter(Context context, List<Product> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
        mDatabase = new SqliteDatabase(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_layout,parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product singleProduct = listProducts.get(position);
        holder.nama.setText(singleProduct.getNama());
        holder.editProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(singleProduct);
            }
        });
        holder.hapusProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete row from database
                mDatabase.deleteProduct(singleProduct.getId());
                // refresh the activity page
                ((Activity)context).finish();
                context.startActivity(((Activity)context).getIntent());
            }
        });
    }

    public int getItemCount(){ return listProducts.size(); }
    private void editTaskDialog(final Product product){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_product_layout, null);

        final EditText nameField = subView.findViewById(R.id.enter_name);
        final EditText quantityField = subView.findViewById(R.id.enter_quantity);

        if (product != null){
            nameField.setText(product.getNama());
            quantityField.setText(String.valueOf(product.getJumlah()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Produk");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT PRODUK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final int quantity = Integer.parseInt(quantityField.getText().toString());

                if (TextUtils.isEmpty(name) || quantity<=0){
                    Toast.makeText(context, "Ada input yang salah!", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateProduct(new Product(product.getId(), name, quantity));
                    // refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Batal", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
