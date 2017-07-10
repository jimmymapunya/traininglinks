package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.CaseDetails;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.StatusDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CaseListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_list);

        ArrayList<StatusDetails> statusDetailsList = new ArrayList<>();
        statusDetailsList.add(new StatusDetails("02/2017/25", "Investigate", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList.add(new StatusDetails("02/2017/25", "Arrest", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList.add(new StatusDetails("02/2017/25", "Bail", true, "21-08-2017", "Johannesburg Magistrate Court", "01-05-2017"));

        ArrayList<CaseDetails> listdao = new ArrayList<>();
        listdao.add(new CaseDetails("02/2017/25", "Adam Mkhabele", "Lindiwe Maponya", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList));
        listdao.add(new CaseDetails("02/2017/25", "Adam Mkhabele", "Lindiwe Maponya", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList));
        listdao.add(new CaseDetails("02/2017/25", "Adam Mkhabele", "Lindiwe Maponya", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList));
        listdao.add(new CaseDetails("02/2017/25", "Adam Mkhabele", "Lindiwe Maponya", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList));
        listdao.add(new CaseDetails("02/2017/25", "Adam Mkhabele", "Lindiwe Maponya", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList));

        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < listdao.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();

            int status_last_index = (listdao.get(i).getStatus().size()) - 1;
            hm.put("caseNo", listdao.get(i).getCaseNo());
            hm.put("caseContent", "Crime: " + listdao.get(i).getOffense() + "Suspect(s) " + listdao.get(i).getAccused());
            hm.put("dateCreated", listdao.get(i).getStatus().get(status_last_index).getDateCreated());
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"caseNo", "caseContent", "dateCreated"};

        // Ids of views in listview_layout
        int[] to = {R.id.txtCaseNo, R.id.txtCaseContent, R.id.txtDateCreated};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.activity_case_list_row, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);

    }
}

