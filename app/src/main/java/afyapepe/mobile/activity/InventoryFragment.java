package afyapepe.mobile.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

/**
 * Created by Millie Agallo on 11/4/2017.
 */

//public class InventoryFragment extends Activity {
//
//    private List<Inventory>inventoryList = new ArrayList<Inventory>();
//    private ListView listView;
//    private InventoryAdapter adapter;
//    private SQLiteHandler db;
//    private SessionManager session;
//    private ProgressDialog pDialog;
//
//    public InventoryFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
//        db = new SQLiteHandler(getActivity());
//
//        session = new SessionManager(getActivity());
//
//        //Fetching user details from SQLite
//        HashMap<String, String>user = db.getUserDetails();
//
//        String email = user.get("email");
//
//        listView = (ListView)view.findViewById(R.id.invetory);
//        adapter = new InventoryAdapter(getActivity(),inventoryList);
//        listView.setAdapter(adapter);
//
//        pDialog = new ProgressDialog(getActivity());
//
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_INVENTORY,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG,response.toString());
//                        hidePDialog();
//                        try{
//
//                            JSONArray request = new JSONArray(response);
//                            for (int i = 0; i < request.length(); i++){
//
//                                Employee inventory = new Employee();
//                                JSONObject jsonObject = null;
//                                jsonObject = request.getJSONObject(i);
//                                inventory.setDrugname(jsonObject.getString("drugname"));
//                                inventory.setManufacturer(jsonObject.getString("Manufacturer"));
//                                inventory.setQuantity(jsonObject.getInt("quantity"));
//                                inventory.setEntry_date(jsonObject.getString("entry_date"));
//
//                                inventoryList.add(inventory);
//                            }
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                            Toast.makeText(getActivity(), e.toString(),Toast.LENGTH_SHORT).show();
//                        }
//
//
////                        Notifies the attached observers that the underlying data has been changed
////                                * and any View reflecting the data set should refresh itself.
//                        adapter.notifyDataSetChanged();
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (pDialog != null){
//                            pDialog.dismiss();
//                            pDialog = null;
//                        }
//                        Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_LONG).show();
//                        error.printStackTrace();
//                    }
//                })
//
//        {
//            @Override
//            protected Map<String, String>getParams() throws AuthFailureError {
//                db = new SQLiteHandler(getActivity());
//
//                // Fetching user details from SQLite
//                HashMap<String, String> user = db.getUserDetails();
//
//                String email = user.get("email");
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email",email);
//
//                return params;
//            }
//        };
   // }