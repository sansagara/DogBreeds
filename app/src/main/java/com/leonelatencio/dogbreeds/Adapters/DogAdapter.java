package com.leonelatencio.dogbreeds.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leonelatencio.dogbreeds.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * Custom ArrayAdapter Pokemon Adapter for displaying list items in listview.
 *
 */
public class DogAdapter extends ArrayAdapter<String> {
    private static class ViewHolder {
        TextView breed;
        TextView number;
    }

    public DogAdapter(Context context, List<String> dogs) {
        super(context, 0, dogs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        if (this.getCount() > 0) {
            String dog_breed = getItem(position);

            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_item_dogs, parent, false);
                viewHolder.breed = (TextView) convertView.findViewById(R.id.breed);
                viewHolder.number = (TextView) convertView.findViewById(R.id.number);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // Populate the data into the template view using the pokemon object
            viewHolder.breed.setText(dog_breed);
            int dog_num = position +1;
            viewHolder.number.setText("" + dog_num);
        }
        // Return the completed view to render on screen
        return convertView;
    }

}
