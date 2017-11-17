package afyapepe.mobile.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import afyapepe.mobile.R;
import afyapepe.mobile.adapter.ManuDrugSubadapter;

import static afyapepe.mobile.R.drawable.ic_arrow_back_white_24dp;
import static afyapepe.mobile.R.drawable.ic_arrow_forward_white_24dp;
import static afyapepe.mobile.R.drawable.ic_compare_arrows_black_24dp;

public class ManudrugSubView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the  activity_main.xml layout file
        setContentView(R.layout.activity_manudrug_sub_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        // SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(this, getSupportFragmentManager());
        ManuDrugSubadapter vadapter = new ManuDrugSubadapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        // viewPager.setAdapter(adapter);
        viewPager.setAdapter(vadapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(ic_arrow_forward_white_24dp);
        tabLayout.getTabAt(1).setIcon(ic_arrow_back_white_24dp);
        tabLayout.getTabAt(2).setIcon(ic_compare_arrows_black_24dp);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        // searchView.setMenuItem(item);
        return true;
    }

}
