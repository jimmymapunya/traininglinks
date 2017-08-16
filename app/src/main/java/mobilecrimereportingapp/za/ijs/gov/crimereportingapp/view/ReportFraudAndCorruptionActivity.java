package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class ReportFraudAndCorruptionActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;

    private TextView notificationCountIcon, inboxCountIcon, txtTittle1, txttitle2;
    private Context context = this;
    private FrameLayout notificationLayout, inboxLayout;
    private Button btnSubmitBus, btnSubmitIndividual;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText txtBusTel,txtCell,individualName,businessName, txtPhysicalAddress, txtBusinessAddress, dateOccurredBus, locationBus, complainDetailBus,dateOccurredIndividual, locationIndividual, complainDetailIndividual;
    Spinner spinnerReportType, spinnerComplaintype;
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


        String[] arrReport = {"Fraud","Corruption"};
        String[] arrCompanyOrIndividual = {"Business","Individual"};

        ArrayAdapter<String> reportAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrReport);
        ArrayAdapter<String> companyOrIndividualAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrCompanyOrIndividual);

       // spinnerComplaintype = (Spinner)findViewById(R.id.spinnerPersonOrBusiness);
        spinnerReportType = (Spinner)findViewById(R.id.spinnerType);

        spinnerReportType.setAdapter(reportAdapter);
        spinnerComplaintype.setAdapter(companyOrIndividualAdapter);

        setSupportActionBar(Toolbar);


       txtBusTel = (EditText)findViewById(R.id.txtBusTel);
       txtCell = (EditText)findViewById(R.id.txtCell);
        individualName = (EditText)findViewById(R.id.txtIndividualName);
        businessName = (EditText)findViewById(R.id.txtBusinessName);
        txtPhysicalAddress = (EditText)findViewById(R.id.txtPhysicalAddress);
        txtBusinessAddress = (EditText)findViewById(R.id.txtBusinessAddress);
        dateOccurredBus = (EditText)findViewById(R.id.txtBusDate);
        locationBus = (EditText)findViewById(R.id.txtBusLocation);
        complainDetailBus = (EditText)findViewById(R.id.txtBusFraudDetails);
        dateOccurredIndividual = (EditText)findViewById(R.id.txtIndividualDate);
        locationIndividual = (EditText)findViewById(R.id.txtIndividualLocation);
        complainDetailIndividual = (EditText)findViewById(R.id.txtIndividualFraudDetails);
        txtTittle1 = (TextView) findViewById(R.id.textView2);
        txttitle2 = (TextView) findViewById(R.id.textView3);
        btnSubmitBus = (Button) findViewById(R.id.btnSubmitBus);
        btnSubmitIndividual = (Button) findViewById(R.id.btnSubmitIndividual);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateOccured();
                updateDateOccuredIndividual();
            }

        };

        dateOccurredBus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ReportFraudAndCorruptionActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateOccurredIndividual.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ReportFraudAndCorruptionActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //personOrBusiness = spinnerComplaintype.getSelectedItem().toString();

        spinnerComplaintype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String reportType = spinnerReportType.getSelectedItem().toString();
                if(position==0){

                    makeItRequired(businessName,"Business name Committing "+reportType);
                    makeItRequired(dateOccurredBus,"Date "+reportType+" occurred");
                    makeItRequired(complainDetailBus,"Detail description of "+reportType);
                    makeItRequired(locationBus,"Location "+ reportType+" occurred");

                    businessName.setVisibility(View.VISIBLE);
                    txtBusTel.setVisibility(View.VISIBLE);
                    txtBusinessAddress.setVisibility(View.VISIBLE);
                    dateOccurredBus.setVisibility(View.VISIBLE);
                    locationBus.setVisibility(View.VISIBLE);
                    complainDetailBus.setVisibility(View.VISIBLE);
                    txttitle2.setVisibility(View.VISIBLE);
                    btnSubmitBus.setVisibility(View.VISIBLE);

                    txtPhysicalAddress.setVisibility(View.GONE);
                    txtCell.setVisibility(View.GONE);
                    individualName.setVisibility(View.GONE);
                    dateOccurredIndividual.setVisibility(View.GONE);
                    locationIndividual.setVisibility(View.GONE);
                    complainDetailIndividual.setVisibility(View.GONE);
                    //Toast.makeText(context,personOrBusiness+ " selected "+position,Toast.LENGTH_LONG).show();
                    txtTittle1.setVisibility(View.GONE);
                    btnSubmitIndividual.setVisibility(View.GONE);
                    dateOccurredBus.setText("");

                }else{

                    makeItRequired(individualName,"Person's name Committing "+reportType);
                    makeItRequired(dateOccurredIndividual,"Date "+reportType+" occurred");
                    makeItRequired(complainDetailIndividual,"Detail description of "+reportType);
                    makeItRequired(locationIndividual,"Location "+ reportType+" occurred");

                    businessName.setVisibility(View.GONE);
                    txtBusTel.setVisibility(View.GONE);
                    txtBusinessAddress.setVisibility(View.GONE);
                    dateOccurredBus.setVisibility(View.GONE);
                    locationBus.setVisibility(View.GONE);
                    complainDetailBus.setVisibility(View.GONE);
                    txttitle2.setVisibility(View.GONE);
                    btnSubmitBus.setVisibility(View.GONE);

                    txtPhysicalAddress.setVisibility(View.VISIBLE);
                    txtCell.setVisibility(View.VISIBLE);
                    individualName.setVisibility(View.VISIBLE);
                    dateOccurredIndividual.setVisibility(View.VISIBLE);
                    locationIndividual.setVisibility(View.VISIBLE);
                    complainDetailIndividual.setVisibility(View.VISIBLE);
                    txtTittle1.setVisibility(View.VISIBLE);
                    btnSubmitIndividual.setVisibility(View.VISIBLE);
                    dateOccurredIndividual.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnSubmitBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strBusinessName = businessName.getText().toString();
                String fraudDetails = complainDetailBus.getText().toString();
                String address = locationBus.getText().toString();
                String dateOccured = dateOccurredBus.getText().toString();

                businessName.setBackgroundResource(R.drawable.edit_text_style);
                complainDetailBus.setBackgroundResource(R.drawable.edit_text_style);
                locationBus.setBackgroundResource(R.drawable.edit_text_style);
                dateOccurredBus.setBackgroundResource(R.drawable.edit_text_style);

                if(strBusinessName.length()<1||fraudDetails.length()<1||address.length()<1||dateOccured.length()<1){
                    if(strBusinessName.length()<1){
                        businessName.setBackgroundResource(R.drawable.error_text_style);
                    }

                    if(fraudDetails.length()<1){
                        complainDetailBus.setBackgroundResource(R.drawable.error_text_style);
                    }
                    if(address.length()<1){
                        locationBus.setBackgroundResource(R.drawable.error_text_style);
                    }
                    if(dateOccured.length()<1){
                        dateOccurredBus.setBackgroundResource(R.drawable.error_text_style);
                    }
                }else{
                    CreateReportFraud(URL,strBusinessName,fraudDetails,address,"Business");
                    businessName.setText("");
                    txtBusTel.setText("");
                    txtBusinessAddress.setText("");
                    dateOccurredBus.setText("");
                    locationBus.setText("");
                    complainDetailBus.setText("");
                    txttitle2.setText("");

                }




            }
        });

        btnSubmitIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strIndivudualName = individualName.getText().toString();
                String fraudDetails = complainDetailIndividual.getText().toString();
                String address = locationIndividual.getText().toString();
                String dateOccured = dateOccurredIndividual.getText().toString();

                individualName.setBackgroundResource(R.drawable.edit_text_style);
                complainDetailIndividual.setBackgroundResource(R.drawable.edit_text_style);
                locationIndividual.setBackgroundResource(R.drawable.edit_text_style);
                dateOccurredIndividual.setBackgroundResource(R.drawable.edit_text_style);

                if(strIndivudualName.length()<1||fraudDetails.length()<1||address.length()<1||dateOccured.length()<1){
                    if(strIndivudualName.length()<1){
                        individualName.setBackgroundResource(R.drawable.error_text_style);
                    }

                    if(fraudDetails.length()<1){
                        complainDetailIndividual.setBackgroundResource(R.drawable.error_text_style);
                    }
                    if(address.length()<1){
                        locationIndividual.setBackgroundResource(R.drawable.error_text_style);
                    }
                    if(dateOccured.length()<1){
                        dateOccurredIndividual.setBackgroundResource(R.drawable.error_text_style);
                    }
                }else{
                    CreateReportFraud(URL,strIndivudualName,fraudDetails,address,"Business");
                    txtPhysicalAddress.setText("");
                    txtCell.setText("");
                    individualName.setText("");
                    dateOccurredIndividual.setText("");
                    locationIndividual.setText("");
                    complainDetailIndividual.setText("");
                    btnSubmitIndividual.setText("");
                    dateOccurredIndividual.setText("");
                }




            }
        });


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


                Map<String,String > params = new HashMap<String,String>();
                params.put("AuthDetail.UserName","jimmy");
                params.put("AuthDetail.Role","admin");
                params.put("AuthDetail.DeviceId", Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID));
                params.put("SuspectType", fraudType);
                params.put("FraudSuspect",companyOrPerson);
                params.put("Physical_Address", address);
                params.put("FraudDetails", fraudDetails);
                params.put("From", "jimmy");
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
    private void updateDateOccured() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateOccurredBus.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateDateOccuredIndividual() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateOccurredIndividual.setText(sdf.format(myCalendar.getTime()));
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

    private void makeItRequired(EditText editText, String text){

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text);
        int start2 = builder.length();
        builder.append("*");
        int end2 = builder.length();

        builder.setSpan(new ForegroundColorSpan(Color.RED), start2, end2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(builder);


    }
}

