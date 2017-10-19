package afyapepe.mobile.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.R.attr.type;

/**
 * Created by Millie Agallo on 9/26/2017.
 */

public class MAnuEAddBackground extends AsyncTask<String,Void,String> {

    Context context;

    MAnuEAddBackground(FragmentActivity ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String jobadd_url = "http://192.168.2.191/afyapepe3/public/insertmanuemployees?email=manu1@afyapepe.com";

        String function = params[0];

        if(function.equals("addemployee")){

            // String job_id = params[1];
            String name = params[1];
            String email = params[2];
            String role = params[3];
            String password = params[4];
            String job = params[5];
            String region = params[6];
           // String email = params[7];

            try {
                URL url = new URL(jobadd_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("name","UTF-8") +"="+ URLEncoder.encode(name,"UTF-8")+"&"+
                                URLEncoder.encode("job","UTF-8") +"="+ URLEncoder.encode(job,"UTF-8")+"&"+
                                URLEncoder.encode("role","UTF-8") +"="+ URLEncoder.encode(role,"UTF-8")+"&"+
                                URLEncoder.encode("region","UTF-8") +"="+ URLEncoder.encode(region,"UTF-8")+"&"+
                                URLEncoder.encode("password","UTF-8") +"="+ URLEncoder.encode(password,"UTF-8")+"&"+
                               URLEncoder.encode("email","UTF-8") +"="+ URLEncoder.encode(email,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }

                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                return "URL Exception";
//                e.printStackTrace();
            } catch (IOException e) {
                return "Input/Output Error";
//                e.printStackTrace();
            }

        }

        return "Failed at a function";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        System.out.println(result);
        try {
            //("{your string}")
            JSONObject resultJSON = new JSONObject(result);
            boolean type = resultJSON.getBoolean("type");

            if (type == false)
            {
                String message = resultJSON.getString("message");
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
               // context.startActivity(new Intent(context, ManuShowAllEmployees.class));
            }
            else
            {
                Toast.makeText(context,"Employee added successfully...",Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, ManuShowAllEmployees.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
