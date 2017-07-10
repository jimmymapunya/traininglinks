package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class NotificationsActivity extends AppCompatActivity {

    private Toolbar Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //Test Message
        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.applicationBar);

        setSupportActionBar(Toolbar);

        /*Back icon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_notif_drawer, (DrawerLayout) findViewById(R.id.drawer_notif_layout), Toolbar);

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
            startActivity(new Intent(this, NotificationsActivity.class));

        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }
}
