package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.methods.HttpGetHC4;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

public class History extends Fragment {

  /*  private SQLiteHandler db;
    private SessionManager session;


    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;

    private TextView agea;
    private TextView blood;
    private TextView age;
    private TextView pob;
    private TextView gender;
    private TextView name;
*/





  /*  private SQLiteHandler db;
    private SessionManager session;


    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;

    private TextView agea;
    private TextView blood;
    private TextView age;
    private TextView pob;
    private TextView gender;
    private TextView name;
*/

    private TextView name;




    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private TextView age;

    private SQLiteHandler db;

    private RecyclerView mRVFishPrice;
    private SessionManager session;

    private class AsyncFetch extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        ProgressDialog pdLoading;
        URL url;


        private AsyncFetch() {
            this.pdLoading = new ProgressDialog(History.this.getContext());
            this.url = null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.pdLoading.setMessage("\tLoading...");
            this.pdLoading.setCancelable(false);
            this.pdLoading.show();
        }

        protected String doInBackground(String... params) {
            try {
                this.url = new URL("http://afyapepe.com/afyapepe/history.php");
                try {
                    this.conn = (HttpURLConnection) this.url.openConnection();
                    this.conn.setReadTimeout(15000);
                    this.conn.setConnectTimeout(10000);
                    this.conn.setRequestMethod(HttpGetHC4.METHOD_NAME);
                    this.conn.setDoOutput(true);
                    String stringBuilder;
                    try {
                        if (this.conn.getResponseCode() == ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
                            StringBuilder result = new StringBuilder();
                            while (true) {
                                String line = reader.readLine();
                                if (line == null) {
                                    break;
                                }
                                result.append(line);
                            }
                            stringBuilder = result.toString();
                            return stringBuilder;
                        }
                        stringBuilder = "unsuccessful";
                        this.conn.disconnect();
                        return stringBuilder;
                    } catch (IOException e) {
                        e.printStackTrace();
                        stringBuilder = e.toString();
                    } finally {
                        this.conn.disconnect();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return e1.toString();
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                return e2.toString();
            }
            return "";
        }

        protected void onPostExecute(String result) {
            this.pdLoading.dismiss();
            List<DataFish> data = new ArrayList();
            this.pdLoading.dismiss();
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    String fname = json_data.getString("created_at");
                    String sname = json_data.getString("current_weight");
                    String agee = json_data.getString("current_height");
                    String gendee = json_data.getString("temperature");
                    String systofoop = json_data.getString("systolic_bp");
                    String dystofoop = json_data.getString("dystolic_bp");
                    String complia = json_data.getString("chief_compliant");

                    //String pobee = json_data.getString("pob");
                    //String bloodee = json_data.getString("blood_type");
                    History.this.name.setText("Date : "+fname + "\n "+"\n"+"\n" +"Weight : "+ sname+ "\n "+"\n"+"\n" +"Height : "+agee+ "\n "+"\n"+"\n" +"Temperature : "+gendee+ "\n "+"\n"+"\n"+"Systolic BP : "+systofoop+ "\n "+"\n"+"\n" +"Dystolic BP : "+dystofoop+"\n"+"\n"+"\n" +"Chief Complaint : "+complia+"\n"+"\n"+"\n"+"\n" );

                    /*
                    VaccinationList.this.agea.setText(": "+ageee);
                    VaccinationList.this.gender.setText(": "+gendee);
                    VaccinationList.this.pob.setText(": "+pobee);
                    VaccinationList.this.blood.setText(": "+bloodee);

                    */

                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), "Serious Problem Occured retrieving history", Toast.LENGTH_SHORT).show();
            }
        }
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_history, container, false);

       // name=(TextView) rootView.findViewById(R.id.history);

/*
        agea=(TextView) rootView.findViewById(R.id.age);
        blood=(TextView) rootView.findViewById(R.id.blood);
        pob=(TextView) rootView.findViewById(R.id.placeof);
        gender=(TextView) rootView.findViewById(R.id.textView13);
        name=(TextView) rootView.findViewById(R.id.name);

*/


        //new History().AsyncFetch().execute();




        return rootView;


    }


}