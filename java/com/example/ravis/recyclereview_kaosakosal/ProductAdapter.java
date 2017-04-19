package com.example.ravis.recyclereview_kaosakosal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by ravis on 4/19/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private ArrayList<product> products;

    public ProductAdapter(Context context, ArrayList<product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.product_cardview_layout, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        final product SelectedProduct = products.get(position);
        holder.first.setText(SelectedProduct.firstname);
        holder.last.setText(SelectedProduct.lastname);
        holder.tvPrice.setText("" + SelectedProduct.price);
        holder.location.setText(SelectedProduct.location);
        String fullUrl = "https://sick-pints.000webhostapp.com/customer/" + SelectedProduct.photo;
        Picasso.with(context)
                .load(fullUrl)
                .placeholder(R.drawable.img)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.ivImage);
        holder.cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, DetailActivity.class);
                in.putExtra("product", SelectedProduct);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        } else return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public CardView cvProduct;
        public ImageView ivImage;
        public TextView tvPrice;
        public TextView first;
        public TextView last;
        public TextView location;

        public ProductViewHolder(View itemView) {
            super(itemView);
            cvProduct = (CardView) itemView.findViewById(R.id.cvItem);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImageUrl);
            first = (TextView) itemView.findViewById(R.id.first);
            last = (TextView) itemView.findViewById(R.id.last);
            location = (TextView) itemView.findViewById(R.id.location);
            tvPrice = (TextView) itemView.findViewById(R.id.Price);
        }
    }
}

