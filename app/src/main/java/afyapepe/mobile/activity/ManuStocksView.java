package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import afyapepe.mobile.R;

/**
 * Created by Agallo on 18-Sep-17.
 */

public class ManuStocksView extends AppCompatActivity {

    ListView TaskListView;
    ProgressBar progressBar;
    private ProgressDialog pDialog;
    AlertDialog.Builder builder;
   // String HttpUrl = "https://seedorf.000webhostapp.com/mycollabo/amystocks.php";

    private static String url = "http://192.168.2.193/afyapepe3/public/showmanustock?email=manu1@afyapepe.com&id=9";
    List<String> IdList = new ArrayList<>();

    ArrayList<HashMap<String, String>> allstockslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manu_stocks_view);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TaskListView = (ListView)findViewById(R.id.manustocklistview);

        builder = new AlertDialog.Builder(ManuStocksView.this);
        allstockslist = new ArrayList<>();
       // lv = (ListView)findViewById(R.id.listview11);

        new GetHttpResponse().execute();

        //Adding ListView Item click Listener.
        TaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(ManuStocksView.this,ManuSingleStockView.class);

                // Sending ListView clicked value using intent.
                intent.putExtra("ListViewValue", IdList.get(position).toString());

                startActivity(intent);

                //Finishing current activity after open next activity.
                finish();

            }
        });
    }

    // JSON parse class started from here.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ManuStocksView.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray joblists = new JSONArray(jsonStr);


                    // Getting JSON Array node
                    //JSONArray joblists = jsonObj.getJSONArray("alljobsdetails");

                    // looping through All Contacts
                    for (int i = 0; i < joblists.length(); i++) {
                        JSONObject obj = joblists.getJSONObject(i);

                        String id = obj.getString("id");
                        String pharm = obj.getString("pharm");
                        String name = obj.getString("name");
                        String county = obj.getString("county");
                        String quantity = obj.getString("quantity");
                        String strength = obj.getString("strength");
                       // String strength_unit = obj.getString("strength_unit");
                        String drugname = obj.getString("drugname");


                        HashMap<String, String> live = new HashMap<>();


                        // live.put("dgno", String.valueOf(dgno));
                        // live.put("collection_id", String.valueOf(collection_id));
                        live.put("id", String.valueOf(id));
                        live.put("pharm", pharm);
                        live.put("name", name);
                        live.put("county", county);
                        live.put("quantity", quantity);
                        live.put("strength", strength);
                       // live.put("strength_unit",strength_unit);
                        live.put("drugname",drugname);

                        // adding contact to adverts list
                        allstockslist.add(live);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check Internet connection!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();
            //0739077968

            ListAdapter adapter = new SimpleAdapter(

                    ManuStocksView.this, allstockslist,

                    R.layout.list_stock, new String[]{"id","drugname","pharm", "name", "county", "quantity", "strength"},
                    new int[]{R.id.tvposition, R.id.tvid, R.id.tvname, R.id.tvname2, R.id.tvname3, R.id.tvpos, R.id.tvpos2});


            TaskListView.setAdapter(adapter);
            TaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
                    Intent breed = new Intent(ManuStocksView.this, ManuSingleStockView.class);
//TaskListView new String[]{"id","drugname","pharm", "name", "county", "quantity", "strength"},
                    String id = ((TextView) view.findViewById(R.id.tvposition)).getText().toString();
                    String drugname = ((TextView) view.findViewById(R.id.tvid)).getText().toString();
                    String pharm = ((TextView) view.findViewById(R.id.tvname)).getText().toString();
                    String name = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
                    String county = ((TextView) view.findViewById(R.id.tvname3)).getText().toString();
                    String quantity = ((TextView) view.findViewById(R.id.tvpos)).getText().toString();
//                    String strength = ((TextView) view.findViewById(R.id.tvpos2)).getText().toString();
                    //    String btname = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
//                    String livename = lvltypeguess.getText().toString();

                    breed.putExtra("id",id);
                    breed.putExtra("name", name);
                    breed.putExtra("county", county);
                    breed.putExtra("pharm", pharm);
                  //  breed.putExtra("strength", strength);
                    breed.putExtra("drugname", drugname);
                    breed.putExtra("quantity", quantity);

                    // breed.putExtra("email", email);

                    breed.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(breed);


                }
            });
        }
    }
}
