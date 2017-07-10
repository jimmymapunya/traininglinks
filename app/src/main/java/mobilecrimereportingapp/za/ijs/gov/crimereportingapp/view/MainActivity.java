package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

/**
 * Created by MaribolleR on 2017/07/01.
 * Purpose: Main class for the crime reporting app.
 */

public class MainActivity extends AppCompatActivity {

    /*Toolbar and Button components declaration*/
    Context context;
    private Toolbar Toolbar;
    private Button btnReportCrime, btnFraudCorruption, btnCases, btnCourtFinder, btnCompose, btnFeedback, btnEmergency, btnUnsafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);

        btnReportCrime = (Button) findViewById(R.id.btnReportcrime);
        btnFraudCorruption = (Button) findViewById(R.id.btnFraud);
        btnCases = (Button) findViewById(R.id.btnMycases);
        btnCourtFinder = (Button) findViewById(R.id.btnCourtfinder);
        btnCompose = (Button) findViewById(R.id.btnCompose);
        btnFeedback = (Button) findViewById(R.id.btnFeedback);
        btnEmergency = (Button) findViewById(R.id.btnEmergency);
        btnUnsafe = (Button) findViewById(R.id.btnFeelsafe);


        /*Button listeners for clickable button events*/
        btnReportCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Report a Crime Selected.", Toast.LENGTH_LONG).show();
            }
        });

        btnFraudCorruption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Report Fraud and Corruption Selected.", Toast.LENGTH_LONG).show();
            }
        });

        btnCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "My Cases Selected.", Toast.LENGTH_LONG).show();
            }
        });

        btnCourtFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Court Finder Selected.", Toast.LENGTH_LONG).show();
            }
        });

        btnCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Compose Message Selected.", Toast.LENGTH_LONG).show();
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Feedback Selected.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, ProvideFeedback.class));
            }
        });

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Emergency Created.", Toast.LENGTH_LONG).show();
            }
        });

        btnUnsafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feeling unsafe. Distress signal sent.", Toast.LENGTH_LONG).show();
            }
        });

        setSupportActionBar(Toolbar);

        /*Back icon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        } else if (id == R.id.notifications) {
            startActivity(new Intent(this, NotificationsActivity.class));

        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }
}
