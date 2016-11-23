package com.negociandola.cuponera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.negociandola.cuponera.db.realm.RealmDataService;
import com.negociandola.cuponera.firebase.FirebaseAnalyticsNegociandola;

public class Splash extends AppCompatActivity {

    private ImageView logo;
    private RealmDataService realmDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = (ImageView) findViewById(R.id.logo);
        realmDataService = new RealmDataService();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, WelcomeActivity.class));
                finish();
            }
        }, 3000);
        showLoading();
        FirebaseAnalyticsNegociandola.newInstance(this).screenEvent("Splash");
    }


    public void showLoading() {
        Animation pulse = AnimationUtils.loadAnimation(Splash.this, R.anim.pulse);
        logo.startAnimation(pulse);
    }


}
