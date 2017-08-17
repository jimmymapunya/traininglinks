package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.GPSTracker;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.UserProfile;

/**
 * Created by JimmyM on 2017/07/07.
 */

public class ComposeMessageActivity extends AppCompatActivity {

    private Context context = this;
    private Toolbar Toolbar;
    private Spinner spinner1,spinner2;
    private Button btnSend;
    private EditText edtMessage;
    String device_id;
    String username;
    String role;
    String URL = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateInboxMessage";
    private String body, subject, to;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Compose Message");

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);


        setSupportActionBar(Toolbar);

        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        addItemsOnSpinner1();
        addItemsOnSpinner2();


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();

                //if (!itemSelected.equals("Select case number")) {
                    subject = itemSelected;
                //}

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                //if (!itemSelected.equals("To")) {
                    to = itemSelected;
                //}
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                body = edtMessage.getText().toString();

                if(body.equals(""))
                {
                    Toast.makeText(context,"Please enter message",Toast.LENGTH_LONG).show();
                }
                else if(subject == "Select case number" )
                {
                    Toast.makeText(context,"Please select subject",Toast.LENGTH_LONG).show();
                }
                else if(to=="To")
                {
                    Toast.makeText(context,"Please select recipient",Toast.LENGTH_LONG).show();

                }
                else
                {
                    createInboxMessage(URL, subject,body,to);
                    edtMessage.setText("");
                }

            }
        });


    }

    void addItemsOnSpinner1()
    {
        List<String> list = new ArrayList<String>();
        list.add("Select case number");
        list.add("10/2017/11");
        list.add("10/2017/98");
        list.add("09/2017/101");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

    }
    void addItemsOnSpinner2()
    {

        List<String> list = new ArrayList<String>();
        list.add("To");
        list.add("Jimmy Mapunya");
        list.add("Thabang Banks");
        list.add("Joe Smith");
        to = (String) spinner2.getSelectedItem();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

    }

    //create message
    public void createInboxMessage(String url, final String subject, final String body, final String to)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,"Sent",Toast.LENGTH_LONG).show();
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
                role = "admin";

                Map<String,String > params = new HashMap<String,String>();
                params.put("AuthDetail.UserName",username);
                params.put("AuthDetail.Role",role);
                params.put("AuthDetail.DeviceId", device_id);
                params.put("InboxId", "0");
                params.put("Subject",subject);
                params.put("Body", body);
                params.put("To", to);
                params.put("From", username);
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