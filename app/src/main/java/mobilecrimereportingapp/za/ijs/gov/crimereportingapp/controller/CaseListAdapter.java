package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.CaseDetails;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.StatusDetails;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.CaseDetailsActivity;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.CaseListActivity;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.CourtFinderActivity;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.ProvideFeedback;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.ReportCrimeActivity;

/**
 * Created by TsundzukaniM on 07-Aug-17.
 */

public class CaseListAdapter extends ArrayAdapter<CaseDetails> {

    Context context;
    int layoutResourceId;
    ArrayList<CaseDetails> aList = null;
    CaseHolder holder = null;
    private static int index;
    private ArrayList<CaseDetails> listdao = CaseListActivity.listdao;

    public CaseListAdapter(Context context, int layoutResourceId, ArrayList<CaseDetails> aList) {
        super(context, layoutResourceId, aList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.aList = aList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        index = position -2;
        Toast.makeText(context, index+" "+position, Toast.LENGTH_SHORT).show();

        if(view == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);

            holder = new CaseHolder();
            holder.relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayoutExpand);
            holder.relativeLayout2 = (RelativeLayout) view.findViewById(R.id.relativeLayout2);
            holder.investigate=(View) view.findViewById(R.id.investigate);
            holder.arrested=(View) view.findViewById(R.id.arrested);
            holder.bail=(View) view.findViewById(R.id.bail);
            holder.trial=(View) view.findViewById(R.id.trial);
            holder.verdict=(View) view.findViewById(R.id.verdict);
            holder.aquit=(View) view.findViewById(R.id.aquit);
            holder.sentence=(View) view.findViewById(R.id.sentence);

            holder.txtinvestigate=(TextView) view.findViewById(R.id.txtInvestigate);
            holder.txtarrested=(TextView) view.findViewById(R.id.txtArrested);
            holder.txtbail=(TextView) view.findViewById(R.id.txtBail);
            holder.txttrial=(TextView) view.findViewById(R.id.txtTrial);
            holder.txtverdict=(TextView) view.findViewById(R.id.txtVerdict);
            holder.txtaquit=(TextView) view.findViewById(R.id.txtAquit);
            holder.txtsentence=(TextView) view.findViewById(R.id.txtSentence);

            holder.txtSuspects=(TextView) view.findViewById(R.id.txtSuspects);
            holder.txtCaseNo=(TextView) view.findViewById(R.id.txtCaseNo);
            holder.txtOffense=(TextView) view.findViewById(R.id.txtOffense);
            holder.txtDateCreated=(TextView) view.findViewById(R.id.txtDateCreated);

            holder.btnHeading=(Button) view.findViewById(R.id.btnHeading);
            holder.btnFindCourt=(Button) view.findViewById(R.id.btnFindCourt);
            holder.btnCaseDetails=(Button) view.findViewById(R.id.btnCaseDetails);
            holder.btnFeedback=(Button) view.findViewById(R.id.btnFeedback);
            holder.imgDropDown=(ImageView) view.findViewById(R.id.imgDropDown);
            view.setTag(holder);
        }
        else
        {
            holder = (CaseHolder) view.getTag();
        }

        CaseDetails caseDetails = aList.get(position);
        ArrayList<StatusDetails> arrStatuses = aList.get(position).getStatus();
        holder.txtCaseNo.setText("Case No : "+aList.get(position).getCaseNo());
       // holder.txtOffense.setText("Crime : ");
        holder.txtSuspects.setText("Suspect(s): "+aList.get(position).getAccused());

