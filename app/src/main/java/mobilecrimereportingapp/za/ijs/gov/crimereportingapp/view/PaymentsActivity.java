package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class PaymentsActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;
    private FrameLayout notificationLayout, inboxLayout;
    private TextView notificationCountIcon, inboxCountIcon;
    private Button btnClose;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /*Icons with number init and setup*/

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);
        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);
        notificationCountIcon.setText(DashboardActivity.notificationCount);
        inboxCountIcon.setText(DashboardActivity.inboxCount);
        btnClose = (Button) findViewById(R.id.btnClose);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

            }
        });


        /*Toolbar instantiation and setup*/
        Toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Payments");
        setSupportActionBar(Toolbar);

        /*Back for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Navigation fragment init and setup*/
        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);
        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);
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
            //Toast.makeText(this,"You are already on the profile screen",Toast.LENGTH_LONG).show();
        }
        return false;

    }
}

