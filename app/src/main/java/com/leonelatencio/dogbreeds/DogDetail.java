package com.leonelatencio.dogbreeds;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.leonelatencio.dogbreeds.R;

import com.squareup.picasso.Picasso;
import com.leonelatencio.dogbreeds.API.ApiClient;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_detail);
        Bundle extras = getIntent().getExtras();
        String dog_breed = extras.getString("breed");

        //get Layout items
        final TextView breed = (TextView) findViewById(R.id.breed);
        final ImageView dog_pic = (ImageView) findViewById(R.id.dog_pic);

        breed.setText(dog_breed);

        Call<com.leonelatencio.dogbreeds.Model.Response> call;
        call = ApiClient.get().getImage(dog_breed);
        call.enqueue(new Callback<com.leonelatencio.dogbreeds.Model.Response>() {
            @Override
            public void onFailure(Call<com.leonelatencio.dogbreeds.Model.Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error loading the details", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call<com.leonelatencio.dogbreeds.Model.Response> call, Response<com.leonelatencio.dogbreeds.Model.Response> response) {
                if (response.body() != null) {
                    List<String> dog_pics = response.body().getMessage();
                    String first_dog_pic_url = dog_pics.get(0);

                    Picasso.with(getApplicationContext())
                            .load(first_dog_pic_url)
                            .into(dog_pic);
                }

            }
        });

    }

}