        int occurencesCount=0;
        //Check if the process have taken place
        String dateCreated ="";
        for(int x = 0; x<arrStatuses.size(); x++){

            //Cheking if its a current process
            if(arrStatuses.get(x).getActionDate()!=null){

                if(arrStatuses.get(x).getProcessName().equals("Investigate")){
                    holder.investigate.setBackgroundResource(R.drawable.success_status);

                } else if(arrStatuses.get(x).getProcessName().equals("Arrest")){
                    holder.arrested.setBackgroundResource(R.drawable.success_status);

                }  else if(arrStatuses.get(x).getProcessName().equals("Bail Hearing")){
                    holder.bail.setBackgroundResource(R.drawable.success_status);

                }else if(arrStatuses.get(x).getProcessName().equals("Trial")){
                    holder.trial.setBackgroundResource(R.drawable.success_status);

                }else if(arrStatuses.get(x).getProcessName().equals("Verdict")){
                    holder.verdict.setBackgroundResource(R.drawable.success_status);

                }else if(arrStatuses.get(x).getProcessName().equals("Aquit")){
                    holder.aquit.setBackgroundResource(R.drawable.success_status);

                }else{
                    holder.sentence.setBackgroundResource(R.drawable.success_status);

                }

            }
            //Checking if have occured
            if(arrStatuses.get(x).isIsCurrent()){

                occurencesCount++;
                dateCreated = arrStatuses.get(x).getActionDate();

                    holder.txtinvestigate.setTextColor(Color.parseColor("#808080"));
                    holder.txtarrested.setTextColor(Color.parseColor("#808080"));
                    holder.txtbail.setTextColor(Color.parseColor("#808080"));
                    holder.txttrial.setTextColor(Color.parseColor("#808080"));
                    holder.txtverdict.setTextColor(Color.parseColor("#808080"));
                    holder.txtaquit.setTextColor(Color.parseColor("#808080"));
                    holder.txtsentence.setTextColor(Color.parseColor("#808080"));

                if(arrStatuses.get(x).getProcessName().equals("Investigate")){
                    holder.investigate.setBackgroundResource(R.drawable.progress_status);
                    holder.txtinvestigate.setTypeface(Typeface.DEFAULT_BOLD);
                    holder.txtinvestigate.setTextColor(Color.parseColor("#F39C12"));

                } else if(arrStatuses.get(x).getProcessName().equals("Arrest")){
                    holder.arrested.setBackgroundResource(R.drawable.progress_status);
                    holder.txtarrested.setTypeface(Typeface.DEFAULT_BOLD);
                    holder.txtarrested.setTextColor(Color.parseColor("#F39C12"));

                }  else if(arrStatuses.get(x).getProcessName().equals("Bail Hearing")){
                    holder.bail.setBackgroundResource(R.drawable.progress_status);
                    holder.txtbail.setTypeface(Typeface.DEFAULT_BOLD);
                    holder.txtbail.setTextColor(Color.parseColor("#F39C12"));

                }else if(arrStatuses.get(x).getProcessName().equals("Trial")){
                    holder.trial.setBackgroundResource(R.drawable.progress_status);
                    holder.txttrial.setTypeface(Typeface.DEFAULT_BOLD);
                    holder.txttrial.setTextColor(Color.parseColor("#F39C12"));

                }else if(arrStatuses.get(x).getProcessName().equals("Verdict")){
                    holder.verdict.setBackgroundResource(R.drawable.progress_status);
                    holder.txtverdict.setTypeface(Typeface.DEFAULT_BOLD);
                    holder.txtverdict.setTextColor(Color.parseColor("#F39C12"));

                }else if(arrStatuses.get(x).getProcessName().equals("Aquit")){
                    holder.aquit.setBackgroundResource(R.drawable.progress_status);
                    holder.txtaquit.setTypeface(Typeface.DEFAULT_BOLD);
                    holder.txtaquit.setTextColor(Color.parseColor("#F39C12"));

                }else{
                    holder.sentence.setBackgroundResource(R.drawable.progress_status);
                    holder.txtsentence.setTypeface(Typeface.DEFAULT_BOLD);
                    holder.txtsentence.setTextColor(Color.parseColor("#F39C12"));
                }

            }


            }
        if(occurencesCount==0){
           dateCreated = arrStatuses.get(arrStatuses.size()-1).getActionDate();
        }
        holder.txtDateCreated.setText(dateCreated);

