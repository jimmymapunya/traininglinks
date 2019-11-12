package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.BookingDetailsAdapter;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.BookingDetails;

import java.util.ArrayList;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import static mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R.id.listview;


public class CaseListActivity extends AppCompatActivity {

    private Toolbar Toolbar;
    private Context context = this;
    BookingDetailsAdapter adapter;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;

    ArrayList<BookingDetails> bookingDetails;
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_list);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(DashboardActivity.notificationCount);
        inboxCountIcon.setText(DashboardActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("My Bookings");

        lv = findViewById(listview);
        bookingDetails = new ArrayList<>();

        bookingDetails.add(new BookingDetails("Mari Rakolota","English","Rate: R350","2019/08/15"));
        bookingDetails.add(new BookingDetails("Johannes Ramo","Mathematics","Rate: R1500","2019/09/02"));
        bookingDetails.add(new BookingDetails("Mari Rakolota","English","Rate: R350","2019/10/15"));


        adapter = new BookingDetailsAdapter(context, bookingDetails);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        inboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaseListActivity.this, InboxActivity.class));
            }
        });
        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaseListActivity.this, NotificationActivity.class));
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


