package afyapepe.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import afyapepe.mobile.R;

public class ManuTrendsView extends AppCompatActivity {

    CardView trendtoday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the  activity_main.xml layout file
        setContentView(R.layout.activity_manu_trends_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        // SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(this, getSupportFragmentManager());
        ManuSimpleAdapter vadapter = new ManuSimpleAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        // viewPager.setAdapter(adapter);
        viewPager.setAdapter(vadapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


}