        holder.imgDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "heloooooooooooooooo", Toast.LENGTH_SHORT).show();
                PopupMenu popup = new PopupMenu(context, v);
                popup.getMenuInflater().inflate(R.menu.case_dropdown,
                        popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_navigate:

                                //Or Some other code you want to put here.. This is just an example.
                                Toast.makeText(context, "heloooooooooooooooo", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(contex, " Install Clicked at position " + " : " + position, Toast.LENGTH_LONG).show();

                                break;
                            case R.id.action_feedback:

                                Toast.makeText(context, "zeeeeeeeeeeeeeeeeeeeeeee", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                break;
                        }

                        return true;
                    }
                });



            }
        });


        holder.btnCaseDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<StatusDetails> statusDetailsList = aList.get(index).getStatus();
                Intent intent = new Intent(context, CaseDetailsActivity.class);

                String[] processName = new String[statusDetailsList.size()];
                String[] actionDate = new String[statusDetailsList.size()];
                String[] actionLocation = new String[statusDetailsList.size()];
                boolean[] isCurrent = new boolean[statusDetailsList.size()];
                String currentProcess = "";
                for (int x = 0; x < statusDetailsList.size(); x++) {

                    processName[x] = statusDetailsList.get(x).getProcessName();
                    actionDate[x] = statusDetailsList.get(x).getActionDate();
                    actionLocation[x] = statusDetailsList.get(x).getActionLocation();
                    if (statusDetailsList.get(x).isIsCurrent()) {
                        currentProcess = statusDetailsList.get(x).getProcessName();
                    }
                }
                Toast.makeText(context, listdao.get(0).getCaseNo()+" "+listdao.get(1).getVictim()+" "+listdao.get(2).getAccused()+" "+listdao.get(index).getOffense(), Toast.LENGTH_SHORT).show();
                intent.putExtra("caseNo", aList.get(index).getCaseNo());
                intent.putExtra("victim", aList.get(index).getVictim());
                intent.putExtra("accused", aList.get(index).getAccused());
                intent.putExtra("offense", aList.get(index).getOffense());
                intent.putExtra("caseDesc", aList.get(index).getCaseDesc());
                intent.putExtra("processNameArr", processName);
                intent.putExtra("actionDateArr", actionDate);
                intent.putExtra("actionLocationArr", actionLocation);
                intent.putExtra("currentProcess", currentProcess);
                context.startActivity(intent);
                //context.startActivity(new Intent(context, CaseDetailsActivity.class));

            }
        });
        holder.btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //call an intent
                context.startActivity(new Intent(context, ProvideFeedback.class));
            }
        });
        holder.relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //call an intent

                ArrayList<StatusDetails> statusDetailsList = aList.get(index).getStatus();
                Intent intent = new Intent(context, CaseDetailsActivity.class);

                String[] processName = new String[statusDetailsList.size()];
                String[] actionDate = new String[statusDetailsList.size()];
                String[] actionLocation = new String[statusDetailsList.size()];
                boolean[] isCurrent = new boolean[statusDetailsList.size()];
                String currentProcess = "";
                for (int x = 0; x < statusDetailsList.size(); x++) {

                    processName[x] = statusDetailsList.get(x).getProcessName();
                    actionDate[x] = statusDetailsList.get(x).getActionDate();
                    actionLocation[x] = statusDetailsList.get(x).getActionLocation();
                    if (statusDetailsList.get(x).isIsCurrent()) {
                        currentProcess = statusDetailsList.get(x).getProcessName();
                    }
                }
                intent.putExtra("caseNo", aList.get(index).getCaseNo());
                intent.putExtra("victim", aList.get(index).getVictim());
                intent.putExtra("accused", aList.get(index).getAccused());
                intent.putExtra("offense", aList.get(index).getOffense());
                intent.putExtra("caseDesc", aList.get(index).getCaseDesc());
                intent.putExtra("processNameArr", processName);
                intent.putExtra("actionDateArr", actionDate);
                intent.putExtra("actionLocationArr", actionLocation);
                intent.putExtra("currentProcess", currentProcess);
                context.startActivity(intent);
                //context.startActivity(new Intent(context, CaseDetailsActivity.class));
            }
        });

        return view;
    }
    public void markCurrentProcess(){

    }

    static class CaseHolder
    {
        RelativeLayout relativeLayout,relativeLayout2;
        View investigate,arrested,bail,trial,verdict,aquit,sentence;
        TextView txtinvestigate,txtarrested,txtbail,txttrial,txtverdict,txtaquit,txtsentence;
        TextView txtSuspects,txtCaseNo,txtOffense,txtDateCreated;
        Button btnHeading,btnFindCourt,btnCaseDetails,btnFeedback;
        ImageView imgDropDown;

    }
}