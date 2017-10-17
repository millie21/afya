package afyapepe.mobile.activity;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {


    private ListView mDrawerLis;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter2;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private SQLiteHandler db;
    private SessionManager session;



    class C00171 implements OnItemClickListener {
        C00171() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            switch (position) {
                case 0:
                    Main2Activity.this.startActivity(new Intent(Main2Activity.this.getApplicationContext(), Main2Activity.class));
                    return;
                case 1:
                    Main2Activity.this.startActivity(new Intent(Main2Activity.this.getApplicationContext(), alaglist.class));
                    return;
                default:
                    return;
            }
        }
    }

    class C00161 implements OnClickListener {
        C00161() {
        }

        public void onClick(View view) {
            Snackbar.make(view, (CharSequence) "Thank you for using Afya Pepe, we have not yet included customer support", 0).setAction((CharSequence) "Action", null).show();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Contact_info();
                case 1:
                    return new Basic_info();
                case 2:
                    return new Next_of_kin_details();
                default:
                    return null;
            }
        }

        public int getCount() {
            return 3;
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Contact";
                case 1:
                    return "Basic Info";
                case 2:
                    return "Next Of Kin";
                default:
                    return null;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.acivnewnew);

        db = new SQLiteHandler(getApplicationContext());


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        mDrawerLis = (ListView) findViewById(R.id.navListon);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutf);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarop));
        this.mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(R.id.container);
        this.mViewPager.setAdapter(this.mSectionsPagerAdapter);
        ((TabLayout) findViewById(R.id.tabs)).setupWithViewPager(this.mViewPager);
        ((FloatingActionButton) findViewById(R.id.fab)).setOnClickListener(new C00161());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    private void addDrawerItems() {
        String[] osArray = { "Home","Allergies","History","Vaccination","Hospital","Test", "Prescription" };

        mAdapter2 = new ArrayAdapter<String>(this, R.layout.custi, osArray);
        View header = getLayoutInflater().inflate(R.layout.headen, null);

        //addHeaderView is to add custom content into first row
        mDrawerLis.addHeaderView(header);


        mDrawerLis.setAdapter(mAdapter2);

        mDrawerLis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position) {

                    case 1:
                        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                        break;

                    case 2:
                        startActivity(new Intent(getApplicationContext(), alaglist.class));
                        break;


                    case 3:
                        startActivity(new Intent(getApplicationContext(), histyhisty.class));
                        break;

                }
            }
        });
    }


    private void setupDrawer() {
        this.mDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Main2Activity.this.getSupportActionBar().setTitle((CharSequence) "Navigation");
                Main2Activity.this.invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                Main2Activity.this.getSupportActionBar().setTitle(Main2Activity.this.mActivityTitle);
                Main2Activity.this.invalidateOptionsMenu();
            }
        };
        this.mDrawerToggle.setDrawerIndicatorEnabled(true);
        this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mDrawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            logoutUser();

            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


/*
    public String numdat()
    {
        return recev;


    }
*/
}
