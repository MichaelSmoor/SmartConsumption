package smoor.michael.smartconsumption;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    ImageButton ibutEen, ibutTwee;
    TextView loginTxt;
    static TabHost tabHost;
    private Inhoud dbInhoud = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibutEen = (ImageButton) findViewById(R.id.IBEen);
        ibutTwee = (ImageButton) findViewById(R.id.IBTwee);
        loginTxt = (TextView) findViewById(R.id.txtLogin);
        tabHost = (TabHost) findViewById(R.id.tabHost);

        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Login");
        tabSpec.setContent(R.id.tabLogin);
        tabSpec.setIndicator("Login");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Home");
        tabSpec.setContent(R.id.tabHome);
        tabSpec.setIndicator("Home");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Inhoud");
        tabSpec.setContent(R.id.tabInhoud);
        tabSpec.setIndicator("Inhoud");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Macro calculator");
        tabSpec.setContent(R.id.tabMacroCalculator);
        tabSpec.setIndicator("Macro's");
        tabHost.addTab(tabSpec);

        dbInhoud = new Inhoud(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void PlaatjeV(View v) {
        tabHost.setCurrentTab(2);
    }

    public void PlaatjeM(View v) {
        tabHost.setCurrentTab(2);
    }
}
