package afyapepe.mobile.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
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
import afyapepe.mobile.activity.App_Config;
import afyapepe.mobile.activity.CountyOne;
import afyapepe.mobile.activity.Drug1;
import afyapepe.mobile.activity.Drug2;
import afyapepe.mobile.activity.Drug3;
import afyapepe.mobile.activity.Drug4;
import afyapepe.mobile.activity.Manufacturers;
import afyapepe.mobile.activity.Stock;
import afyapepe.mobile.adapter.SimpleSalesAdapter;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.R.id.fab;
import static afyapepe.mobile.app.AppController.TAG;

public class ManuByDrug extends AppCompatActivity {



    private List<Stock> salesList = new ArrayList<Stock>();
    private ListView listView;
    private SimpleSalesAdapter adapter;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;

    @Override
    public  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manubydrug);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        db = new SQLiteHandler(ManuByDrug.this);
//
//        session = new SessionManager(ManuByDrug.this);
//
//        //Fetching user details from SQLite
//        HashMap<String, String> user = db.getUserDetails();
//
//        String email = user.get("email");
//
//        View empty = findViewById(R.id.list_empty);
//        listView = (ListView) findViewById(R.id.listview11);
//        // TaskListView.setVisibility((adapter.isEmpty())?View.GONE:View.VISIBLE);
//        listView.setEmptyView(empty);
//        adapter = new SimpleSalesAdapter(ManuByDrug.this, salesList);
//        listView.setAdapter(adapter);
//
//        pDialog = new ProgressDialog(ManuByDrug.this);
//
//        pDialog.setMessage("Loading...");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.manubydrug_url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, response.toString());
//                        pDialog.dismiss();
//                        try {
//
//                            JSONArray request = new JSONArray(response);
//                            for (int i = 0; i < request.length(); i++) {
//
//                                Stock stock = new Stock();
//                                JSONObject jsonObject = null;
//                                jsonObject = request.getJSONObject(i);
//                                stock.setDrugname(jsonObject.getString("drugname"));
//                               // stock.setName(jsonObject.getString("name"));
//                                stock.setFacilityName(jsonObject.getString("FacilityName"));
//                                stock.setCounty(jsonObject.getString("county"));
//                                stock.setPharmacy(jsonObject.getString("pharmacy"));
//                                stock.setTotal(jsonObject.getString("total"));
//
//                                salesList.add(stock);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(ManuByDrug.this, e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//
//
////                        Notifies the attached observers that the underlying data has been changed
////                                * and any View reflecting the data set should refresh itself.
//                        adapter.notifyDataSetChanged();
//                       // Toast.makeText(ManuByDrug.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();
//
//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+listView.getCount());
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (pDialog != null) {
//                            pDialog.dismiss();
//                            pDialog = null;
//                        }
//                        Toast.makeText(ManuByDrug.this, error.toString(), Toast.LENGTH_LONG).show();
//                        error.printStackTrace();
//                    }
//                })
//
//
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                db = new SQLiteHandler(ManuByDrug.this);
//
//                // Fetching user details from SQLite
//                HashMap<String, String> user = db.getUserDetails();
//
//                String email = user.get("email");
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", email);
//
//                return params;
//            }
//        };
//
//        int socketTimeout = 90000; // 30 seconds. You can change it
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        stringRequest.setRetryPolicy(policy);
//        AppController.getInstance().addToRequestQueue(stringRequest);

    }
    //manudrugs
    public void card_view1(View v) {
        Intent intent1 = new Intent(getApplicationContext(), Drug1.class);
        startActivity(intent1);
    }
    public void card_view2(View v) {
        Intent intent1 = new Intent(getApplicationContext(), Drug2.class);
        startActivity(intent1);
    }
    public void card_view3(View v) {
        Intent intent1 = new Intent(getApplicationContext(), Drug3.class);
        startActivity(intent1);
    }
    public void card_view4(View v) {
        Intent intent1 = new Intent(getApplicationContext(), Drug4.class);
        startActivity(intent1);
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

                if(stock.getDrugname().toLowerCase().contains(charString.toLowerCase())){
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
    public void fab(View view){
        Intent intent5 = new Intent(getApplicationContext(), Manufacturers.class);
        startActivity(intent5);
    }
}
