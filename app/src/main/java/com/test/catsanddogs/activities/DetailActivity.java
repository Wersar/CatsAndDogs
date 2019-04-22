package com.test.catsanddogs.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.catsanddogs.R;
import com.test.catsanddogs.data.Pet;
import com.test.catsanddogs.util.DeviceUtils;

public class DetailActivity extends AppCompatActivity {
    public static final String PET_EXTRA = "pet_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Pet pet = intent.getParcelableExtra(PET_EXTRA);

        int width = new DeviceUtils(getApplication()).getScreenWidth();

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.get().load(pet.getUrl()).resize(width, 0).into(imageView);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(pet.getTitle());
    }
}
