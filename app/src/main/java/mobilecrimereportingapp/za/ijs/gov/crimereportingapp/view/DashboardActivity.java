package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;


public class DashboardActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private FloatingActionButton fabBookTutor, btnBookings, btnChats, btnPayments, btnCompose, btnProfile, btnFeedback ;
    private Context context;
    public static String notificationCount, inboxCount, myCaseCount;
    public static JSONArray jsonArrayNotification,jsonArrayInboxes;
    private LinearLayout bookLayout;
    TextView txtName, txtRegistrationNo;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //getWindow().setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        context = this;

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabBookTutor = findViewById(R.id.fabBookTutor);
//        btnBookings =  findViewById(R.id.btnBookings);
//        btnChats =  findViewById(R.id.btnChats);
//        btnPayments = findViewById(R.id.btnPayments);
//        btnFeedback =  findViewById(R.id.btnFeedback);
//        btnProfile = findViewById(R.id.btnProfile);
//        bookLayout = findViewById(R.id.bookLayout);
//        txtName = findViewById(R.id.txtName);
//        txtRegistrationNo = findViewById(R.id.txtRegistrationNo);
//
//        int role = getIntent().getIntExtra("role",0);
//
//        if(role==0)
//        {
//            txtName.setText("Jimmy Mapunya");
//            txtRegistrationNo.setText("Student");
//        }
//
        /*Button listeners for clickable button events*/
        int role = 1;
        fabBookTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(role==1)
                {
                    //Toast.makeText(getApplicationContext(), "Tutor Cannot Book", Toast.LENGTH_LONG).show();
                    fabBookTutor.setEnabled(false);
                    //bookLayout.setEnabled(false);
                }
                else
                {
                    startActivity(new Intent(context, ReportCrimeActivity.class));

                }

            }
        });
//
//        btnBookings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(context, CaseListActivity.class));
//
//            }
//        });
//
//        btnChats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(context, ComposeMessageActivity.class));
//
//            }
//        });
//
//        btnProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(context, ProfileActivity.class));
//            }
//        });
//
//        btnPayments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        btnFeedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(context, EventFeedbackActivity.class));
//            }
//        });
//
//        btnPayments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(context, PaymentsActivity.class));
//
//            }
//        });


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
            Toast.makeText(DashboardActivity.this,notificationCount,Toast.LENGTH_LONG).show();
            
        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }
}
