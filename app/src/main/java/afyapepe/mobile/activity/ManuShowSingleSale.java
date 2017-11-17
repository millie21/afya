package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import afyapepe.mobile.R;

public class ManuShowSingleSale extends AppCompatActivity {


  //  TextView tvchoicename,tvlivebreed,tvlocation,tvphoneno,tvprice,tvchoicedesc;
    EditText searchitem;
    Button se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_show_single_sale);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.4),(int)(height*.2));


        searchitem = (EditText) findViewById(R.id.tvposition2);
      //  se = (Button) findViewById(R.id.s1);


    }
}
