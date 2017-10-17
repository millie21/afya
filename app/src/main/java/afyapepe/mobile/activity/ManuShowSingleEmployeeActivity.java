package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import afyapepe.mobile.R;

public class ManuShowSingleEmployeeActivity extends AppCompatActivity {


    TextView tvchoicename,tvlivebreed,tvlocation,tvphoneno,tvprice,tvchoicedesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_show_single_employee);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));

        tvchoicename = (TextView) findViewById(R.id.tvposition);
        tvlivebreed = (TextView) findViewById(R.id.tvid);
        tvlocation = (TextView) findViewById(R.id.tvname);
        tvphoneno = (TextView) findViewById(R.id.tvname2);
        tvprice = (TextView) findViewById(R.id.tvname3);
        tvchoicedesc = (TextView) findViewById(R.id.tvpos);

//        String county = ((TextView) view.findViewById(R.id.tvname)).getText().toString();
//        String phonenumber = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
//        String email = ((TextView) view.findViewById(R.id.tvname3)).getText().toString();

        Bundle bundle = getIntent().getExtras();
        String livename = bundle.getString("id");
        String breedname = bundle.getString("name");
        String firstname = bundle.getString("role");
        String location = bundle.getString("email");
        String phoneno = bundle.getString("job");
        String pricing = bundle.getString("region");
//        String datesubmitted = bundle.getString("datesubmitted");
//        String description = bundle.getString("description");

        tvchoicename.setText(livename);
        tvlivebreed.setText(breedname);
        tvchoicedesc.setText(firstname);
        tvlocation.setText( location);
        tvphoneno.setText(  phoneno);
        tvprice.setText( pricing);

    }
}
