package afyapepe.mobile.activity;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import afyapepe.mobile.R;

public class ManuDSubTo extends Fragment {


    AlertDialog.Builder builder;
    private ProgressDialog pDialog;
    private ListView lv;
    FloatingActionButton FAB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_manu_dsub_to, container, false);
        return rootView;

    }
    private static String url = "http://192.168.2.193/afyapepe3/public/showmanudrugsubstitutionsaway?email=manu1@afyapepe.com&id=9";

    ArrayList<HashMap<String, String>> alldrugsubinlist;


    @Override
    public void onStart() {
        super.onStart();

        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        builder = new AlertDialog.Builder(getActivity());
        alldrugsubinlist = new ArrayList<>();
        lv = (ListView) getView().findViewById(R.id.listview11);


        new GetAllJobs().execute();
    }

    private class GetAllJobs extends AsyncTask<Void, Void, Void> {

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
                        String name = obj.getString("name");
                        String FacilityName = obj.getString("FacilityName");
                        String drugname = obj.getString("drugname");
                        String substitution_reason = obj.getString("substitution_reason");
                        String quantity = obj.getString("quantity");
                        String price = obj.getString("price");
                        // String job = obj.getString("total");
                        String total = obj.getString("total");


                        HashMap<String, String> live = new HashMap<>();


                        // live.put("dgno", String.valueOf(dgno));
                        // live.put("collection_id", String.valueOf(collection_id));
                        live.put("id", String.valueOf(id));
                        live.put("name", name);
                        live.put("FacilityName", FacilityName);
                        live.put("drugname",drugname);
                        live.put("substitution_reason",substitution_reason);
                        live.put("quantity",quantity);
                        live.put("price",price);
                        live.put("total",total);

                        // adding contact to adverts list
                        alldrugsubinlist.add(live);
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

                     alldrugsubinlist,
//                    live.put("id", String.valueOf(id));
//            live.put("name", name);
//            live.put("FacilityName", FacilityName);
//            live.put("drugname",drugname);
//            live.put("substitution_reason",substitution_reason);
//            live.put("quantity",quantity);
//            live.put("price",price);
//            live.put("total",total);

                    R.layout.list_manudrug, new String[]{"id","drugname","name","FacilityName","quantity","price","total","reason","substitution_reason"},
                    new int[]{R.id.tvposition, R.id.tvid, R.id.tvname2, R.id.tvpos,R.id.tvname, R.id.tvname3, R.id.tvpos2, R.id.tvpos3, R.id.tvpos4});


            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent breed = new Intent(getActivity(),ManuSingleDrugDist.class);
                    //  "id","drugname","name","FacilityName","quantity","price","total","reason","substitution_reason"},

                    //   String id = ((TextView) view.findViewById(R.id.tvposition)).getText().toString();
                    String drugname = ((TextView) view.findViewById(R.id.tvid)).getText().toString();
                    String name = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
                    String FacilityName = ((TextView) view.findViewById(R.id.tvpos)).getText().toString();
                    String quantity = ((TextView) view.findViewById(R.id.tvname)).getText().toString();
                    String price = ((TextView) view.findViewById(R.id.tvname3)).getText().toString();
                    String total = ((TextView) view.findViewById(R.id.tvpos2)).getText().toString();
                    String reason = ((TextView) view.findViewById(R.id.tvpos3)).getText().toString();
                    String substitution_reason = ((TextView) view.findViewById(R.id.tvpos4)).getText().toString();
                    //    String btname = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
//                    String livename = lvltypeguess.getText().toString();

                    breed.putExtra("id",id);
                    breed.putExtra("drugname", drugname);
                    breed.putExtra("name", name);
                    breed.putExtra("FacilityName", FacilityName);
                    breed.putExtra("quantity", quantity);
                    breed.putExtra("price", price);
                    breed.putExtra("total", total);
                    breed.putExtra("reason", reason);
                    breed.putExtra("substitution_reason", substitution_reason);

                    // breed.putExtra("email", email);

                    breed.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(breed);


                }
            });
        }

    }

}
