package mowerczuk.pogodynkanew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class AboutActivity extends AppCompatActivity {
    TextView _rights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        _rights = (TextView) findViewById(R.id.rightsReserved);
        _rights.setText("Copyright \u00a9 " + Calendar.getInstance().get(Calendar.YEAR) + " " + getString(R.string.author_sign) + ". " + getString(R.string.right_reserved));

    }

    @Override
    public void onBackPressed(){
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

        mainActivity.putExtra("city", "");
        mainActivity.putExtra("country", "");
        setResult(1, mainActivity);
        finish();
    }
}
