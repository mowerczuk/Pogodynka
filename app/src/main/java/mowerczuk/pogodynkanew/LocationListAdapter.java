package mowerczuk.pogodynkanew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macie on 26.01.2017.
 */

public class LocationListAdapter extends BaseAdapter {
    private List locations;
    private final LayoutInflater inflater;

    public LocationListAdapter(Context context, ArrayList<LocationModel> locations) {
        super();
        this.locations = locations;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Object getItem(int i) {
        return locations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.locations_list, viewGroup, false);

        TextView cityText = (TextView)rowView.findViewById(R.id.city);
        TextView countryText = (TextView)rowView.findViewById(R.id.country);

        LocationModel location = (LocationModel) getItem(i);

        cityText.setText(location.getCity());
        countryText.setText(location.getCountry());

        return rowView;
    }
}
