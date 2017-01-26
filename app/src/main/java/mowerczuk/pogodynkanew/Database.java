package mowerczuk.pogodynkanew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by macie on 26.01.2017.
 */

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "locationManager";

    private static final String TABLE_LOCATIONS = "locations";

    private static final String KEY_ID = "id";
    private static final String KEY_CITY = "city";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_SUNSET = "sunset";
    private static final String KEY_SUNRISE = "sunrise";

    private Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static Database MyDbInstance;
    public static Database getInstance(Context context)
    {
        if (MyDbInstance == null)
        {
            MyDbInstance = new Database(context);
        }
        return MyDbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CITY + " TEXT," + KEY_COUNTRY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);

        onCreate(sqLiteDatabase);
    }

    public void addLocation(LocationModel location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CITY, location.getCity());
        values.put(KEY_COUNTRY, location.getCountry());

        db.insert(TABLE_LOCATIONS, null, values);
        db.close();
    }

    public int updateLocation(LocationModel location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CITY, location.getCity());
        values.put(KEY_COUNTRY, location.getCountry());

        return db.update(TABLE_LOCATIONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(location.getID()) });
    }

    public void deleteLocation(LocationModel location) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(location.getID()) });
        db.close();
    }

    public ArrayList<LocationModel> getAllLocations() {
        ArrayList<LocationModel> locationList = new ArrayList<LocationModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_LOCATIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                LocationModel location = new LocationModel(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                locationList.add(location);
            } while (cursor.moveToNext());
        }

        return locationList;
    }

    public int getLocationsCount(){
        return getAllLocations().size();
    }
}
