package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class ProfileActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;
    private FrameLayout notificationLayout, inboxLayout;
    private TextView notificationCountIcon, inboxCountIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        /*Icons with number init and setup*/

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);
        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);
        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar instantiation and setup*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("User Profile");
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
            Toast.makeText(this,"You are already on the profile screen",Toast.LENGTH_LONG).show();
        }
        return false;

    }
}
