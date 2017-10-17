package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ManuByRegion extends Fragment {


    AlertDialog.Builder builder;
    private ProgressDialog pDialog;
    private ListView lv;
    FloatingActionButton FAB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_manu_by_region, container, false);
        return rootView;

    }
    private static String url = "http://192.168.2.193/afyapepe3/public/showmanusales?email=manu1@afyapepe.com&id=9";

    ArrayList<HashMap<String, String>> allsaleslist;

    @Override
    public void onStart() {
        super.onStart();
        // initControls();
        // setContentView(R.layout.activity_sector_activity);

        builder = new AlertDialog.Builder(getActivity());
        allsaleslist = new ArrayList<>();
        lv = (ListView) getView().findViewById(R.id.manuregionlistview);


        new GetAllSales().execute();
    }

    private class GetAllSales extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
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
                        String FacilityName = obj.getString("FacilityName");
                        String name = obj.getString("name");
                        String drugname = obj.getString("drugname");
                        String pharmacy = obj.getString("pharmacy");
                        String county = obj.getString("county");

                        HashMap<String, String> live = new HashMap<>();


                        // live.put("dgno", String.valueOf(dgno));
                        // live.put("collection_id", String.valueOf(collection_id));
                        live.put("id", String.valueOf(id));
                        live.put("FacilityName", FacilityName);
                        live.put("name", name);
                        live.put("drugname",drugname);
                        live.put("pharmacy",pharmacy);
                        live.put("county",county);

                        // adding contact to adverts list
                        allsaleslist.add(live);
                    }
                } catch (final JSONException e) {
                    Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();


                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server. Check Internet connection!",
                                Toast.LENGTH_LONG)
                                .show();

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();
            //0739077968

            ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),

                     allsaleslist,

                    R.layout.list_book, new String[]{"id","county","name","FacilityName","pharmacy","drugname"},
                    new int[]{R.id.tvposition, R.id.tvid, R.id.tvname2, R.id.tvpos,R.id.tvname, R.id.tvname3});


            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent breed = new Intent(getActivity(), ManuShowSingleSale.class);

//                    String id = ((TextView) view.findViewById(R.id.tvposition)).getText().toString();
                    String FacilityName = ((TextView) view.findViewById(R.id.tvid)).getText().toString();
                    String name = ((TextView) view.findViewById(R.id.tvpos)).getText().toString();
                    String drugname = ((TextView) view.findViewById(R.id.tvname)).getText().toString();
                    String pharmacy = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
                    String county = ((TextView) view.findViewById(R.id.tvname3)).getText().toString();
                    //    String btname = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
//                    String livename = lvltypeguess.getText().toString();

                    breed.putExtra("id",id);
                    breed.putExtra("FacilityName", FacilityName);
                    breed.putExtra("name", name);
                    breed.putExtra("drugname", drugname);
                    breed.putExtra("pharmacy", pharmacy);
                    breed.putExtra("county", county);

                    // breed.putExtra("email", email);

                    breed.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(breed);


                }
            });
        }

    }

}
