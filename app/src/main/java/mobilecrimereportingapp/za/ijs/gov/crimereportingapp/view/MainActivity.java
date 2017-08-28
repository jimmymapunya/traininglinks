package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.AppSingleton;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.GPSTracker;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.UserProfile;

/**
 * Created by MaribolleR on 2017/07/01.
 * Purpose: Main class for the crime reporting app.
 */

public class MainActivity extends AppCompatActivity {

    /*Toolbar and Button components declaration*/
    private static final String URL_request = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/GetDashboard?AuthDetail.UserName=john&AuthDetail.Role=map&AuthDetail.DeviceId=1234rrt";
    private static final String URL_emergency = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateEmergency";
    private static final String URL_unsafe = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateUnsafe";

    private Toolbar Toolbar;
    Context context = this;
    ProgressDialog progressDialog;
    private Button btnReportCrime, btnFraudCorruption, btnCases, btnCourtFinder, btnCompose, btnFeedback, btnEmergency, btnUnsafe;
    private String username, role, device_id, longitude, latitude;
    private double lat,lon;
    GPSTracker gpsTracker;

    public static String notificationCount, inboxCount, myCaseCount;
    public static JSONArray jsonArrayNotification,jsonArrayInboxes;
    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        progressDialog = new ProgressDialog(context);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        btnReportCrime = (Button) findViewById(R.id.btnReportcrime);
        btnFraudCorruption = (Button) findViewById(R.id.btnFraud);
        btnCases = (Button) findViewById(R.id.btnMycases);
        btnCourtFinder = (Button) findViewById(R.id.btnCourtfinder);
        btnCompose = (Button) findViewById(R.id.btnCompose);
        btnFeedback = (Button) findViewById(R.id.btnFeedback);
        btnEmergency = (Button) findViewById(R.id.btnEmergency);
        btnUnsafe = (Button) findViewById(R.id.btnFeelsafe);

        /*Button listeners for clickable button events*/

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });

        inboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InboxActivity.class));
            }
        });
        btnReportCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Please select whether you are reporting as a witness or victim")
                        .setCancelable(true)
                        .setPositiveButton("Witness", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ReportCrimeActivity.isVictim = false;
                                ProfileActivity.screenToGoTo = true;
                                startActivity(new Intent(context, ProfileActivity.class));
                            }
                        })
                        .setNegativeButton("Victim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ReportCrimeActivity.isVictim = true;
                                ProfileActivity.screenToGoTo = true;
                                startActivity(new Intent(context, ProfileActivity.class));
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        btnFraudCorruption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Please select type of Tip-Off you are reporting")
                        .setCancelable(true)
                        .setPositiveButton("Fraud and Corruption", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ProfileActivity.screenToGoTo = false;
                                ProfileActivity.screenToGoToTipOffGeneral = false;
                                ProfileActivity.screenToGoToTipOffFraud = true;
                                startActivity(new Intent(context, ProfileActivity.class));
                            }
                        })
                        .setNegativeButton("General", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ProfileActivity.screenToGoTo = false;
                                ProfileActivity.screenToGoToTipOffGeneral = true;
                                ProfileActivity.screenToGoToTipOffFraud = false;
                                startActivity(new Intent(context, ProfileActivity.class));
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
               // startActivity(new Intent(context, ReportFraudAndCorruptionActivity.class));
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

                /**String uri = "https://maps.google.com/maps?saddr=Your location, Pretoria&daddr=";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(i);*/
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


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to report an emergency, continuing will results in your details and current location being sent to SAPS?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                createEmergency(URL_emergency);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnUnsafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCreateUnsafe(URL_unsafe);
            }
        });

        getDashboard(URL_request);

        setSupportActionBar(Toolbar);

        /**Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);
    }

    //Get data from API service for dashboard
    public void getDashboard(String url)
    {
        String  REQUEST_TAG = "net.nnovationmessagehub.azurewebsites.";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getApplicationContext(),"response "+ response.toString(), Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    notificationCount = jsonResponse.getString("notificationCount");
                    notificationCountIcon.setText(notificationCount);

                    inboxCount = jsonResponse.getString("inboxCount");
                    inboxCountIcon.setText(inboxCount);

                    myCaseCount= jsonResponse.getString("notificationCount");
                    jsonArrayNotification = jsonResponse.getJSONArray("notifications");
                    jsonArrayInboxes = jsonResponse.getJSONArray("inboxes");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }

                Toast.makeText(context, message ,Toast.LENGTH_LONG).show();
                error.printStackTrace();

            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }

    //create emergency
    public void createEmergency(String url)
    {
        gpsTracker = new GPSTracker(context);
        lat = gpsTracker.getLatitude();
        lon = gpsTracker.getLongitude();
        //Toast.makeText(MainActivity.this,"Lon "+lat,Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,"Emergency reported",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }

                        Toast.makeText(context, message ,Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){

                //Values to post
                device_id = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
                username = "John";
                role = "1";
                longitude = String.valueOf(lat);
                latitude = String.valueOf(lon);

                Map<String,String> params = new HashMap<String, String>();
                params.put("AuthDetail.UserName",username);
                params.put("AuthDetail.Role",role);
                params.put("AuthDetail.DeviceId", device_id);
                params.put("Location.Longitude",longitude);
                params.put("Location.Latitude", latitude);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //create unsafe
    public void createCreateUnsafe(String url)
    {
        gpsTracker = new GPSTracker(context);
        lat = gpsTracker.getLatitude();
        lon = gpsTracker.getLongitude();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Feeling unsafe. Distress signal sent.", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }

                        Toast.makeText(context, message ,Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){

                //Values to post
                device_id = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
                username = UserProfile.Username;
                role = "1";
                longitude = String.valueOf(lat);
                latitude = String.valueOf(lon);

                Map<String,String> params = new HashMap<String, String>();
                params.put("AuthDetail.UserName",username);
                params.put("AuthDetail.Role",role);
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
            Toast.makeText(MainActivity.this,notificationCount,Toast.LENGTH_LONG).show();
            
        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }
}
