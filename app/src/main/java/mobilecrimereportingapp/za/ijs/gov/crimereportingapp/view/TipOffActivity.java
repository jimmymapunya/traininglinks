package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.UserProfile;

public class TipOffActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;

    private TextView notificationCountIcon, inboxCountIcon, txtTittle1, txttitle2;
    private Context context = this;
    private FrameLayout notificationLayout, inboxLayout;
    private Button btnSubmit;
    private int device_id;
    private String username, role;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText txtOffenderName,txtOffenderAddress,txtOffenderCell,txtOffenseComiited, txtDateOfOffense, txtLocationOfOffense, txtOffenseDetails, txtVictimNames, txtVictimAddress,txtVictimCellNumber, txtCaseNo;
    Spinner spinnerQuestion;
    String URL = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateTippOff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipp_off);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Report Fraud And Corruption");


        String[] arrOptions = {"No","Yes"};

        ArrayAdapter<String> spinnerQuestionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrOptions);
        spinnerQuestion = (Spinner)findViewById(R.id.spinnerQuestion);
        spinnerQuestion.setAdapter(spinnerQuestionAdapter);

        txtOffenderName = (EditText)findViewById(R.id.txtOffenderName);
        txtOffenderAddress = (EditText)findViewById(R.id.txtOffenderAddress);
        txtOffenderCell = (EditText)findViewById(R.id.txtOffenderCell);
        txtOffenseComiited = (EditText)findViewById(R.id.txtOffenseType);
        txtDateOfOffense = (EditText)findViewById(R.id.txtDateCommited);
        txtLocationOfOffense = (EditText)findViewById(R.id.txtOffenseLocation);
        txtOffenseDetails = (EditText)findViewById(R.id.txtOffenseDesc);
        txtVictimNames = (EditText)findViewById(R.id.txtVictimName);
        txtVictimAddress = (EditText)findViewById(R.id.txtVictimAddress);
        txtVictimCellNumber = (EditText)findViewById(R.id.txtVictimCell);
        txtCaseNo = (EditText) findViewById(R.id.txtCaseNo);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        makeItRequired(txtOffenseComiited,"Offence committed/Planned");
        makeItRequired(txtDateOfOffense,"Date offence was committed/Planned");
        makeItRequired(txtLocationOfOffense,"Address offence took place");
        makeItRequired(txtOffenseDetails,"Describe the offence");

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
            }

        };

        txtDateOfOffense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(TipOffActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        spinnerQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){

                    txtCaseNo.setVisibility(View.GONE);


                }else{
                    txtCaseNo.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strTypeOfOffesne = txtOffenseComiited.getText().toString();
                String strDateOfOffense = txtDateOfOffense.getText().toString();
                String strLocationOfOffense = txtLocationOfOffense.getText().toString();
                String strOffenseDesc = txtOffenseDetails.getText().toString();
                String stroffenderNames =txtOffenderName.getText().toString();
                String stroffenderAddress=txtOffenderAddress.getText().toString();
                String stroffenderCell=txtOffenderCell.getText().toString();
                String strvictimNames=txtVictimNames.getText().toString();
                String strvictimAddress=txtVictimAddress.getText().toString();
                String strvictimCell=txtVictimCellNumber.getText().toString();
                String strcaseNo=txtCaseNo.getText().toString(); ;

                txtOffenseComiited.setBackgroundResource(R.drawable.edit_text_style);
                txtDateOfOffense.setBackgroundResource(R.drawable.edit_text_style);
                txtLocationOfOffense.setBackgroundResource(R.drawable.edit_text_style);
                txtOffenseDetails.setBackgroundResource(R.drawable.edit_text_style);

                if(strTypeOfOffesne.length()<1||strDateOfOffense.length()<1||strLocationOfOffense.length()<1||strOffenseDesc.length()<1){
                    if(strTypeOfOffesne.length()<1){
                        txtOffenseComiited.setBackgroundResource(R.drawable.error_text_style);
                    }

                    if(strDateOfOffense.length()<1){
                        txtDateOfOffense.setBackgroundResource(R.drawable.error_text_style);
                    }
                    if(strLocationOfOffense.length()<1){
                        txtLocationOfOffense.setBackgroundResource(R.drawable.error_text_style);
                    }
                    if(strOffenseDesc.length()<1){
                        txtOffenseDetails.setBackgroundResource(R.drawable.error_text_style);
                    }
                }else{
                    //CreateReporOffense(URL,strTypeOfOffesne,strDateOfOffense,strLocationOfOffense,strOffenseDesc);
                    createTippOff(URL,stroffenderNames,stroffenderAddress,stroffenderCell,strTypeOfOffesne,strDateOfOffense,strLocationOfOffense,strOffenseDesc,strvictimNames,strvictimAddress,strvictimCell,strcaseNo);
                    //Toast.makeText(context,"Tip off has been submitted, Thank you for caring about South Africa by providing a tip-off.",Toast.LENGTH_LONG).show();
                    txtOffenseComiited.setText("");
                    txtDateOfOffense.setText("");
                    txtLocationOfOffense.setText("");
                    txtOffenseDetails.setText("");
                    startActivity(new Intent(TipOffActivity.this, MainActivity.class));
                }




            }
        });

        inboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TipOffActivity.this, InboxActivity.class));
            }
        });
        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TipOffActivity.this, NotificationActivity.class));
            }
        });
        setSupportActionBar(Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);
    }

    //create message
    public void createTippOff(final String url, final String strOffenderName,final String strOffenderAddress,final String strOffenderCell,final String strOffenseComiited, final String strDateOfOffense, final String strLocationOfOffense, final String strOffenseDetails, final String strVictimNames, final String strVictimAddress,final String strVictimCellNumber, final String strCaseNo)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,"Tip off has been submitted, Thank you for caring about South Africa by providing a tip-off.",Toast.LENGTH_LONG).show();
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

                Map<String,String > params = new HashMap<String,String>();
                params.put("AuthDetail.UserName",UserProfile.Username);
                params.put("AuthDetail.Role",UserProfile.Role);
                params.put("AuthDetail.DeviceId", Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID));

                params.put("OffenderNames", strOffenderName);
                params.put("OffenderAddress",strOffenderAddress);
                params.put("OffenderCell", strOffenderCell);
                params.put("OffenceCommited", strOffenseComiited);

                params.put("DateOfOffence",strDateOfOffense);
                params.put("LocationOffence", strLocationOfOffense);
                params.put("OffenceDetail", strOffenseDetails);
                params.put("VictimNames", strVictimNames);
                params.put("VictimAddress",strVictimAddress);
                params.put("VictimCell", strVictimCellNumber);

                params.put("CaseNo", strCaseNo);
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

        txtDateOfOffense.setText(sdf.format(myCalendar.getTime()));
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

