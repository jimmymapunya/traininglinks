package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.AppSingleton;

import static java.security.AccessController.getContext;

/**
 * Created by MaribolleR on 2017/07/01.
 * Purpose: Main class for the crime reporting app.
 */

public class MainActivity extends AppCompatActivity {

    /*Toolbar and Button components declaration*/
    private static final String URL_request = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/GetDashboard?AuthDetail.UserName=jimmy&AuthDetail.Role=map&AuthDetail.DeviceId=1234rrt";
    private static final String URL_emergency = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateEmergency";
    private Toolbar Toolbar;
    Context context = this;
    ProgressDialog progressDialog;
    private Button btnReportCrime, btnFraudCorruption, btnCases, btnCourtFinder, btnCompose, btnFeedback, btnEmergency, btnUnsafe;
    private String username, role, device_id, longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        progressDialog = new ProgressDialog(context);

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
                startActivity(new Intent(context, ReportCrimeActivity.class));
            }
        });

        btnFraudCorruption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ReportFraudAndCorruptionActivity.class));
            }
        });

        btnCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CaseListActivity.class));
            }
        });

        btnCourtFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CourtFinderActivity.class));
            }
        });

        btnCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ComposeMessageActivity.class));
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ProvideFeedback.class));
            }
        });

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEmergency(URL_emergency);
            }
        });

        btnUnsafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feeling unsafe. Distress signal sent.", Toast.LENGTH_LONG).show();
            }
        });

        volleyStringRequest(URL_request);
        setSupportActionBar(Toolbar);

        /*Back icon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);
    }

    //Get data from API service
    public void volleyStringRequest(String url)
    {
        String  REQUEST_TAG = "net.nnovationmessagehub.azurewebsites.";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"response "+ response.toString(), Toast.LENGTH_LONG).show();
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error "+ error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.hide();
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }

    //create emergency
    public void createEmergency(String url)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){

                //Values to post
                device_id = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
                username = "jimmy";
                role = "admin";
                longitude = "1233.555";
                latitude = "13334.55";

                Map<String,String> params = new HashMap<String, String>();
                params.put("AuthDetail.UserName",username);
                params.put("AuthDetail.Role","admin");
                params.put("AuthDetail.DeviceId", device_id);
                params.put("Location.Longitude",longitude);
                params.put("Location.Latitude", latitude);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
            //startActivity(new Intent(this, NotificationsActivity.class));

        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }
}
