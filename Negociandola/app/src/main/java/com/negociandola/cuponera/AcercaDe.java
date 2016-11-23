package com.negociandola.cuponera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.negociandola.cuponera.firebase.FirebaseAnalyticsNegociandola;

public class AcercaDe extends AppCompatActivity {

    private ImageView facebook;
    private ImageView instagram;
    private ImageView twitter;
    private ImageView sitio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        facebook = (ImageView) findViewById(R.id.facebook);
        instagram = (ImageView) findViewById(R.id.instagram);
        twitter = (ImageView) findViewById(R.id.twitter);
        sitio = (ImageView) findViewById(R.id.sitio);

        facebook.setOnClickListener((v) -> showBrowser("https://www.facebook.com/negociandola"));
        instagram.setOnClickListener((v) -> showBrowser("https://www.instagram.com/negociandolamx"));
        twitter.setOnClickListener((v) -> showBrowser("https://twitter.com/negociandola"));
        sitio.setOnClickListener((v) -> showBrowser("http://www.negociandola.com"));
        FirebaseAnalyticsNegociandola.newInstance(this).screenEvent("Acerca de");

    }

    private void showBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
