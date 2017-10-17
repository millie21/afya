package afyapepe.mobile.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import afyapepe.mobile.R;

public class Prescriptions extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_prescriptions, container, false);

/*
        agea=(TextView) rootView.findViewById(R.id.age);
        blood=(TextView) rootView.findViewById(R.id.blood);
        pob=(TextView) rootView.findViewById(R.id.placeof);
        gender=(TextView) rootView.findViewById(R.id.textView13);
        name=(TextView) rootView.findViewById(R.id.name);

*/


        //new Prescriptions().AsyncFetch().execute();




        return rootView;


    }


}
