package afyapepe.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import afyapepe.mobile.R;

/**
 * Created by Agallo on 18-Sep-17.
 */


public class ManuUpdateEmployeeActivity extends AppCompatActivity {

    EditText etname, etemail, etpassword,etrole;
    EditText etregion,etjob;
    Button RegisterEmployee;
    String name, email, password, role, region, job;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_employee);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etname = (EditText) findViewById(R.id.ename);
        etemail = (EditText) findViewById(R.id.eemail);
        etjob = (EditText) findViewById(R.id.egroup);
        etpassword = (EditText) findViewById(R.id.eid);
        etregion = (EditText) findViewById(R.id.eregion);
        etrole = (EditText) findViewById(R.id.erole);
    }

//    public void goback(View view){
//        Intent intent = new Intent(ManuUpdateEmployeeActivity.this, ManuShowSingleEmployeeActivity.class);
//        startActivity(intent);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch(item.getItemId()) {
            case R.id.menu_home:
                Intent intent = new Intent(this, ManuShowAllEmployees.class);
                this.startActivity(intent);
                break;
            case R.id.menu_search:
                // another startActivity, this is for item with id "menu_item2"
                Intent intent2 = new Intent(this, ManuShowAllEmployees.class);
                this.startActivity(intent2);
                break;
            case R.id.menu_notifications:
                Intent intent3 = new Intent(this, ManuShowAllEmployees.class);
                this.startActivity(intent3);
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
