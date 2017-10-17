package afyapepe.mobile.activity;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import afyapepe.mobile.R;

public class SwipeTest extends AppCompatActivity {

    //swipe test
    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_test);

        //swipe test
        gestureObject = new GestureDetectorCompat(this, new SwipeTest.LearnGesture());

    }
    //swipe test
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    //swipe test
    //object class
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY){

            if(event2.getX() > event1.getX()){

                Intent intentdruggie = new Intent(SwipeTest.this, ManuAddEmployee.class);
                finish();
                startActivity(intentdruggie);
            }
            else
            if(event2.getX() < event1.getX()){
                //left to right swipe

                Intent jigijigi = new Intent(SwipeTest.this, ManuSalesView.class);
                finish();
                startActivity(jigijigi);
            }
            return true;
        }


    }
}
