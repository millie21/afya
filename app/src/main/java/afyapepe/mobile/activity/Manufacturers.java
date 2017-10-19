package afyapepe.mobile.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.R.drawable.image1;
import static android.R.attr.id;
import static support.v7.appcompat.R.attr.logo;

public class Manufacturers extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    public ImageView ImageView;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        
        Context context = null;

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
       // String imageView = user.get("logo");
        String email = user.get("email");

        // Displaying the user details on the screen
//     imageView.setImageBitmap(imageView);
//imageView.setImageBitmap(BitmapFactory.decodeFile("http://localhost/afyapepe3/public/img/merck_logo_detail.png"));

        //to display manudetails after login
        txtName.setText(name);
  //      txtEmail.setText(email);

        // Logout button click event
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                logoutUser();
//            }
//        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Picasso.with(context) // Context
//                .load("http://abc.imgur.com/gxsg.png") // URL or file
//                .into(imageView); // An ImageView object to show the loaded image

//        Picasso.with(this)
//                .load("http://192.168.2.100/afyapepe3/public/img/merck_logo_detail.png")
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//
//               /* Save the bitmap or do something with it here */
//
//                        // Set it in the ImageView
//                        imageView.setImageBitmap(bitmap)
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
      //  new GetPic().execute();
    }
//    class GetPic  extends AsyncTask<String, Bitmap, Bitmap> {
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            URL img_value = null;
//            try {
////                img_value = new URL("https://graph.facebook.com/"+id+"picture?type=large");
//                img_value = new URL("http://localhost/afyapepe3/public/img/merck_logo_detail.png");
//            } catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            try {
//                imageView = BitmapFactory.decodeStream(img_value.openConnection().getInputStream());
//                Log.i("bitmap_pic_get", "ok");
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            //bitmap_pic = Bitmap.createScaledBitmap(bitmap_pic, 50, 50, false);
//            return bitmap_pic ;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap_pic) {
//            ImageView.setImageBitmap(logo);
//        }
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manufacturers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //other logout option

            session.setLogin(false);

            db.deleteUsers();

            // Launching the login activity
            Intent intent = new Intent(Manufacturers.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_employees) {
            // Handle the camera action
            Intent intentemployees = new Intent(Manufacturers.this, ManuEmployeesView.class);
            startActivity(intentemployees);
        } else if (id == R.id.nav_sales) {
            //to  manufacturer sales
            Intent intentsaleslanding = new Intent(Manufacturers.this, ManuSalesView.class);
            startActivity(intentsaleslanding);

        } else if (id == R.id.nav_drugs) {

            //to drug subs
            Intent intentdruggie = new Intent(Manufacturers.this, ManudrugSubView.class);
            startActivity(intentdruggie);

        } else if (id == R.id.nav_stock) {
            //to manufacturer stocks view
            Intent intentstocks = new Intent(Manufacturers.this, ManuStocksView.class);
            startActivity(intentstocks);

        } else if (id ==R.id.nav_trends) {
            // to manufacturer trends
                Intent intenttrends = new Intent(this,ManuTrendsView.class);
            startActivity(intenttrends);

        } else if (id == R.id.nav_competition) {
            //to manufacturer competition analysis
             Intent intentcompetition = new Intent(this,ManuCompetitionView.class);
             startActivity(intentcompetition);
        }
        else if (id == R.id.nav_sectorsum) {
            //to manufacturer sector summary
            Intent intentsectorsumm = new Intent(this, ManuSectorSumView.class);
            startActivity(intentsectorsumm);
        }
             else if (id == R.id.nav_email){
                //to manufacturer sector summary
                Intent intentsectorsumm = new Intent(this,ManuEmail.class);
                startActivity(intentsectorsumm);


        } else if (id == R.id.nav_logout) {

            session.setLogin(false);

            db.deleteUsers();

            // Launching the login activity
            Intent intent = new Intent(Manufacturers.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
        /**
         * Logging out the user. Will set isLoggedIn flag to false in shared
         * preferences Clears the user data from sqlite users table
         * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(Manufacturers.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    //cardviews
    public void cardview1(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuEmployeesView.class);
        startActivity(intent);
    }

    public void cardview2(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuSalesView.class);
        startActivity(intent);
    }

    public void cardview3(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuStocksView.class);
        startActivity(intent);
    }

    public void cardview4(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManudrugSubView.class);
        startActivity(intent);
    }
    public void cardview5(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuTrendsView.class);

        startActivity(intent);
    }
    public void cardview6(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuSectorSumView.class);
        startActivity(intent);
    }

}
