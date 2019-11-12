package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class SplashScreen extends AppCompatActivity {

    private Context mContext;
    private static final int DelaySeconds = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*Assign context to the declared context.*/
        mContext = this;

        splashScreenAwait();
    }

    /**
     * splashScreenAwait: Used to start a delay for the splash screen before proceeding.
     * Uses thread to delay for 2 secs.
     */
    private void splashScreenAwait() {

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(DelaySeconds);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    startActivity( new Intent(mContext, MainActivity.class));
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
