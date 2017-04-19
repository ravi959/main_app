package com.example.ravis.recyclereview_kaosakosal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;
import java.util.ArrayList;

/**
 * Created by ravis on 4/19/2017.
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView rvItem;
    CardView cvItem;
    final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvItem = (RecyclerView) findViewById(R.id.rvItem);
        cvItem = (CardView) findViewById(R.id.cvItem);
        rvItem.setHasFixedSize(true);
        String url = "http://adroapp.net16.net/customer/product.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        try {
                            ArrayList<product> productList = new JsonConverter<product>().toArrayList(response, product.class);
                            ProductAdapter adapter = new ProductAdapter(getApplicationContext(), productList);

                            rvItem.setAdapter(adapter);
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "You need network connection", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    Log.d(TAG, error.toString());
                    Toast.makeText(getApplicationContext(), "Error from host or check your network", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LinearLayoutManager manager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rvItem.setLayoutManager(manager);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

}