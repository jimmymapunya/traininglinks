package mobilecrimereportingapp.za.ijs.gov.crimereportingapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFrag extends Fragment {

    /*File name for shared preferences and user knowledge of drawer*/
    public static final String PREF_FILENAME = "preferences";
    public static final String USER_KNOWS_OF_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;

    private NavigationAdapter navigationAdapter;

    /*Data members to be tracked of if the drawer has been opened before.*/
    private boolean userKnowsAboutDrawer;
    private boolean fromSavedInstance;

    /*Declaration of view for the drawer*/
    private View fragView;
    /*Recycler view declaration*/
    private RecyclerView recyclerView;

    public NavigationDrawerFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userKnowsAboutDrawer = Boolean.valueOf(readFromPreferences(getActivity(),USER_KNOWS_OF_DRAWER, "false"));

        if(savedInstanceState!= null){
            fromSavedInstance = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        /*Instantiate the recycler view*/
        recyclerView = (RecyclerView) view.findViewById(R.id.navigationList);

        /*Instantiate the adapter*/
        navigationAdapter = new NavigationAdapter(getActivity(),getData());

        /*Set the adapter and layout manager*/
        recyclerView.setAdapter(navigationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public static List<NavigationInfo> getData(){
        List<NavigationInfo> navigationInfos = new ArrayList<>();

        int icons [] = {R.mipmap.ic_book,R.mipmap.ic_bug,R.mipmap.ic_court, R.mipmap.ic_location, R.mipmap.ic_feedback, R.mipmap.ic_compose};
        String itemTitles [] = {"Report a crime","Report Fraud & Corruption","My Cases" , "Court Finder", "Provide Feedback", "Compose Message" };

        for(int i = 0; i < icons.length && i < itemTitles.length; i++){
            NavigationInfo currentNavInfo = new NavigationInfo();

            currentNavInfo.setIconID(icons[i]);
            currentNavInfo.setItemName(itemTitles[i]);

            navigationInfos.add(currentNavInfo);
        }
        return navigationInfos;
    }

    public void setUpDrawer(int fragID, DrawerLayout drawerLayout, final Toolbar toolbar) {
       /*Attach navigation fragment view*/
        fragView = getActivity().findViewById(fragID);

        this.drawerLayout = drawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open_drawer,R.string.close_drawer ){

            @Override
            public void onDrawerOpened(View drawerView) {
               super.onDrawerOpened(drawerView);

                if(!userKnowsAboutDrawer){
                    userKnowsAboutDrawer = true;

                    /*Save setting that drawer has been seen to shared preference*/
                    saveToPreferences(getActivity(),USER_KNOWS_OF_DRAWER,userKnowsAboutDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                /*Check so that the slide does not completely dark*/
                if(slideOffset<0.6){
                    /*Add animation for the slider to dim*/
                    toolbar.setAlpha(1-slideOffset);
                }

            }
        };

       /*Check if drawer has been seen before or the activity is from a saved instance*/
        if(!userKnowsAboutDrawer && !fromSavedInstance){
             /*Opening the drawer*/
            drawerLayout.openDrawer(fragView);
        }

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        /*Synchronise the state of the action bar*/
        drawerLayout.post(new Runnable(){

            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });
    }

    /*Methods for writing and reading shared preferences*/

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();

    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILENAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);

    }
}
