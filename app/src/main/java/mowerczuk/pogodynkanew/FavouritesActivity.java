package mowerczuk.pogodynkanew;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    private ArrayList<LocationModel> locations;
    private ListView mainListView;
    private Database myDB = Database.getInstance(this);
    private LocationListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        mainListView = (ListView)findViewById(R.id.locationsListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FavouritesActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_new_location, null);
                final EditText mCity = (EditText) mView.findViewById(R.id.newCity);
                final EditText mCountry = (EditText) mView.findViewById(R.id.newCountry);
                Button mAdd = (Button) mView.findViewById(R.id.addNewLocation);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mAdd.setOnClickListener(new View.OnClickListener(){
                   @Override
                    public void onClick(View view) {
                       if (!mCity.getText().toString().isEmpty() && !mCountry.getText().toString().isEmpty()) {
                           String newCity = mCity.getText().toString();
                           String newCountry = mCountry.getText().toString();
                           try
                           {
                               LocationModel newLocation = new LocationModel(newCity, newCountry);
                               myDB.addLocation(newLocation);
                               //setResult(1);
                               locations = myDB.getAllLocations();
                               adapter = new LocationListAdapter(FavouritesActivity.this, locations);
                               mainListView.setAdapter(adapter);
                               dialog.cancel();
                           }
                           catch (Exception ex) {
                               Toast.makeText(FavouritesActivity.this, R.string.cannot_do_this, Toast.LENGTH_SHORT).show();
                               dialog.cancel();
                           }
                       }
                       else {
                           Toast.makeText(FavouritesActivity.this, R.string.fill_fields, Toast.LENGTH_SHORT).show();
                       }
                   }
                });

            }
        });

        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1,
                                           int position, long id) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FavouritesActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_edit_location, null);
                final EditText mCity = (EditText) mView.findViewById(R.id.editCity);
                final EditText mCountry = (EditText) mView.findViewById(R.id.editCountry);
                Button mDelete = (Button) mView.findViewById(R.id.deleteLocationButton);
                Button mEdit = (Button) mView.findViewById(R.id.editLocationButton);

                final LocationModel selectedLocation = ((LocationModel) parent.getItemAtPosition(position));

                final String currentCity = ((LocationModel) parent.getItemAtPosition(position)).getCity();
                final String currentCountry = ((LocationModel) parent.getItemAtPosition(position)).getCountry();
                final Integer currentId = ((LocationModel) parent.getItemAtPosition(position)).getID();


                mCity.setText(currentCity);
                mCountry.setText(currentCountry);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mDelete.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (!mCity.getText().toString().isEmpty() && !mCountry.getText().toString().isEmpty()) {
                            try
                            {
                                myDB.deleteLocation(selectedLocation);
                                //setResult(1);
                                locations = myDB.getAllLocations();
                                adapter = new LocationListAdapter(FavouritesActivity.this, locations);
                                mainListView.setAdapter(adapter);
                                dialog.cancel();
                            }
                            catch (Exception ex) {
                                Toast.makeText(FavouritesActivity.this, R.string.cannot_do_this, Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }
                        else {
                            Toast.makeText(FavouritesActivity.this, R.string.fill_fields, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mEdit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (!mCity.getText().toString().isEmpty() && !mCountry.getText().toString().isEmpty()) {
                            String newCity = mCity.getText().toString();
                            String newCountry = mCountry.getText().toString();
                            try
                            {
                                LocationModel newLocation = new LocationModel(currentId, newCity, newCountry);
                                myDB.updateLocation(newLocation);
                                //setResult(1);
                                locations = myDB.getAllLocations();
                                adapter = new LocationListAdapter(FavouritesActivity.this, locations);
                                mainListView.setAdapter(adapter);
                                dialog.cancel();
                            }
                            catch (Exception ex) {
                                Toast.makeText(FavouritesActivity.this, R.string.cannot_do_this, Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }
                        else {
                            Toast.makeText(FavouritesActivity.this, R.string.fill_fields, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                return  true;
            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

                String currentCity = ((LocationModel) parent.getItemAtPosition(position)).getCity();
                String currentCountry = ((LocationModel) parent.getItemAtPosition(position)).getCountry();
                mainActivity.putExtra("city", currentCity);
                mainActivity.putExtra("country", currentCountry);
                setResult(0, mainActivity);
                finish();
            }
        });


        //btn = (Button) findViewById(R.id.button);
        //addBtn = (Button) findViewById(R.id.newButton);
        /*
        if (myDB.getLocationsCount() == 0)
        {
            myDB.addLocation(new LocationModel("Bialystok", "Poland"));
            myDB.addLocation(new LocationModel("Warszawa", "Poland"));
            myDB.addLocation(new LocationModel("Berlin", "Germany"));
            myDB.addLocation(new LocationModel("Helsinki", "Finland"));
        }
        */

        locations = myDB.getAllLocations();

        adapter = new LocationListAdapter(this, locations);
        mainListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

        mainActivity.putExtra("city", "");
        mainActivity.putExtra("country", "");
        setResult(0, mainActivity);
        finish();
    }
}
