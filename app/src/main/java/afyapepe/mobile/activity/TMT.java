package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import afyapepe.mobile.R;
import afyapepe.mobile.adapter.SalesAdapterRTotal;
import afyapepe.mobile.adapter.SimpleSalesAdapterR;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.fragment.ManuByRegion;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

/**
 * Created by Millie Agallo on 1/24/2018.
 */

public class TMT extends AppCompatActivity {

    private List<Stock> totalsaleslist = new ArrayList<Stock>();
    private SalesAdapterRTotal adapterRTotal;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tmt);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new SQLiteHandler(TMT.this);

        session = new SessionManager(TMT.this);


        //Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        View empty = findViewById(R.id.list_empty);
        listView = (ListView) findViewById(R.id.listview11);
        listView.setEmptyView(empty);
       // adapter = new SimpleSalesAdapterR(ManuByRegion.this, salesListr);

        adapterRTotal = new SalesAdapterRTotal(TMT.this, totalsaleslist);

        listView.setAdapter(adapterRTotal);
        //  public void fetchTotalIncome()throws JSONException {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, App_Config.testtotal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());

                        try {
                            JSONArray request = new JSONArray(response);
                            for (int i = 0; i < request.length(); i++) {

                                Stock stock = new Stock();
                                JSONObject jsonObject = null;
                                jsonObject = request.getJSONObject(i);
                                stock.setTotalIncome(jsonObject.getString("TotalIncome"));

                                // String totalIncome = test.getString("TotalIncome");
                                totalsaleslist.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TMT.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        adapterRTotal.notifyDataSetChanged();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(TMT.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(TMT.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringrequest);
    }


}
