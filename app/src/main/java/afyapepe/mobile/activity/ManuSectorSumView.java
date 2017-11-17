package afyapepe.mobile.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import afyapepe.mobile.R;
import afyapepe.mobile.adapter.ManuOtherAdapter;

import static afyapepe.mobile.R.drawable.earth;
import static afyapepe.mobile.R.drawable.ic_local_hospital_white_24dp;
import static afyapepe.mobile.R.drawable.stethoscope;

/**
 * Created by Millie Agallo on 10/4/2017.
 */

public class ManuSectorSumView extends AppCompatActivity {

    CardView trendtoday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the  activity_main.xml layout file
        setContentView(R.layout.activity_manu_sector_sum_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        ManuOtherAdapter vadapter = new ManuOtherAdapter(this, getSupportFragmentManager());



        // Set the adapter onto the view pager
        // viewPager.setAdapter(adapter);
        viewPager.setAdapter(vadapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //setting icons on tabs
        tabLayout.getTabAt(0).setIcon(earth);
        tabLayout.getTabAt(1).setIcon(ic_local_hospital_white_24dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        // searchView.setMenuItem(item);
        return true;
    }
}
