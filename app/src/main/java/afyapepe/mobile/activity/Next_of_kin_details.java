package afyapepe.mobile.activity;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.HttpGetHC4;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Next_of_kin_details extends Fragment {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private TextView age;
    private TextView agea;
    private TextView blood;
    private SQLiteHandler db;
    private TextView gender;
    private RecyclerView mRVFishPrice;
    private TextView name;
    private TextView pob;
    private SessionManager session;

    private class AsyncFetch extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        ProgressDialog pdLoading;
        URL url;

        private AsyncFetch() {
            this.pdLoading = new ProgressDialog(Next_of_kin_details.this.getContext());
            this.url = null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.pdLoading.setMessage("\tLoading");
            this.pdLoading.setCancelable(false);
            this.pdLoading.show();
        }

        protected String doInBackground(String... params) {
            try {
                this.url = new URL("http://afyapepe.com/afyapepe/nextofkin.php");
                try {
                    this.conn = (HttpURLConnection) this.url.openConnection();
                    this.conn.setReadTimeout(15000);
                    this.conn.setConnectTimeout(10000);
                    this.conn.setRequestMethod(HttpGetHC4.METHOD_NAME);
                    this.conn.setDoOutput(true);
                    String stringBuilder;
                    try {
                        if (this.conn.getResponseCode() == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
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
                    String fname = json_data.getString("firstname");
                    String sname = json_data.getString("secondName");
                    String ageee = json_data.getString("age");
                    String gendee = json_data.getString("gender");
                    if (gendee.equals("1")) {
                        gendee = "Male";
                    } else if (gendee.equals("2")) {
                        gendee = "Female";
                    }
                    String pobee = json_data.getString("pob");
                    String bloodee = json_data.getString("blood_type");
                    Next_of_kin_details.this.name.setText(": "+fname + " " + sname);
                    Next_of_kin_details.this.agea.setText(": "+ageee);
                    Next_of_kin_details.this.gender.setText(": "+gendee);
                    Next_of_kin_details.this.pob.setText(": "+pobee);
                    Next_of_kin_details.this.blood.setText(": "+bloodee);
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), "You dont have a next of kin", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.next_of_kin_details, container, false);
        this.agea = (TextView) rootView.findViewById(R.id.age);
        this.blood = (TextView) rootView.findViewById(R.id.blood);
        this.pob = (TextView) rootView.findViewById(R.id.placeof);
        this.gender = (TextView) rootView.findViewById(R.id.textView13);
        this.name = (TextView) rootView.findViewById(R.id.name);
        new AsyncFetch().execute(new String[0]);
        return rootView;
    }
}
