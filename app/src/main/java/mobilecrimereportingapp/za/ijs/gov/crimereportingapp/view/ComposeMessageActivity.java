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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.GPSTracker;

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
    private String subject, to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Compose Message");


        setSupportActionBar(Toolbar);

        /*Back icon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        addItemsOnSpinner1();
        addItemsOnSpinner2();

        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String body = edtMessage.getText().toString();
                createInboxMessage(URL,subject, body, to, username);
            }
        });


    }

    void addItemsOnSpinner1()
    {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("10/2017/11");
        list.add("10/2017/98");
        list.add("09/2017/101");
        subject = (String) spinner1.getSelectedItem();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

    }
    void addItemsOnSpinner2()
    {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
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
    public void createInboxMessage(String url, final String subject, final String body, final String to,final String from)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){

                //Values to post
                device_id = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
                username = "jimmy";
                role = "admin";


                Map<String,String > params = new HashMap<String,String>();
                params.put("AuthDetail.UserName",username);
                params.put("AuthDetail.Role",role);
                params.put("AuthDetail.DeviceId", device_id);
                params.put("InboxId", "0");
                params.put("Subject",subject);
                params.put("Body", body);
                params.put("To", to);
                params.put("From", from);
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