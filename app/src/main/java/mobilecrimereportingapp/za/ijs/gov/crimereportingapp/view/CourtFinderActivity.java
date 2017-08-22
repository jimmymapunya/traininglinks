package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.GeneralCourtInfo;

import static mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R.layout.list_item;


/**
 * Created by ThatoM on 2017/07/12.
 * This is the class for court finder
 */

public class CourtFinderActivity extends AppCompatActivity implements OnMapReadyCallback {
    // URL of object to be parsed
    String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;


    private GoogleMap mMap;
    private Toolbar Toolbar;
    private Button btnGo;
    private EditText txtTo, txtFrom;

    private double courtLatitude;
    private double courtLongitude;
    private String courtTitle;
    private String courtType;

    private List<GeneralCourtInfo> courtList  = new ArrayList<>();
    private GeneralCourtInfo court;

    Context context=this;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;
    ProgressDialog progressDialog;


    String strTxtTo;
    String strTxtFrom;

    String [] items = {"mehul joisar","amit mishra","amitabh","Aamir khan","jesica","katrina"};
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_finder);

        initList();

        btnGo = (Button) findViewById(R.id.btnGo);
        txtTo = (EditText) findViewById(R.id.txtTo);
        txtFrom = (EditText) findViewById(R.id.txtFrom);

        //EditText [] toFromArr = new EditText[]{txtTo,txtFrom};

         //for(int i=0; i<=1;i++){
             //implementation of list search form from edit text
        txtFrom.addTextChangedListener(new TextWatcher(){

                 @Override
                 public void onTextChanged(CharSequence s, int start, int before, int count) {
                     if(s.length()>=1){
                         //show listview
                         listView.setVisibility(View.VISIBLE);
                         CourtFinderActivity.this.adapter.getFilter().filter(s);
                     }else {
                         //hide listview
                         listView.setVisibility(View.INVISIBLE);
                     }

                 }

                 @Override
                 public void beforeTextChanged(CharSequence s, int start, int count, int	after) {
                 }

                 @Override

                 public void afterTextChanged(Editable s) {

                 }

             });
        //implementation of list search form from edit text
        txtTo.addTextChangedListener(new TextWatcher(){

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){

                    //show listview
                    listView.setVisibility(View.VISIBLE);
                    CourtFinderActivity.this.adapter.getFilter().filter(s);
                }else {
                    //hide listview
                    listView.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int	after) {
            }

            @Override

            public void afterTextChanged(Editable s) {

            }

        });
         //}



        //progressDialog = new ProgressDialog(context);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Court Finder SA");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        setSupportActionBar(Toolbar);
        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

    }


    public void initList(){

        // Casts results into the TextView found within the main layout XML with id jsonData
        listView = (ListView) findViewById(R.id.listview);
        //ebable listview text filtering
        listView.setTextFilterEnabled(true);
        listItems = new ArrayList<String>();
        adapter  = new ArrayAdapter<String>(CourtFinderActivity.this, list_item, R.id.txtitem, listItems);
        listView.setAdapter(adapter);

        //hide list view
        //listView.setVisibility(View.INVISIBLE);

        //on listview item selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on edit text focused drop the list item there
                EditText [] toFromArr = new EditText[]{txtFrom, txtTo};

                for(int i=0; i<=1;i++){
                    toFromArr[i].setOnFocusChangeListener(focusListener);
                    if(toFromArr[0].hasFocus()){

                        // Get the selected item text from ListView
                        String selectedItem = (String) parent.getItemAtPosition(position);

                        // Display the selected item text on TextView
                        txtFrom.setText(selectedItem);
                        //then hide lisview
                        listView.setVisibility(View.INVISIBLE);

                    }if(toFromArr[1].hasFocus()){

                        //listView.setVisibility(View.VISIBLE);
                        // Get the selected item text from ListView
                        String selectedItem = (String) parent.getItemAtPosition(position);

                        // Display the selected item text on TextView
                        txtTo.setText(selectedItem);
                        //then hide lisview
                        listView.setVisibility(View.INVISIBLE);

                    }
                }
            }
        });

        try
        {

            /*Get the JSON content using loadJSONFromAsset file*/
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            /*Get the JSON array of the codes within the loaded asset*/
            JSONArray jsonArrayCodes = jsonObject.getJSONArray("Codes");

            for (int i = 0; i < jsonArrayCodes.length(); i++)
            {
                /*Get object for each of the array codes*/
                JSONObject jsonInsideCodes = jsonArrayCodes.getJSONObject(i);

                courtTitle = jsonInsideCodes.getString("Description");
                JSONArray additionalAttributes = jsonInsideCodes.getJSONArray("AdditionalAttributes");

                court = new GeneralCourtInfo(courtTitle);
                courtList.add(court);

                for (int x = 0; x < additionalAttributes.length(); x++) {

                    JSONObject jsonInsideAdditionalAttributes = additionalAttributes.getJSONObject(x);
                    String name = jsonInsideAdditionalAttributes.getString("Name");

                    /**Check to ensure we dealing with Coordinate lat and long
                     if(name.equalsIgnoreCase("Facility Type")){
                     String fType = jsonInsideAdditionalAttributes.getString("Value");
                     listItems.add(courtTitle+" "+fType);

                     }*/
                    if(name.equalsIgnoreCase("GPS Coordinate Lattitude"))
                    {
                        String value = jsonInsideAdditionalAttributes.getString("Value");

                        /*Check if value contains data and not empty*/
                        if(!value.equals(""))
                        {
                            courtLatitude = Double.parseDouble(value);
                        }
                    }
                    if(name.equalsIgnoreCase("GPS Coordinate Longitude"))
                    {
                        String value = jsonInsideAdditionalAttributes.getString("Value");

                        /*Check if value contains data and not empty*/
                        if(!value.equals(""))
                        {
                            courtLongitude = Double.parseDouble(value);
                        }
                    }

                    if(name.equalsIgnoreCase("Facility Type"))
                    {
                        courtType = jsonInsideAdditionalAttributes.getString("Value");
                        //String fType = jsonInsideAdditionalAttributes.getString("Value");
                        listItems.add(wordFirstCap(courtTitle.toLowerCase())+" "+courtType);//+" [ "+courtLatitude+", "+courtLongitude+" ]"

                    }
                }

                //Calculate distance between courts and current location

                /**GPSTracker gps = new GPSTracker(this);
                 Location currentLocation = new Location("");
                 currentLocation.setLatitude(gps.getLatitude());
                 currentLocation.setLongitude(gps.getLongitude());

                 LatLng CurrentLocation = new LatLng(gps.getLatitude(), gps.getLongitude());
                 final Marker markerCurrentLocation = mMap.addMarker(new MarkerOptions().position(CurrentLocation).title("You are here"));
                 mMap.addMarker(new MarkerOptions().position(location).title(courtTitle));
                 mMap.moveCamera(CameraUpdateFactory.newLatLng(CurrentLocation));
                 mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );
                 //markerCurrentLocation.setSnippet(courtType);
                 markerCurrentLocation.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                 Location courtLocation = new Location("");
                 courtLocation.setLatitude(courtLatitude);
                 courtLocation.setLongitude(courtLongitude);

                 DecimalFormat decimalFormat = new DecimalFormat("##");

                 float distanceInMeters = currentLocation.distanceTo(courtLocation)/1000;
                 String km = decimalFormat.format(distanceInMeters);
                 int finalKM = Integer.parseInt(km);

                 if(finalKM<=50)
                 {

                 LatLng location = new LatLng(courtLatitude, courtLongitude);
                 final Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(courtTitle));
                 mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                 mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );
                 marker.setSnippet(courtType+"\n"+"Court is "+finalKM+" KM away");
                 marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                 //marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.court));
                 }*/
            }
            //progressDialog.hide();
        }
        catch (JSONException e)
        {
            progressDialog.hide();
            e.printStackTrace();
        }

    }
    /**/
    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            View focusedView;
            if (hasFocus){
                focusedView = v;
            } else {
                focusedView  = null;
            }
        }
    };

    //capitalize first letter of every word
    public String wordFirstCap(String str)
    {
        String[] words = str.trim().split(" ");
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < words.length; i++)
        {
            if(words[i].trim().length() > 0)
            {
                //Log.e("words[i].trim",""+words[i].trim().charAt(0));
                ret.append(Character.toUpperCase(words[i].trim().charAt(0)));
                ret.append(words[i].trim().substring(1));
                if(i < words.length - 1) {
                    ret.append(' ');
                }
            }
        }

        return ret.toString();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng currentLocation = new LatLng(-25.75006, 28.19121);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

        /*progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.hide();*/
        btnGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(context, OverallFeedbackActivity.class));

                strTxtFrom = txtFrom.getText().toString();
                strTxtTo = txtTo.getText().toString();
                if(!(strTxtFrom.isEmpty()) && !(strTxtTo.isEmpty())){
                    //go
                    String uri = "https://maps.google.com/maps?saddr="+ strTxtFrom +"&daddr="+ strTxtTo;
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(i);
                }
                //String uri = "https://maps.google.com/maps?saddr="+ strTxtFrom +"&daddr="+ strTxtTo;
                //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                //startActivity(i);

            }
        });


    }
}
