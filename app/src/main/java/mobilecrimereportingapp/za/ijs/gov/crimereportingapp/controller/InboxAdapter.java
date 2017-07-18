package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.InboxModel;

/**
 * Created by JimmyM on 2017/07/17.
 */

public class InboxAdapter extends ArrayAdapter<InboxModel>
{

    ArrayList<InboxModel> inbox ;
    public InboxAdapter(Context context, ArrayList<InboxModel> objects) {
        super(context, R.layout.inbox_custom_list, objects);
        this.inbox = objects;
        // TODO Auto-generated constructor stub
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.inbox_custom_list, null,true);
        TextView txtFrom = (TextView) single_row.findViewById(R.id.txtFrom);
        TextView txtSubject = (TextView) single_row.findViewById(R.id.txtSubject);
        //TextView txtMessage = (TextView) single_row.findViewById(R.id.txtMessage);
        TextView txtInboxDare = (TextView) single_row.findViewById(R.id.txtInboxDare);

        txtFrom.setText(inbox.get(position).getFrom());
        txtSubject.setText(inbox.get(position).getSubject());
        //txtMessage.setText(inbox.get(position).getMessage());
        txtInboxDare.setText(inbox.get(position).getInboxDate());

        return single_row;
    }
}