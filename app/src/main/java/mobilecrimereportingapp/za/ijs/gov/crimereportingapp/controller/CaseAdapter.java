package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

import static mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R.id.relativeLayout;
import static mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R.id.txtInvestigate;

/**
 * Created by TsundzukaniM on 13-Jul-17.
 */

    public class CaseAdapter extends SimpleAdapter {

        List<? extends Map<String, ?>> list  = new ArrayList<>();
        public CaseAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            list = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view=super.getView(position, convertView, parent);
            View investigate=(View) view.findViewById(R.id.investigate);
            View arrested=(View) view.findViewById(R.id.arrested);
            View bail=(View) view.findViewById(R.id.bail);
            View trial=(View) view.findViewById(R.id.trial);
            View verdict=(View) view.findViewById(R.id.verdict);
            View aquit=(View) view.findViewById(R.id.aquit);
            View sentence=(View) view.findViewById(R.id.sentence);

            TextView txtinvestigate=(TextView) view.findViewById(R.id.txtInvestigate);
            TextView txtarrested=(TextView) view.findViewById(R.id.txtArrested);
            TextView txtbail=(TextView) view.findViewById(R.id.txtBail);
            TextView txttrial=(TextView) view.findViewById(R.id.txtTrial);
            TextView txtverdict=(TextView) view.findViewById(R.id.txtVerdict);
            TextView txtaquit=(TextView) view.findViewById(R.id.txtAquit);
            TextView txtsentence=(TextView) view.findViewById(R.id.txtSentence);





            //imageView.setBackgroundResource(R.drawable.success_status);
            String processes = list.get(position).get("status"+position).toString();
            String arrStatuses[] = processes.substring(0,processes.length()).split("#");

            for(int x=0; x<arrStatuses.length; x++){


                    if(arrStatuses[x].indexOf("*")!=-1){

                        String currentProcess = arrStatuses[x].substring(0,arrStatuses[x].length()-1);
                        if(currentProcess.equals("Investigate")){
                            investigate.setBackgroundResource(R.drawable.progress_status);
                            txtinvestigate.setTypeface(Typeface.DEFAULT_BOLD);
                            txtinvestigate.setTextColor(Color.parseColor("#F39C12"));

                        } else if(currentProcess.equals("Arrest")){
                            arrested.setBackgroundResource(R.drawable.progress_status);
                            txtarrested.setTypeface(Typeface.DEFAULT_BOLD);
                            txtarrested.setTextColor(Color.parseColor("#F39C12"));

                        }  else if(currentProcess.equals("Bail Hearing")){
                            bail.setBackgroundResource(R.drawable.progress_status);
                            txtbail.setTypeface(Typeface.DEFAULT_BOLD);
                            txtbail.setTextColor(Color.parseColor("#F39C12"));

                        }else if(currentProcess.equals("Trial")){
                            trial.setBackgroundResource(R.drawable.progress_status);
                            txttrial.setTypeface(Typeface.DEFAULT_BOLD);
                            txttrial.setTextColor(Color.parseColor("#F39C12"));

                        }else if(currentProcess.equals("Verdict")){
                            verdict.setBackgroundResource(R.drawable.progress_status);
                            txtverdict.setTypeface(Typeface.DEFAULT_BOLD);
                            txtverdict.setTextColor(Color.parseColor("#F39C12"));

                        }else if(currentProcess.equals("Aquit")){
                            aquit.setBackgroundResource(R.drawable.progress_status);
                            txtaquit.setTypeface(Typeface.DEFAULT_BOLD);
                            txtaquit.setTextColor(Color.parseColor("#F39C12"));

                        }else{
                            sentence.setBackgroundResource(R.drawable.progress_status);
                            txtsentence.setTypeface(Typeface.DEFAULT_BOLD);
                            txtsentence.setTextColor(Color.parseColor("#F39C12"));
                        }

                }else{
                        String currentProcess = arrStatuses[x].substring(0,arrStatuses[x].length()-1);
                        if(arrStatuses[x].equals("Investigate")){
                            investigate.setBackgroundResource(R.drawable.success_status);
                        } else if(arrStatuses[x].equals("Arrest")){
                            arrested.setBackgroundResource(R.drawable.success_status);
                        }  else if(arrStatuses[x].equals("Bail Hearing")){
                            bail.setBackgroundResource(R.drawable.success_status);
                        }else if(arrStatuses[x].equals("Trial")){
                            trial.setBackgroundResource(R.drawable.success_status);
                        }else if(arrStatuses[x].equals("Verdict")){
                            verdict.setBackgroundResource(R.drawable.success_status);
                        }else if(arrStatuses[x].equals("Aquit")){
                            aquit.setBackgroundResource(R.drawable.success_status);
                        }else{
                            sentence.setBackgroundResource(R.drawable.success_status);
                        }


                }

            }



            investigate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            });
            return view;
            //return super.getView(position, convertView, parent);

        }

        @Override
        public int getCount() {
            return super.getCount();
        }
    }