package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import afyapepe.mobile.adapter.SimpleSubAAdapter;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

public class SubYearAway extends AppCompatActivity {

    ListView TaskListView;
    FloatingActionButton fab;
    ProgressBar progressBar;
    private ProgressDialog pDialog;
    AlertDialog.Builder builder;
    private SQLiteHandler db;
    private SessionManager session;
    private List<Stock> subList = new ArrayList<>();
    SimpleSubAAdapter adapter;
    TextView displayTextViewTitle;


    List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub_year_away);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setIcon(R.drawable.ic_search_white_24dp);

        db = new SQLiteHandler(SubYearAway.this);

        session = new SessionManager(SubYearAway.this);

        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        View empty = findViewById(R.id.list_empty);
        TaskListView = (ListView) findViewById(R.id.listview11);
        // TaskListView.setVisibility((adapter.isEmpty())?View.GONE:View.VISIBLE);
        TaskListView.setEmptyView(empty);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        adapter = new SimpleSubAAdapter(SubYearAway.this, subList);


        TaskListView.setAdapter(adapter);
        // Showing progress dialog
        pDialog = new ProgressDialog(SubYearAway.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.subyearaway_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        pDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            /*for (int i = 0; i < request.length(); i++) {*/
                                Stock stock = new Stock();


                                stock.setDrugname(jsonObject.getString("drugname"));
                                stock.setName(jsonObject.getString("name"));
                              //  stock.setQuantity(jsonObject.getString("quantity"));
                                stock.setPharmacy(jsonObject.getString("pharmacy"));
                                stock.setSubdrugname(jsonObject.getString("subdrugname"));

                                subList.add(stock);
                           // }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SubYearAway.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter.notifyDataSetChanged();
                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
                        getTotalCount.setText(""+TaskListView.getCount());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(SubYearAway.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(SubYearAway.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
        int socketTimeout = 90000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(stringRequest);

//        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, App_Config.sub_test_year,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, response.toString());
//                        pDialog.dismiss();
//                        try {
//                            JSONArray request = new JSONArray(response);
//                            for (int i = 0; i < request.length(); i++) {
//                                Stock stock = new Stock();
//                                JSONObject jsonObject = null;
//                                jsonObject = request.getJSONObject(i);
//                                stock.setSubdrugname(jsonObject.getString("subdrugname"));
//
//                                subList.add(stock);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(SubYearAway.this, e.toString(), Toast.LENGTH_LONG).show();
//                        }
//
//                        adapter.notifyDataSetChanged();
//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+TaskListView.getCount());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (pDialog != null) {
//                    pDialog.dismiss();
//                    pDialog = null;
//                }
//                Toast.makeText(SubYearAway.this, error.toString(), Toast.LENGTH_LONG).show();
//                error.printStackTrace();
//            }
//        })
//
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                db = new SQLiteHandler(SubYearAway.this);
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
//        int socketTimeout2 = 90000; // 30 seconds. You can change it
//        RetryPolicy policy2 = new DefaultRetryPolicy(socketTimeout2,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        stringRequest2.setRetryPolicy(policy2);
//
//        AppController.getInstance().addToRequestQueue(stringRequest2);


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
            filteredstocklist = subList;
        }
        else {
            ArrayList<Stock> filteredList = new ArrayList<>();

            for (Stock stock : subList){

                if(stock.getDrugname().toLowerCase().contains(charString.toLowerCase())){
                    filteredList.add(stock);
                }
            }
            filteredstocklist = filteredList;
        }

        subList.clear();

        subList.addAll(filteredstocklist);

        adapter.notifyDataSetChanged();

        return filteredstocklist;
    }

    public void fab(View view){
        Intent intent5 = new Intent(getApplicationContext(), Manufacturers.class);
        startActivity(intent5);
    }
}


