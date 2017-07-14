package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.NotificationInfo;

/**
 * Created by JimmyM on 2017/07/14.
 */

public class NotificationAdapter extends ArrayAdapter<NotificationInfo>
{

    ArrayList<NotificationInfo> notification ;
    public NotificationAdapter(Context context,ArrayList<NotificationInfo> objects) {
        super(context, R.layout.notification_custom_listview, objects);
        this.notification = objects;
        // TODO Auto-generated constructor stub
    }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.notification_custom_listview, null,true);
        TextView txtDescription = (TextView) single_row.findViewById(R.id.txtNotificationDescription);
        TextView txtNotificationTime = (TextView) single_row.findViewById(R.id.txtNotificationDate);

            txtDescription.setText(notification.get(position).getNotificationDescription());
            txtNotificationTime.setText(notification.get(position).getNotificationdate());


        return single_row;
    }
}
