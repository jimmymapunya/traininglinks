package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;




/**
 * Created by TsundzukaniM on 13-Jul-17.
 */

    public class CaseAdapter extends SimpleAdapter implements AdapterView.OnItemClickListener {

        List<? extends Map<String, ?>> list  = new ArrayList<>();
        private Context context;
        private int index;
        private String arrStatuses[];

        public CaseAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            list = data;


        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return null;

        }

        @Override
        public int getCount() {
            return super.getCount();
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}