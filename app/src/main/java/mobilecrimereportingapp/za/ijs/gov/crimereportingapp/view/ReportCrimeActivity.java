package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.Adapter.StudentAdapter;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.StudentModel;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.UserProfile;

/**
 * Created by MaribolleR on 2017/07/01.
 * Purpose: Crime reporting activity for the Crime reporting app.
 */

public class ReportCrimeActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;
    private Context context = this;

    private static final String URL_case = "http://innovationmessagehub.azurewebsites.net//api/MessageHub/CreateCaseDetail";

    private Spinner  spinnerInjuries, spinnerSceneItems, spinnerWeapons, spinnerRacialGroup,
            spinnerGender, spinnerAgeGroup, spinnerFacialIdentikit;

   // private SearchableSpinner searchableSpinner;

    private ArrayAdapter<CharSequence> spinnerKnowOffenderAdapter, spinnerInjuriesAdapter, spinnerSceneItemsAdapter, spinnerWeaponsAdapter, spinnerFirstAccountAdapter,
            spinnerWitnessesAdapter, spinnerRacialGroupAdapter, spinnerGenderAdapter, spinnerAgeGroupAdapter, spinnerFacialIdentikitAdapter;

    private EditText txtFirstAccountAddress, txtFirstAccountContact, txtFirstAccountName, txtBeforeCrime, txtDuringCrime, txtAfterCrime, txtInjuries, txtCrimeSurroundings, txtOffenderName, txtOffenderContact, txtOffenderAddress, txtTattoos,
            txtAppearance, txtWitnessName, txtWitnessContact, txtWitnessAddress, txtObjectsUsed, txtSceneItems;

    private Button btnAddOffenderInfo, btnAddWitnessInfo, btnSubmit;
    private TextView notificationCountIcon, inboxCountIcon, lblWitnessName, lblWitnessContact, lblWitnessAddress;
    private LinearLayout OffenderDetailsLayout, doneWitnessDetails;
    private FrameLayout notificationLayout, inboxLayout;
    private CheckBox checkBoxConfirmation;

    String device_id, username, role;
    private ListView lv;
    private Button btnBook;
    ArrayList<StudentModel> student;
    StudentAdapter adapter;

    public static boolean isVictim;
    private boolean isInjuries, isObjectsUsed, isRemovedItems, isKnowOffender, isIdentifyFacialIdentiKit, isToldAnyone, isPossibleWitnesses, isConfirmed;

    private String beforeCrime, duringCrime, afterCrime, injuriesDescript, objectsUsedDescript, removedItemsDescript, surroundingsDescript,
            offenderName, offenderContact, offenderAddress, offenderRace, offenderGender, offenderAge, tattoosDescript, appearanceDescript,
            firstAccountName, firstAccountContact, firstAccountAddress, witnessName, witnessContact, witnessAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_crime);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Initialisation();

        inboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InboxActivity.class));
            }
        });

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });



        lv.setOnItemClickListener((parent, view, position, id) -> {
            view.getFocusables(position);
            view.setSelected(true);

            btnBook.setOnClickListener(v -> {

                StudentModel std = (StudentModel) parent.getItemAtPosition(position);
                String name= std.getFullName();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Booking");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you wanna book Tutor "+name+"?")
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, id1) ->
                                Toast.makeText(context, "Request Submitted", Toast.LENGTH_LONG).show())

                        .setNegativeButton("CANCEL", (dialog, id12) -> {

                            Toast.makeText(context, "Canceled ", Toast.LENGTH_LONG).show();

                            dialog.cancel();
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            });
        });


    }

    public void reportCrime(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Crime Report has been submitted.", Toast.LENGTH_LONG).show();
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
                }) {
            @Override
            protected Map<String, String> getParams() {

                //Values to post
                device_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                username = UserProfile.Username;
                role = "admin";

                Map<String, String> params = new HashMap<>();

                params.put("AuthDetail.UserName", username);
                params.put("AuthDetail.Role", role);
                params.put("AuthDetail.DeviceId", device_id);
                params.put("Accused", "");
                params.put("CaseDesc", "");
                params.put("Offence", "");
                params.put("Victim", "");
                params.put("Location", surroundingsDescript);
                params.put("BeforeCrime", beforeCrime);
                params.put("DuringCrime", duringCrime);
                params.put("Injuries", injuriesDescript);
                params.put("CrimeSurroundings", surroundingsDescript);
                params.put("AfterCrime", afterCrime);
                params.put("OffenderName", offenderName);
                params.put("OffenderContact", offenderContact);
                params.put("OffenderAddress", offenderAddress);
                params.put("DescriptionTattoos", tattoosDescript);
                params.put("Appearance", appearanceDescript);
                params.put("WitnessName", witnessName);
                params.put("WitnessContact", witnessContact);
                params.put("WitnessAddress", witnessAddress);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Initialisation() {
        /*Icons with number init and setup*/

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);
        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);
        notificationCountIcon.setText(DashboardActivity.notificationCount);
        inboxCountIcon.setText(DashboardActivity.inboxCount);

        btnBook = findViewById(R.id.btnBook);
        lv = findViewById(R.id.lv_list);
        student = new ArrayList<>();

        student.add(new StudentModel("Mari Rakolota, Rate: R1000,00"));
        student.add(new StudentModel("Jimmy Mapunya, Rate: R500,00"));
        student.add(new StudentModel("John Willaims, Rate: R750.00"));
        student.add(new StudentModel("Elizabeth Keen, Rate: R489,00"));
        student.add(new StudentModel("Lucia Malokela, Rate: R2000, 00"));
        student.add(new StudentModel("Anna Mabuza, Rate: R349, 00"));


        adapter = new StudentAdapter(context, student);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /*Toolbar instantiation and setup*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Book");
        setSupportActionBar(Toolbar);

        /*Back for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Navigation fragment init and setup*/
        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);
        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);










        /* Create ArrayAdapters using the string array and a default spinner layout*/
        spinnerKnowOffenderAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerInjuriesAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerSceneItemsAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerWeaponsAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerFirstAccountAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerWitnessesAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);

        /* Specify the layout to use when the list of choices appears*/
        spinnerKnowOffenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInjuriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSceneItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeaponsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirstAccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWitnessesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /* Apply the adapter to the spinner*/
//        spinnerKnowOffender.setAdapter(spinnerKnowOffenderAdapter);


        /*Dynamic linear layout offender and witness details*/
        OffenderDetailsLayout = (LinearLayout) findViewById(R.id.OffenderDetailsLayout);
        //doneWitnessDetails = (LinearLayout) findViewById(R.id.doneWitnessDetails);

        /*Button to submit*/
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        /*Checkbox to confirm details reported are correct*/
        checkBoxConfirmation = (CheckBox) findViewById(R.id.checkBoxConfirmation);

        isInjuries = false;
        isObjectsUsed = false;
        isRemovedItems = false;
        isKnowOffender = false;
        isToldAnyone = false;
        isPossibleWitnesses = false;

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
        } else if(id == R.id.action_profile){
            startActivity(new Intent(context,ProfileActivity.class));
       }
        return false;

    }
}
