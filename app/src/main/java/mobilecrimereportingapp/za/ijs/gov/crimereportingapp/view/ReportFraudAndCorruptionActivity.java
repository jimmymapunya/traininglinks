package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.UserProfile;

public class ReportFraudAndCorruptionActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;

    private TextView notificationCountIcon, inboxCountIcon;
    private Context context = this;
    private FrameLayout notificationLayout, inboxLayout;
    EditText txtFullnames,txtEmail,txtCellnumber,txtIdnumber,txtCompanyOrPerson,txtAddress,txtFraudDetails;
    Spinner spnfraudType;
    String device_id;
    String username;
    String role;
    String URL = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateReportFraud";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_fraud_and_corruption);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Report Fraud And Corruption");


        String[] arrTypes = {"Company","Person"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrTypes);
        Spinner spinTypes = (Spinner)findViewById(R.id.spinnerType);
        spinTypes.setAdapter(adapter);
        setSupportActionBar(Toolbar);

        txtFullnames = (EditText)findViewById(R.id.txtFullnames);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtCellnumber = (EditText)findViewById(R.id.txtCellnumber);
        txtIdnumber = (EditText)findViewById(R.id.txtIdnumber);
        txtCompanyOrPerson = (EditText)findViewById(R.id.txtCompanyOrPerson);
        txtAddress = (EditText)findViewById(R.id.txtAddress);
        txtFraudDetails = (EditText)findViewById(R.id.txtFraudDetails);
        spnfraudType=(Spinner) findViewById(R.id.spinnerType);

        txtFullnames.setText("Maribolle Mabunda");
        txtEmail.setText("Marie@bolle.co.za");
        txtCellnumber.setText("0733119856");
        txtIdnumber.setText("9801180404080");

        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullnames = txtFullnames.getText().toString();
                String email = txtEmail.getText().toString();
                String cellnumber = txtCellnumber.getText().toString();
                String companyOrPerson = txtCompanyOrPerson.getText().toString();
                String fraudDetails = txtFraudDetails.getText().toString();
                String address = txtAddress.getText().toString();
                String idNumber = txtIdnumber.getText().toString();
                String fraudType =spnfraudType.getSelectedItem().toString();

                CreateReportFraud(URL,companyOrPerson,fraudDetails,address,fraudType);
                txtAddress.setText("");
                txtCompanyOrPerson.setText("");
                txtFraudDetails.setText("");
            }
        });


        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);
    }

    private void CreateReportFraud(final String url,final String companyOrPerson,final String fraudDetails,final String address,final String fraudType) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,"Corruption or fraud have been reported",Toast.LENGTH_LONG).show();
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
                username = UserProfile.Username;
                role = "admin";

                Map<String,String > params = new HashMap<String,String>();
                params.put("AuthDetail.UserName",username);
                params.put("AuthDetail.Role",role);
                params.put("AuthDetail.DeviceId", device_id);
                params.put("SuspectType", fraudType);
                params.put("FraudSuspect",companyOrPerson);
                params.put("Physical_Address", address);
                params.put("FraudDetails", fraudDetails);
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

