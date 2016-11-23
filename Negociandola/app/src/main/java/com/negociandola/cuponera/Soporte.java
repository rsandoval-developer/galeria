package com.negociandola.cuponera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.negociandola.cuponera.firebase.FirebaseAnalyticsNegociandola;

public class Soporte extends AppCompatActivity {

    private LinearLayout btnBug;
    private LinearLayout btnFuncion;
    private  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        toolbar = (Toolbar)findViewById(R.id.miActionBar3);

        btnBug = (LinearLayout) findViewById(R.id.btnBug);
        btnFuncion = (LinearLayout) findViewById(R.id.btnFuncion);

        findViewById(R.id.btnAyuda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We normally won't show the welcome slider again in real app
                // but this is for testing
                PrefManager prefManager = new PrefManager(getApplicationContext());

                // make first time launch TRUE
                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(Soporte.this, WelcomeActivity.class));
                finish();
            }
        });

        btnFuncion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = "soporte@negociandola.com";
                String pedido = "Pedido de función";
                String nombreApp = "Negociandola";
                String versionApp = getResources().getString(R.string.version_app);
                String contexto = "Tus deseos son órdenes: ";


                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");

                email.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, pedido + " - " + nombreApp+" / " + versionApp );
                email.putExtra(Intent.EXTRA_TEXT, contexto );
                startActivity(Intent.createChooser(email, "Enviar correo :"));
            }
        });

        btnBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = "soporte@negociandola.com";
                String reportar = "Reportar un error";
                String nombreApp = "Negociandola";
                String versionApp = getResources().getString(R.string.version_app);
                String contexto = "Cúentanos lo sucedido: ";


                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");

                email.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, reportar + " - " + nombreApp+" / " + versionApp );
                email.putExtra(Intent.EXTRA_TEXT, contexto );
                startActivity(Intent.createChooser(email, "Enviar correo :"));
            }
        });
        setupToolbarConfig();

        FirebaseAnalyticsNegociandola.newInstance(this).screenEvent("Soporte");


    }

    protected void setupToolbarConfig() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }
}
