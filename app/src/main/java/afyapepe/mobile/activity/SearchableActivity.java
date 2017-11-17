package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import afyapepe.mobile.R;
import afyapepe.mobile.adapter.SimpleStockAdapter;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

public class SearchableActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView lstView;

    private static String url = "http://192.168.2.188/afyapepe3/public/showmanustock?email=manu1@afyapepe.com&id=9";
    ProgressBar progressBar;
    private ProgressDialog pDialog;
    AlertDialog.Builder builder;
    private SQLiteHandler db;
    private SessionManager session;
    List<Stock> stocklist = new ArrayList<>();
    SimpleStockAdapter adapter;
    TextView displayTextViewTitle;

    String[] lstSource = {

            "Bungoma",
            "Bondo",
            "Kisumu",
            "Kakamega",
            "Malindi",
            "Mombasa",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Search");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        lstView = (ListView)findViewById(R.id.lstView);
        adapter = new SimpleStockAdapter(SearchableActivity.this, stocklist);
        lstView.setAdapter(adapter);

        pDialog = new ProgressDialog(SearchableActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
//        HashMap<String, String> user = db.getUserDetails();

        searchView = (MaterialSearchView)findViewById(R.id.search_view);

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                                stock.setQuantity(jsonObject.getString("quantity"));

                                //  stock.setRetryPolicy(new DefaultRetryPolicy(6000, 1, 1));


                                stocklist.add(stock);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SearchableActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Total number of Items are:" + lstView.getAdapter().getCount() , Toast.LENGTH_LONG).show();
                        //  displayData();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(SearchableActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(SearchableActivity.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
//        int socketTimeout = 30000;//30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        response.setRetryPolicy(policy);
//        stringRequest.add(request);
        AppController.getInstance().addToRequestQueue(stringRequest);

   // }


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                //If closed Search View , lstView will return default
                lstView = (ListView)findViewById(R.id.lstView);
                ArrayAdapter adapter = new ArrayAdapter(SearchableActivity.this,android.R.layout.simple_list_item_1,lstSource);
                lstView.setAdapter(adapter);

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for(String item:lstSource){
                        if(item.contains(newText))
                            lstFound.add(item);
                    }

                    ArrayAdapter adapter = new ArrayAdapter(SearchableActivity.this,android.R.layout.simple_list_item_1,lstFound);
                    lstView.setAdapter(adapter);
                }
                else{
                    //if search text is null
                    //return default
                    ArrayAdapter adapter = new ArrayAdapter(SearchableActivity.this,android.R.layout.simple_list_item_1,lstSource);
                    lstView.setAdapter(adapter);
                }
                return true;
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}
