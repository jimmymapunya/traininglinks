package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.HashMap;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.UserProfile;

/**
 * Created by MaribolleR on 2017/07/01.
 * Purpose: Crime reporting activity for the Crime reporting app.
 */

public class ReportCrimeActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;
    private Context context = this;

    private static final String URL_case = "http://innovationmessagehub.azurewebsites.net//api/MessageHub/CreateCaseDetail";

    private Spinner spinnerKnowOffender, spinnerInjuries, spinnerSceneItems, spinnerWeapons, spinnerFirstAccount, spinnerWitnesses, spinnerRacialGroup,
            spinnerGender, spinnerAgeGroup, spinnerFacialIdentikit;

    private SearchableSpinner searchableSpinner;

    private ArrayAdapter<CharSequence> spinnerKnowOffenderAdapter, spinnerInjuriesAdapter, spinnerSceneItemsAdapter, spinnerWeaponsAdapter, spinnerFirstAccountAdapter,
            spinnerWitnessesAdapter, spinnerRacialGroupAdapter, spinnerGenderAdapter, spinnerAgeGroupAdapter, spinnerFacialIdentikitAdapter;

    private EditText txtFirstAccountAddress, txtFirstAccountContact, txtFirstAccountName, txtBeforeCrime, txtDuringCrime, txtAfterCrime, txtInjuries, txtCrimeSurroundings, txtOffenderName, txtOffenderContact, txtOffenderAddress, txtTattoos,
            txtAppearance, txtWitnessName, txtWitnessContact, txtWitnessAddress, txtObjectsUsed, txtSceneItems;

    private Button btnAddOffenderInfo, btnAddWitnessInfo, btnSubmit;
    private TextView notificationCountIcon, inboxCountIcon, lblWitnessName, lblWitnessContact, lblWitnessAddress;
    private LinearLayout OffenderDetailsLayout, doneWitnessDetails, editOffenderDetails, editFirstAccountDetails, editWitnessDetails;
    private FrameLayout notificationLayout, inboxLayout;
    private CheckBox checkBoxConfirmation;

    String device_id, username, role;

    public static boolean isVictim;
    private boolean isInjuries, isObjectsUsed, isRemovedItems, isKnowOffender, isIdentifyFacialIdentiKit, isToldAnyone, isPossibleWitnesses, isConfirmed;

    private String beforeCrime, duringCrime, afterCrime, injuriesDescript, objectsUsedDescript, removedItemsDescript, surroundingsDescript,
            offenderName, offenderContact, offenderAddress, offenderRace, offenderGender, offenderAge, tattoosDescript, appearanceDescript,
            firstAccountName, firstAccountContact, firstAccountAddress, witnessName, witnessContact, witnessAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_crime);

        Initialisation();

        /*Button and Spinner listeners*/
        btnAddOffenderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = LayoutInflater.from(context).inflate(R.layout.custom_witness_done, null);

                /*TextView components initialisation*/
                lblWitnessName = (TextView) view.findViewById(R.id.lblWitnessName);
                lblWitnessContact = (TextView) view.findViewById(R.id.lblWitnessContact);
                lblWitnessAddress = (TextView) view.findViewById(R.id.lblWitnessAddress);

                lblWitnessName.setText(txtOffenderName.getText().toString());
                lblWitnessContact.setText(txtOffenderContact.getText().toString());
                lblWitnessAddress.setText(txtOffenderAddress.getText().toString());

                txtOffenderName.setText("");
                txtOffenderContact.setText("");
                txtOffenderAddress.setText("");
                txtTattoos.setText("");
                txtAppearance.setText("");

                OffenderDetailsLayout.addView(view);
            }
        });

        btnAddWitnessInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = LayoutInflater.from(context).inflate(R.layout.custom_witness_done, null);

                /*TextView components initialisation*/
                lblWitnessName = (TextView) view.findViewById(R.id.lblWitnessName);
                lblWitnessContact = (TextView) view.findViewById(R.id.lblWitnessContact);
                lblWitnessAddress = (TextView) view.findViewById(R.id.lblWitnessAddress);

                lblWitnessName.setText(txtWitnessName.getText().toString());
                lblWitnessContact.setText(txtWitnessContact.getText().toString());
                lblWitnessAddress.setText(txtWitnessAddress.getText().toString());

                txtWitnessName.setText("");
                txtWitnessContact.setText("");
                txtWitnessAddress.setText("");

                doneWitnessDetails.addView(view);

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                beforeCrime = txtBeforeCrime.getText().toString();
                duringCrime = txtDuringCrime.getText().toString();
                afterCrime = txtBeforeCrime.getText().toString();
                surroundingsDescript = txtCrimeSurroundings.getText().toString();

                if (isInjuries) injuriesDescript = txtInjuries.getText().toString();
                if (isObjectsUsed) objectsUsedDescript = txtObjectsUsed.getText().toString();
                if (isRemovedItems) removedItemsDescript = txtSceneItems.getText().toString();
                if (isKnowOffender) {
                    offenderName = txtOffenderName.getText().toString();
                    offenderContact = txtOffenderContact.getText().toString();
                    offenderAddress = txtOffenderAddress.getText().toString();
                    tattoosDescript = txtTattoos.getText().toString();
                    appearanceDescript = txtAppearance.getText().toString();
                }
                if (isPossibleWitnesses) {
                    witnessName = txtWitnessName.getText().toString();
                    witnessContact = txtWitnessContact.getText().toString();
                    witnessAddress = txtWitnessAddress.getText().toString();
                }
                    if(checkBoxConfirmation.isChecked()){
                        reportCrime(URL_case);
                    } else{
                        Toast.makeText(context, "Please confirm that all details provided are correct by checking the confirmation",Toast.LENGTH_LONG).show();
                    }

            }
        });

        spinnerInjuries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                if (itemSelected.equals("Yes")) {
                    txtInjuries.setVisibility(View.VISIBLE);
                    isInjuries = true;
                } else {
                    txtInjuries.setVisibility(View.INVISIBLE);
                    isInjuries = false;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerWeapons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                if (itemSelected.equals("Yes")) {
                    txtObjectsUsed.setVisibility(View.VISIBLE);
                    isObjectsUsed = true;
                } else {
                    txtObjectsUsed.setVisibility(View.INVISIBLE);
                    isObjectsUsed = false;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerSceneItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                if (itemSelected.equals("Yes")) {
                    txtSceneItems.setVisibility(View.VISIBLE);
                    isRemovedItems = true;
                } else {
                    txtSceneItems.setVisibility(View.INVISIBLE);
                    isRemovedItems = false;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerKnowOffender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                if (itemSelected.equals("Yes")) {
                    editOffenderDetails.setVisibility(View.VISIBLE);
                    btnAddOffenderInfo.setClickable(true);
                    isKnowOffender = true;
                } else {
                    editOffenderDetails.setVisibility(View.INVISIBLE);
                    btnAddOffenderInfo.setClickable(false);
                    isKnowOffender = false;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerAgeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();

                if (!itemSelected.equals("Select age")) {
                    offenderAge = itemSelected;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        spinnerRacialGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();

                if (!itemSelected.equals("Select race")) {
                    offenderRace = itemSelected;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();

                if (!itemSelected.equals("Select sex")) {
                    offenderGender = itemSelected;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerFacialIdentikit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();

                if (!itemSelected.equals("Select")) {
                    isIdentifyFacialIdentiKit = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        spinnerFirstAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                if (itemSelected.equals("Yes")) {
                    editFirstAccountDetails.setVisibility(View.VISIBLE);
                    isToldAnyone = true;

                } else {
                    editFirstAccountDetails.setVisibility(View.INVISIBLE);
                    isToldAnyone = false;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerWitnesses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                if (itemSelected.equals("Yes")) {
                    editWitnessDetails.setVisibility(View.VISIBLE);
                    btnAddWitnessInfo.setClickable(true);
                    isPossibleWitnesses = true;

                } else {
                    editWitnessDetails.setVisibility(View.INVISIBLE);
                    btnAddWitnessInfo.setClickable(false);
                    isPossibleWitnesses = false;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
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
                        Toast.makeText(context, "An error has occurred.", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                //Values to post
                device_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                username = UserProfile.Username;
                role = "admin";

                Map<String, String> params = new HashMap<String, String>();

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
        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar instantiation and setup*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Report Crime");
        setSupportActionBar(Toolbar);

        /*Back for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Navigation fragment init and setup*/
        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);
        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        /*EditText components initialisation*/
        txtBeforeCrime = (EditText) findViewById(R.id.txtBeforeCrime);
        txtDuringCrime = (EditText) findViewById(R.id.txtDuringCrime);
        txtAfterCrime = (EditText) findViewById(R.id.txtAfterCrime);
        txtInjuries = (EditText) findViewById(R.id.txtInjuries);
        txtInjuries.setVisibility(View.INVISIBLE);
        txtObjectsUsed = (EditText) findViewById(R.id.txtObjectsUsed);
        txtObjectsUsed.setVisibility(View.INVISIBLE);
        txtSceneItems = (EditText) findViewById(R.id.txtSceneItems);
        txtSceneItems.setVisibility(View.INVISIBLE);
        txtCrimeSurroundings = (EditText) findViewById(R.id.txtCrimeSurroundings);
        txtOffenderName = (EditText) findViewById(R.id.txtOffenderName);
        txtOffenderContact = (EditText) findViewById(R.id.txtOffenderContact);
        txtOffenderAddress = (EditText) findViewById(R.id.txtOffenderAddress);
        txtTattoos = (EditText) findViewById(R.id.txtTattoos);
        txtAppearance = (EditText) findViewById(R.id.txtAppearance);
        txtFirstAccountName = (EditText) findViewById(R.id.txtFirstAccountName);
        txtFirstAccountContact = (EditText) findViewById(R.id.txtFirstAccountContact);
        txtFirstAccountAddress = (EditText) findViewById(R.id.txtFirstAccountAddress);
        txtWitnessName = (EditText) findViewById(R.id.txtWitnessName);
        txtWitnessContact = (EditText) findViewById(R.id.txtWitnessContact);
        txtWitnessAddress = (EditText) findViewById(R.id.txtWitnessAddress);

        /*Starting code for the spinners*/
        searchableSpinner = (SearchableSpinner) findViewById(R.id.spinner);

        spinnerKnowOffender = (Spinner) findViewById(R.id.spinnerKnowOffender);
        spinnerInjuries = (Spinner) findViewById(R.id.spinnerInjuries);
        spinnerSceneItems = (Spinner) findViewById(R.id.spinnerSceneItems);
        spinnerWeapons = (Spinner) findViewById(R.id.spinnerObjectsUsed);
        spinnerFirstAccount = (Spinner) findViewById(R.id.spinnerFirstAccount);
        spinnerWitnesses = (Spinner) findViewById(R.id.spinnerWitnesses);

        spinnerRacialGroup = (Spinner) findViewById(R.id.spinnerRacialGroup);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerAgeGroup = (Spinner) findViewById(R.id.spinnerAgeGroup);
        spinnerFacialIdentikit = (Spinner) findViewById(R.id.spinnerFacialIdentikit);

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

        spinnerRacialGroupAdapter = ArrayAdapter.createFromResource(this,
                R.array.racial_group, android.R.layout.simple_spinner_item);
        spinnerGenderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        spinnerAgeGroupAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_group, android.R.layout.simple_spinner_item);
        spinnerFacialIdentikitAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);

        /* Specify the layout to use when the list of choices appears*/
        spinnerKnowOffenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInjuriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSceneItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeaponsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirstAccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWitnessesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerRacialGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgeGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFacialIdentikitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /* Apply the adapter to the spinner*/
        spinnerKnowOffender.setAdapter(spinnerKnowOffenderAdapter);
        spinnerInjuries.setAdapter(spinnerInjuriesAdapter);
        spinnerSceneItems.setAdapter(spinnerSceneItemsAdapter);
        spinnerWeapons.setAdapter(spinnerWeaponsAdapter);
        spinnerFirstAccount.setAdapter(spinnerFirstAccountAdapter);
        spinnerWitnesses.setAdapter(spinnerWitnessesAdapter);

        spinnerRacialGroup.setAdapter(spinnerRacialGroupAdapter);
        spinnerGender.setAdapter(spinnerGenderAdapter);
        spinnerAgeGroup.setAdapter(spinnerAgeGroupAdapter);
        spinnerFacialIdentikit.setAdapter(spinnerFacialIdentikitAdapter);

        /*Dynamic linear layout offender and witness details*/
        OffenderDetailsLayout = (LinearLayout) findViewById(R.id.OffenderDetailsLayout);
        doneWitnessDetails = (LinearLayout) findViewById(R.id.doneWitnessDetails);
        editOffenderDetails = (LinearLayout) findViewById(R.id.editOffenderDetails);
        editOffenderDetails.setVisibility(View.INVISIBLE);
        editFirstAccountDetails = (LinearLayout) findViewById(R.id.editFirstAccountDetails);
        editFirstAccountDetails.setVisibility(View.INVISIBLE);
        editWitnessDetails = (LinearLayout) findViewById(R.id.editWitnessDetails);
        editWitnessDetails.setVisibility(View.INVISIBLE);

        /*Button to add the dynamic linear layout above*/
        btnAddOffenderInfo = (Button) findViewById(R.id.btnAddOffenderInfo);
        btnAddOffenderInfo.setClickable(false);
        btnAddWitnessInfo = (Button) findViewById(R.id.btnAddWitnessInfo);
        btnAddWitnessInfo.setClickable(false);
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
        } else if (id == R.id.notifications) {
            //startActivity(new Intent(this, NotificationsActivity.class));

        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }
}
