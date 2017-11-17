package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import afyapepe.mobile.adapter.SimpleSalesAdapterCountyInfo;
import afyapepe.mobile.adapter.SimpleSalesAdapterR;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.fragment.ManuByRegion;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

public class ManuCountyInfo extends AppCompatActivity {

    private static String url = "http://192.168.2.196/afyapepe3/public/showmanusales?email=manu1@afyapepe.com&id=9";

    private List<Stock> salesListr = new ArrayList<Stock>();
    private ListView listView;
    private SimpleSalesAdapterCountyInfo adapter;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manu_county_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new SQLiteHandler(ManuCountyInfo.this);

        session = new SessionManager(ManuCountyInfo.this);



        //Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        listView = (ListView) findViewById(R.id.listview11);
        adapter = new SimpleSalesAdapterCountyInfo(ManuCountyInfo.this, salesListr);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(ManuCountyInfo.this);

        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        pDialog.dismiss();
                        try {

                            JSONArray request = new JSONArray(response);
                            for (int i = 0; i < request.length(); i++) {

                                Stock stock = new Stock();
                                JSONObject jsonObject = null;
                                jsonObject = request.getJSONObject(i);
                                stock.setDrugname(jsonObject.getString("drugname"));
                                stock.setName(jsonObject.getString("name"));
                                stock.setFacilityName(jsonObject.getString("FacilityName"));
                                stock.setCounty(jsonObject.getString("county"));
                                stock.setPharmacy(jsonObject.getString("pharmacy"));
                                stock.setTotal(jsonObject.getString("total"));

                                salesListr.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCountyInfo.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
                        getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuCountyInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCountyInfo.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(search);
        search(searchView);


        return true;
    }

    private void search(SearchView searchView){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filteredDrugs(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                if(newText.isEmpty()){
//
//                }
                return false;
            }
        });
    }
    public List<Stock> filteredDrugs(CharSequence charSequence){
        List<Stock> filteredstocklist;
        String charString = charSequence.toString();

        if (charString.isEmpty()){
            filteredstocklist = salesListr;
        }
        else {
            ArrayList<Stock> filteredList = new ArrayList<>();

            for (Stock stock : salesListr){

                if(stock.getCounty().toLowerCase().contains(charString.toLowerCase())){
                    filteredList.add(stock);
                }
            }
            filteredstocklist = filteredList;
        }

        salesListr.clear();

        salesListr.addAll(filteredstocklist);

        adapter.notifyDataSetChanged();

        return filteredstocklist;
    }
}
