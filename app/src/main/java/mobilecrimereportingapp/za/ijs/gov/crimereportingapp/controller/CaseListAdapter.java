package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
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
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.EventFeedbackActivity;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.ProvideFeedback;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.ReportCrimeActivity;

/**
 * Created by TsundzukaniM on 07-Aug-17.
 */

public class CaseListAdapter extends ArrayAdapter<CaseDetails> implements Filterable{

    private Context context;
    private int layoutResourceId;
    private ArrayList<CaseDetails> aList = null;
    private ArrayList<CaseDetails> filteredData = null;
    private CaseHolder holder = null;
    private View dropDownView, layoutView;
    //private ItemFilter mFilter = new ItemFilter();

    public CaseListAdapter(Context context, int layoutResourceId, ArrayList<CaseDetails> aList) {
        super(context, layoutResourceId, aList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.aList = aList;
        this.filteredData = aList;
    }
    public int getCount() {
        return filteredData.size();
    }
    public CaseDetails getItem(int position) {
        return filteredData.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    private View initializeHolder(View view, ViewGroup parent){
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
            holder.txtCaseContent=(TextView) view.findViewById(R.id.txtCaseContent);
            holder.txtCaseNo=(TextView) view.findViewById(R.id.txtCaseNo);
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
        return view;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        System.out.println(position+"xxxxxxxxxxxxxxxxxxxxxxxxxxxxx start");
        View view = initializeHolder(convertView, parent);
        ArrayList<StatusDetails> arrStatuses = aList.get(position).getStatus();
        holder.txtCaseNo.setText("Case No : "+aList.get(position).getCaseNo());
        holder.txtSuspects.setText("Crime: "+aList.get(position).getAccused());
        holder.txtCaseContent.setText("Suspect(s): "+aList.get(position).getOffense());
        holder.relativeLayout2.setTag(position);

        applyStatusBarColors(arrStatuses);

        //When click the horizontal status bar
        holder.relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCaseDetails((Integer)v.getTag());

            }
        });
        holder.imgDropDown.setTag(position);
        holder.imgDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dropDownView = v;
                PopupMenu popup = new PopupMenu(context, v);
                popup.getMenuInflater().inflate(R.menu.case_dropdown, popup.getMenu());
                popup.show();
                Menu menu = popup.getMenu();
                ArrayList<StatusDetails> statuses = aList.get((Integer)dropDownView.getTag()).getStatus();
                for(int x=0; x<statuses.size(); x++){
                    if(statuses.get(x).isIsCurrent() && statuses.get(x).getActionLocation()!=null){

                        menu.findItem(R.id.action_case_details).setVisible(false);

                    }
                }
                if(menu.equals(R.id.action_case_details)){

                }

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_navigate:
                                String address = "Johannesburg Magistrate court";
                                String uri = "https://maps.google.com/maps?saddr=current location&daddr="+address;
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                                break;

                            case R.id.action_feedback:
                                context.startActivity(new Intent(context, EventFeedbackActivity.class));
                                break;

                            default:
                                navigateToCaseDetails((Integer)dropDownView.getTag());
                                break;
                        }

                        return true;
                    }
                });

            }
        });

        System.out.println(position+"Eeeeeeeeeeeeeeeeeeeeeeeeeeeeee end");
        return view;
    }
    private void navigateToCaseDetails(int pos){


        ArrayList<StatusDetails> statusDetailsList = aList.get(pos).getStatus();
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
        // Toast.makeText(context, listdao.get(0).getCaseNo()+" "+listdao.get(1).getVictim()+" "+listdao.get(2).getAccused()+" "+listdao.get(index).getOffense(), Toast.LENGTH_SHORT).show();
        intent.putExtra("caseNo", aList.get(pos).getCaseNo());
        intent.putExtra("victim", aList.get(pos).getVictim());
        intent.putExtra("accused", aList.get(pos).getAccused());
        intent.putExtra("offense", aList.get(pos).getOffense());
        intent.putExtra("caseDesc", aList.get(pos).getCaseDesc());
        intent.putExtra("processNameArr", processName);
        intent.putExtra("actionDateArr", actionDate);
        intent.putExtra("actionLocationArr", actionLocation);
        intent.putExtra("currentProcess", currentProcess);
        context.startActivity(intent);
    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                aList = (ArrayList<CaseDetails>) results.values;
                notifyDataSetChanged();
            }
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                ArrayList<CaseDetails> fillteredArrList = new ArrayList<CaseDetails>();

                if (filteredData == null) {
                    filteredData = new ArrayList<CaseDetails>(aList);
                }
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = filteredData.size();
                    filterResults.values = filteredData;

                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < filteredData.size(); i++) {
                        if (filteredData.get(i).getCaseNo().toLowerCase().startsWith(constraint.toString())) {
                            fillteredArrList.add(new CaseDetails(
                                    filteredData.get(i).getCaseNo(),
                                    filteredData.get(i).getVictim(),
                                    filteredData.get(i).getAccused(),
                                    filteredData.get(i).getOffense(),
                                    filteredData.get(i).getCaseDesc(),
                                    filteredData.get(i).getStatus()));
                        }
                    }
                    filterResults.count = fillteredArrList.size();
                    filterResults.values = fillteredArrList;
                }
                return filterResults;
            }


        };
        return filter;
    }
    private void applyStatusBarColors(ArrayList<StatusDetails> arrStatuses){


        int occurencesCount=0;

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
                dateCreated = arrStatuses.get(x).getProcessName()+" date: "+ arrStatuses.get(x).getActionDate();

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
            dateCreated =  arrStatuses.get(arrStatuses.size()-1).getProcessName() + " date: "+arrStatuses.get(arrStatuses.size()-1).getActionDate();
        }
        holder.txtDateCreated.setText(dateCreated);

    }
    static class CaseHolder
    {
        RelativeLayout relativeLayout,relativeLayout2;
        View investigate,arrested,bail,trial,verdict,aquit,sentence;
        TextView txtinvestigate,txtarrested,txtbail,txttrial,txtverdict,txtaquit,txtsentence;
        TextView txtSuspects,txtCaseNo,txtDateCreated, txtCaseContent;
        Button btnHeading,btnFindCourt,btnCaseDetails,btnFeedback;
        ImageView imgDropDown;

    }



}