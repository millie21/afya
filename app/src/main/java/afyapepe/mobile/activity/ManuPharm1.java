package afyapepe.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.ManuByPharmacy;
import afyapepe.mobile.fragment.ManuByPharmacy50;

public class ManuPharm1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_pharm1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void pharm1(View view)
    {
        Intent joboneIntent = new Intent(ManuPharm1.this, ManuByPharmacy.class);
        startActivity(joboneIntent);

    }

    public void pharm50(View view)
    {
        Intent joboneIntent = new Intent(ManuPharm1.this, ManuByPharmacy50.class);
        startActivity(joboneIntent);

    }

}
