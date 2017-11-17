package afyapepe.mobile.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import afyapepe.mobile.activity.Manufacturers;
import afyapepe.mobile.activity.Stock;
import afyapepe.mobile.adapter.SimpleSalesAdapter;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

/**
 * Created by Millie Agallo on 11/14/2017.
 */

public class ManuByPharmacy extends AppCompatActivity {

    private static String url = "http://192.168.2.196/afyapepe3/public/showmanusales?email=manu1@afyapepe.com&id=9";

    private List<Stock> salesList = new ArrayList<Stock>();
    private ListView listView;
    private SimpleSalesAdapter adapter;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;

    @Override
    public  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manu_by_drug);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new SQLiteHandler(ManuByPharmacy.this);

        session = new SessionManager(ManuByPharmacy.this);

//        FloatingActionButton m = (FloatingActionButton) findViewById(R.id.fab);
//        m.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(ManuByPharmacy.this, Manufacturers.class);
//                startActivity(intent);
//
//            }
//
//        });

        //Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        listView = (ListView) findViewById(R.id.listview11);
        adapter = new SimpleSalesAdapter(ManuByPharmacy.this, salesList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(ManuByPharmacy.this);

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
                                // stock.setName(jsonObject.getString("name"));
                                stock.setFacilityName(jsonObject.getString("FacilityName"));
                                stock.setCounty(jsonObject.getString("county"));
                                stock.setPharmacy(jsonObject.getString("pharmacy"));
                                stock.setTotal(jsonObject.getString("total"));

                                salesList.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuByPharmacy.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }


//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                    //    Toast.makeText(ManuByPharmacy.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();
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
                        Toast.makeText(ManuByPharmacy.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })


        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByPharmacy.this);

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
            filteredstocklist = salesList;
        }
        else {
            ArrayList<Stock> filteredList = new ArrayList<>();

            for (Stock stock : salesList){

                if(stock.getPharmacy().toLowerCase().contains(charString.toLowerCase())){
                    filteredList.add(stock);
                }
            }
            filteredstocklist = filteredList;
        }

        salesList.clear();

        salesList.addAll(filteredstocklist);

        adapter.notifyDataSetChanged();

        return filteredstocklist;
    }
}
