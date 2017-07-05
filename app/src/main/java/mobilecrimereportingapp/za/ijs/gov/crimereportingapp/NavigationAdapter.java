package mobilecrimereportingapp.za.ijs.gov.crimereportingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


/**
 * Created by MaribolleR on 2017/04/17.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.naviViewHolder> {

    /*Static variables for Navigation*/
    private static final int REPORT_CRIME= 0;
    private static final int REPORT_FRAUD = 1;
    private static final int CASES = 2;
    private static final int COURT_FINDER= 3;
    private static final int FEEDBACK = 4;
    private static final int COMPOSE = 5;

    private List<NavigationInfo> navigationData= Collections.emptyList();
    private Context context;

    public NavigationAdapter(Context context, List<NavigationInfo> navigationData){
        this.navigationData = navigationData;
        this.context = context;
    }

    @Override
    public naviViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_nav_row,parent,false);

        naviViewHolder ViewHolder = new naviViewHolder(view);


        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(final naviViewHolder holder, int position) {
        final NavigationInfo currentItem = navigationData.get(position);

        /*Set the text and image of the view holder*/
        holder.listText.setText(currentItem.getItemName());
        holder.listImage.setImageResource(currentItem.getIconID());
    }

    @Override
    public int getItemCount() {
        return navigationData.size();
    }

    class naviViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView listImage;
        TextView listText;

        public naviViewHolder(View itemView) {
            super(itemView);

            /*Attachment of the view holder with listImage and listText*/
            listImage = (ImageView) itemView.findViewById(R.id.listIcn);
            listText = (TextView) itemView.findViewById(R.id.listTxt);

            listText.setOnClickListener(this);
            listImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(getLayoutPosition()==REPORT_CRIME){
                //context.startActivity(new Intent(context.getApplicationContext(), NotificationsActivity.class));
            } else if(getLayoutPosition() == REPORT_FRAUD){
                //context.startActivity(new Intent(context.getApplicationContext(), something.class));
            } else if(getLayoutPosition() == CASES){
                //context.startActivity(new Intent(context.getApplicationContext(), something.class));
            } else if(getLayoutPosition() == COURT_FINDER){
                //context.startActivity(new Intent(context.getApplicationContext(), something.class));
            } else if(getLayoutPosition() == FEEDBACK){
                //context.startActivity(new Intent(context.getApplicationContext(), something.class));
            } else if(getLayoutPosition() == COMPOSE){
                //context.startActivity(new Intent(context.getApplicationContext(), something.class));
            }
        }
    }
}