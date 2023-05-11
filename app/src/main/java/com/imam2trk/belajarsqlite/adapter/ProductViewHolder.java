package com.imam2trk.belajarsqlite.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.imam2trk.belajarsqlite.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView nama;
    public ImageView hapusProduk;
    public ImageView editProduk;

    public ProductViewHolder(View itemView){
        super(itemView);
        nama = itemView.findViewById(R.id.product_name);
        hapusProduk = itemView.findViewById(R.id.delete_product);
        editProduk = itemView.findViewById(R.id.edit_product);
    }
}
