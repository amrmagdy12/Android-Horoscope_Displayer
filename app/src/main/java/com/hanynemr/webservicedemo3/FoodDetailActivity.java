package com.hanynemr.webservicedemo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends AppCompatActivity {

    TextView foodDescText;
    ImageView foodImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        foodDescText=findViewById(R.id.foodDescText);
        foodImg=findViewById(R.id.foodImg);
        foodDescText.setText(getIntent().getStringExtra("desc"));
        Picasso.get().load(getIntent().getStringExtra("img")).into(foodImg);
    }
}
