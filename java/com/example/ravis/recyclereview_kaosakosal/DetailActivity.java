package com.example.ravis.recyclereview_kaosakosal;



/*
* This activity displays the detailed information for any particular recyclerview item
* */





import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    ImageView ivImage;
    TextView setPrice, tvTitle, first, last, email, desc, price, lat, lon, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        first = (TextView) findViewById(R.id.setfirst);
        last = (TextView) findViewById(R.id.setlast);
        email = (TextView) findViewById(R.id.setemail);
        location = (TextView) findViewById(R.id.setlocation);
        setPrice = (TextView) findViewById(R.id.setPrice);
        desc = (TextView) findViewById(R.id.setdesc);
        if (getIntent().getSerializableExtra("product") != null) {
            product product = (product) getIntent().getSerializableExtra("product");
            String fullUrl = "https://sick-pints.000webhostapp.com/customer/" + product.photo;
            Picasso.with(this)
                    .load(fullUrl)
                    .placeholder(R.drawable.img)
                    .error(android.R.drawable.stat_notify_error)
                    .into(ivImage);
            first.setText(product.firstname);
            last.setText(product.lastname);
            email.setText(product.email);
            location.setText(product.location);
            desc.setText(product.description);
            setPrice.setText("" + product.price);

        }
    }
}
