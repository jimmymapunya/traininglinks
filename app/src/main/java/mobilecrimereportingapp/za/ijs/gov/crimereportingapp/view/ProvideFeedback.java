package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

/**
 * Created by ThatoM on 2017/07/07.
 * This is the class for selecting the type of feedback to give
 */

public class ProvideFeedback extends AppCompatActivity {

    private Toolbar Toolbar;
    Context context = this;
    private  Button btnOverallFeedback, btnEventFeedback, btnIndividualFeedback;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //lets commit first try
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_feedback);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Provide Feedback");

        btnOverallFeedback = (Button) findViewById(R.id.btnOverallFeedback);
        btnEventFeedback = (Button) findViewById(R.id.btnEventFeedback);
        btnIndividualFeedback = (Button) findViewById(R.id.btnIndividualFeedback);


        btnOverallFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, OverallFeedbackActivity.class));
            }
        });

        btnEventFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, EventFeedbackActivity.class));
            }
        });

        btnIndividualFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, IndividualFeedbackActivity.class));
            }
        });

        setSupportActionBar(Toolbar);
        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        } else if (id == R.id.notifications) {
            //startActivity(new Intent(this, NotificationsActivity.class));

        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }


}
