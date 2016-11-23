package com.negociandola.cuponera;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.negociandola.cuponera.adapter.PageAdapter;
import com.negociandola.cuponera.db.Categoria;
import com.negociandola.cuponera.db.Categorias;
import com.negociandola.cuponera.db.Cupon;
import com.negociandola.cuponera.db.realm.CategoriaRealm;
import com.negociandola.cuponera.db.realm.CuponRealm;
import com.negociandola.cuponera.db.realm.RealmDataService;
import com.negociandola.cuponera.firebase.FirebaseAnalyticsNegociandola;
import com.negociandola.cuponera.fragment.CategoriaFragment;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class PantallaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RealmDataService realmDataService;
    private List<Categoria> categorias;
    private DatabaseReference databaseReference;
    private ContentLoadingProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        loading =(ContentLoadingProgressBar)findViewById(R.id.loading);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        realmDataService = new RealmDataService();
        databaseReference = ((Application)this.getApplicationContext()).getDatabaseReference();
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        toolbar.setNavigationIcon(R.drawable.ic_dehaze_white_24dp);
        getCategoriasFirebase();
        FirebaseAnalyticsNegociandola.newInstance(this).screenEvent("Pantalla Principal");


    }

    private void getCategoriasFirebase(){
        loading.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.GONE);
        if(Utils.isNetworkAvailableCompat(this)){
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    loading.setVisibility(View.GONE);
                    viewPager.setVisibility(View.VISIBLE);
                    categorias = dataSnapshot.getValue(Categorias.class).getCategorias();
                    setupViewPager();
                    tabLayout.setupWithViewPager(viewPager);
                    RealmDataService.newInstance().saveCategorias(categorias);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            loading.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
            categorias = getCategorias(RealmDataService.newInstance().getCategorias().getCategorias());
            setupViewPager();
            tabLayout.setupWithViewPager(viewPager);
        }

    }


    private List<Categoria> getCategorias(RealmList<CategoriaRealm> categoriaRealms){
        List<Categoria> categoriaList = new ArrayList<>();
        for (CategoriaRealm categoriaRealm : categoriaRealms){
            Categoria categoria = new Categoria();
            categoria.setId(categoriaRealm.getId());
            categoria.setNombreCategoria(categoriaRealm.getNombreCategoria());
            List<Cupon> cupons = new ArrayList<>();
            for (CuponRealm cuponRealm : categoriaRealm.getCupones()){
                Cupon cupon = new Cupon();
                cupon.setId(cuponRealm.getId());
                cupon.setTituloCupon(cuponRealm.getTituloCupon());
                cupon.setImagenCupon(cuponRealm.getImagenCupon());
                cupon.setNombreNegocio(cuponRealm.getNombreNegocio());
                cupon.setVencimiento(cuponRealm.getVencimiento());
                cupon.setFolio(cuponRealm.getFolio());
                cupon.setRestricciones(cuponRealm.getRestricciones());
                cupon.setDescripcionNegocio(cuponRealm.getDescripcionNegocio());
                cupon.setHorarioNegocio(cuponRealm.getHorarioNegocio());
                cupon.setDireccionNegocio(cuponRealm.getDireccionNegocio());
                cupon.setTelefonoNegocio(cuponRealm.getTelefonoNegocio());
                cupon.setCanjeado(cuponRealm.isCanjeado());
                cupon.setAprobado(cuponRealm.isAprobado());
                cupons.add(cupon);
            }
            categoria.setCupones(cupons);
            categoriaList.add(categoria);
        }
        return categoriaList;
    }

    private void setupViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        Gson gson = new Gson();
        List<String> titlesList = new ArrayList<>();
        for (int x = 0; x < categorias.size(); x++) {
            titlesList.add(categorias.get(x).getNombreCategoria());
            String gsonCupones = gson.toJson(categorias.get(x));
            fragments.add(CategoriaFragment.newInstance(gsonCupones));

        }
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), titlesList, fragments));

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_canjes) {
            Intent misCanjes = new Intent(this, MisCanjesActivity.class);
            startActivity(misCanjes);
        } else if (id == R.id.nav_compartir_app) {
            String share = "Te invito a usar esta app: https://play.google.com/store/apps/details?id=com.negociandola.cuponera , está de lujo";
            String nombreApp = "Negociandola";

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, nombreApp );
            sendIntent.putExtra(Intent.EXTRA_TEXT, share);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else if (id == R.id.nav_calificanos) {
            String url = "market://details?id=com.negociandola.cuponera";
            Intent rate = new Intent(Intent.ACTION_VIEW);
            rate.setData(Uri.parse(url));
            startActivity(rate);
        }  else if (id == R.id.nav_soporte) {
            Intent soporte = new Intent(this,Soporte.class);
            startActivity(soporte);
        } else if (id == R.id.nav_acerca_de) {
            Intent acerca = new Intent(this,AcercaDe.class);
            startActivity(acerca);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
