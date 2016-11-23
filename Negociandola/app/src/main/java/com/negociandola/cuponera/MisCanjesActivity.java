package com.negociandola.cuponera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.negociandola.cuponera.adapter.MisCanjesAdapter;
import com.negociandola.cuponera.db.realm.MisCanjes;
import com.negociandola.cuponera.db.realm.RealmDataService;
import com.negociandola.cuponera.firebase.FirebaseAnalyticsNegociandola;

import java.util.ArrayList;
import java.util.List;

public class MisCanjesActivity extends AppCompatActivity {

    private RecyclerView misCanjesRecyclerView;
    private MisCanjesAdapter adapter;
    private List<MisCanjes> misCanjes = new ArrayList<>();
    private TextView textMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_canjes);
        misCanjes = RealmDataService.newInstance().getMisCanjes();
        adapter = new MisCanjesAdapter(misCanjes, this);
        textMensaje = (TextView) findViewById(R.id.text_mensaje);
        misCanjesRecyclerView = (RecyclerView) findViewById(R.id.mis_canjes);
        misCanjesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        misCanjesRecyclerView.setAdapter(adapter);
        textMensaje.setVisibility(misCanjes.size() == 0 ? View.VISIBLE : View.GONE);

        FirebaseAnalyticsNegociandola.newInstance(this).screenEvent("Mis Canjes");


    }
}
