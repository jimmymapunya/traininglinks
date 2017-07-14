package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;


import java.util.ArrayList;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.NotificationAdapter;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.NotificationInfo;

public class NotificationActivity extends AppCompatActivity {
    private Toolbar Toolbar;
    Context context = this;
    private ListView lv;

    ArrayList<NotificationInfo> notifications;
    NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Notification");

        lv = (ListView) findViewById(R.id.listview);

        notifications = new ArrayList<>();
        notifications.add(new NotificationInfo(MainActivity.notificationDescription,MainActivity.notificationDate));
        adapter = new NotificationAdapter(context,notifications);
        lv.setAdapter(adapter);


        setSupportActionBar(Toolbar);
        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);



    }
}
