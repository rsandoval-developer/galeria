package tec.com.galeria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GaleriaActivity extends AppCompatActivity {

    private RecyclerView mGaleria;
    private GaleriaAdapter mAdapter;
    private List<GaleriaModel.Imagen> galeria = new ArrayList<>();
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mGaleria = (RecyclerView) findViewById(R.id.galeria);
        mAdapter = new GaleriaAdapter(this, galeria);
        mGaleria.setLayoutManager(new GridLayoutManager(this, 3));
        mGaleria.setAdapter(mAdapter);

        getGaleria();
    }

    private void getGaleria(){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GaleriaModel galeriaModel = dataSnapshot.getValue(GaleriaModel.class);
                galeria.clear();
                galeria.addAll(galeriaModel.getGaleria());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
