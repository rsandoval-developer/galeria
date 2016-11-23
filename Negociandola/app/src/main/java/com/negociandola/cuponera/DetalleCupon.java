package com.negociandola.cuponera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.negociandola.cuponera.db.realm.MisCanjes;
import com.negociandola.cuponera.db.realm.RealmDataService;
import com.negociandola.cuponera.firebase.FirebaseAnalyticsNegociandola;

public class DetalleCupon extends AppCompatActivity {

    private TextView tvPromocion;
    private ImageView imgImagenCupon;
    private TextView tvValido;
    private TextView tvFolio;
    private TextView tvRestricciones;
    private TextView tvNombreNegocio;
    private TextView tvDescripcionNegocio;
    private TextView tvHorario;
    private TextView tvDireccion;
    private TextView tvTelefono;
    private TextView btnCanjear;
    private LinearLayout contentFolio;
    private LinearLayout btnAprobar;
    private LinearLayout content_aprobado;
    private LinearLayout barCanje;
    private Toolbar mToolbar;
    private String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cupon);
        //database = new Database(this);
        mToolbar = (Toolbar) findViewById(R.id.miActionBar4);
        Bundle parametros = getIntent().getExtras();
        String titulo_promo = parametros.getString("titulo_cupon");
        String imagen_cupon = parametros.getString("imagen_cupon");
        String vencimiento = parametros.getString("vecimiento");
        final String folio = parametros.getString("folio");
        String restriccion = parametros.getString("restriccion");
        final String nombre_negocio = parametros.getString("nombre_negocio");
        String descripcion_negocio = parametros.getString("descripcion_negocio");
        String horario_negocio = parametros.getString("horario_negocio");
        final String direccion_negocio = parametros.getString("direccion_negocio");
        String telefono_negocio = parametros.getString("telefono_negocio");
        boolean isCajeado = parametros.getBoolean("is_canjeado");
        boolean isAprobado = parametros.getBoolean("is_aprobado");
        final String id = parametros.getString("id");

        tvPromocion = (TextView) findViewById(R.id.tvPromocion);
        imgImagenCupon = (ImageView) findViewById(R.id.imgImagenCupon);
        tvValido = (TextView) findViewById(R.id.tvValido);
        tvFolio = (TextView) findViewById(R.id.tvFolio);
        tvRestricciones = (TextView) findViewById(R.id.tvRestricciones);
        tvNombreNegocio = (TextView) findViewById(R.id.tvNombreNegocio);
        tvDescripcionNegocio = (TextView) findViewById(R.id.tvDescripcionNegocio);
        tvHorario = (TextView) findViewById(R.id.tvHorario);
        tvDireccion = (TextView) findViewById(R.id.tvDireccion);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        btnCanjear = (TextView) findViewById(R.id.canjear);
        contentFolio = (LinearLayout) findViewById(R.id.content_folio);
        btnAprobar = (LinearLayout) findViewById(R.id.btnAprobar);
        content_aprobado = (LinearLayout) findViewById(R.id.content_aprobado);
        barCanje = (LinearLayout) findViewById(R.id.barCanje);

        tvPromocion.setText(titulo_promo);

        Glide.with(this)
                .load(imagen_cupon)
                .centerCrop()
                .into(imgImagenCupon);

        tvValido.setText(vencimiento);
        tvFolio.setText(folio + Utils.generateSessionId());
        tvRestricciones.setText(restriccion);
        tvNombreNegocio.setText(nombre_negocio);
        tvDescripcionNegocio.setText(descripcion_negocio);
        tvHorario.setText(horario_negocio);
        tvDireccion.setText(direccion_negocio);
        tvTelefono.setText(telefono_negocio);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.setDuration(600);
            getWindow().setEnterTransition(slide);

            getWindow().setReturnTransition(new Fade());
        } else {

        }
        MisCanjes misCanjes = RealmDataService.newInstance().getMiCanje(folio);
        if (folio.equals(misCanjes.getFolio())) {
            isCajeado = true;
            isAprobado = misCanjes.isAprobado();
        }


        if (isAprobado) {
            btnCanjear.setVisibility(View.GONE);
            contentFolio.setVisibility(View.GONE);
            content_aprobado.setVisibility(View.VISIBLE);
            barCanje.setBackgroundColor(Color.parseColor("#009688"));
            btnAprobar.setVisibility(View.GONE);
        } else {
            if (isCajeado) {
                tvFolio.setText(misCanjes.getCupon());
                btnCanjear.setVisibility(View.GONE);
                contentFolio.setVisibility(View.VISIBLE);
                btnAprobar.setVisibility(View.VISIBLE);
            }
        }

        if (isCajeado) {
            tvFolio.setText(misCanjes.getCupon());
            btnCanjear.setVisibility(View.GONE);
            if (!isAprobado) {
                contentFolio.setVisibility(View.VISIBLE);
                btnAprobar.setVisibility(View.VISIBLE);
            }
        } else {
            btnAprobar.setVisibility(View.GONE);
            btnCanjear.setVisibility(View.VISIBLE);
            contentFolio.setVisibility(View.GONE);
            btnAprobar.setVisibility(View.GONE);
        }

        btnAprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentFolio.setVisibility(View.GONE);
                btnAprobar.setVisibility(View.GONE);
                content_aprobado.setVisibility(View.VISIBLE);
                barCanje.setBackgroundColor(Color.parseColor("#009688"));
                MisCanjes misCanjes = new MisCanjes();
                misCanjes.setCupon(tvFolio.getText().toString());
                misCanjes.setFechaCanje(Utils.getDate());
                misCanjes.setFolio(folio);
                misCanjes.setTituloCupon(titulo_promo);
                misCanjes.setImagenCupon(imagen_cupon);
                misCanjes.setVencimiento(vencimiento);
                misCanjes.setRestricciones(restriccion);
                misCanjes.setNombreNegocio(nombre_negocio);
                misCanjes.setDescripcionNegocio(descripcion_negocio);
                misCanjes.setHorarioNegocio(horario_negocio);
                misCanjes.setDireccionNegocio(direccion_negocio);
                misCanjes.setTelefonoNegocio(telefono_negocio);
                misCanjes.setAprobado(true);
                RealmDataService.newInstance().saveMisCanjes(misCanjes);
                FirebaseAnalyticsNegociandola.newInstance(DetalleCupon.this).trackEvent(id, "Aprobado");

            }
        });


        btnCanjear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCanjear.setVisibility(View.GONE);
                contentFolio.setVisibility(View.VISIBLE);
                btnAprobar.setVisibility(View.VISIBLE);
                MisCanjes misCanjes = new MisCanjes();
                misCanjes.setCupon(tvFolio.getText().toString());
                misCanjes.setFechaCanje(Utils.getDate());
                misCanjes.setFolio(folio);
                misCanjes.setTituloCupon(titulo_promo);
                misCanjes.setImagenCupon(imagen_cupon);
                misCanjes.setVencimiento(vencimiento);
                misCanjes.setRestricciones(restriccion);
                misCanjes.setNombreNegocio(nombre_negocio);
                misCanjes.setDescripcionNegocio(descripcion_negocio);
                misCanjes.setHorarioNegocio(horario_negocio);
                misCanjes.setDireccionNegocio(direccion_negocio);
                misCanjes.setTelefonoNegocio(telefono_negocio);
                RealmDataService.newInstance().saveMisCanjes(misCanjes);
                FirebaseAnalyticsNegociandola.newInstance(DetalleCupon.this).trackEvent(id, "Canjeado");
            }
        });

        tvTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = tvTelefono.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);

            }
        });

        tvDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + direccion_negocio);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        setupToolbarConfig();
        FirebaseAnalyticsNegociandola.newInstance(this).screenEvent("Detalle Cupon");


    }

    protected void setupToolbarConfig() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.left);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.compartir:
                String share = "Te invito a usar esta app: https://play.google.com/store/apps/details?id=com.negociandola.cuponera , está de lujo";
                String nombreApp = "Negociandola";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, nombreApp);
                sendIntent.putExtra(Intent.EXTRA_TEXT, share);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
