package smoor.michael.smartconsumption;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    ImageButton ibutEen, ibutTwee;
    Button btnInhoud, btnMacro;
    TextView loginTxt, txtCalorieen;
    EditText textGeslacht, textGewicht, textLeeftijd, textLengte;
    WebView webData;
    Spinner dropDownActief;
    static TabHost tabHost;
    private Inhoud dbInhoud = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibutEen = (ImageButton) findViewById(R.id.IBEen);
        ibutTwee = (ImageButton) findViewById(R.id.IBTwee);
        btnInhoud = (Button) findViewById(R.id.btnData);
        btnMacro = (Button) findViewById(R.id.btnBereken);
        loginTxt = (TextView) findViewById(R.id.txtLogin);
        txtCalorieen = (TextView) findViewById(R.id.txtCalorie);
        textGeslacht = (EditText) findViewById(R.id.txtGeslacht);
        textGewicht = (EditText) findViewById(R.id.txtGewicht);
        textLeeftijd = (EditText) findViewById(R.id.txtLeeftijd);
        textLengte = (EditText) findViewById(R.id.txtLengte);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        webData = (WebView) findViewById(R.id.webViewData);
        webData.setWebViewClient(new WebViewClient());
        dropDownActief = (Spinner) findViewById(R.id.ddActief);


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

        String[] actief={"Niet actief", "Licht actief", "Gemiddeld actief", "Erg actief", "Extreem actief"};
        ArrayAdapter<String> stringArrayAdapter=
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        actief);
        dropDownActief.setAdapter(stringArrayAdapter);
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
        tabHost.setCurrentTab(1);
    }

    public void PlaatjeM(View v) {
        tabHost.setCurrentTab(1);
    }

    public void ButtonData(View v) {
        tabHost.setCurrentTab(2);
        webData.loadUrl("http://tweakers.net/");
    }

    public void ButtonMacro (View v) {
        String g = textGewicht.getText().toString();
        double gewicht = Double.parseDouble(g);
        String l = textLengte.getText().toString();
        double lengte = Double.parseDouble(l);
        String le = textLeeftijd.getText().toString();
        double leeftijd = Double.parseDouble(le);
        Double bmr;
        String geslacht = textGeslacht.getText().toString();
        String actief = dropDownActief.getSelectedItem().toString();

        if (geslacht == "Man") {
            bmr = ((9.99 * gewicht) + (6.25 * lengte) - (4.92 * leeftijd) + 5);
        }
        else {
            bmr = ((9.99 * gewicht) + (6.25 * lengte) - (4.92 * leeftijd) - 161);
        }

        if (actief == "Niet actief") {
            bmr *= 1.2;
        }
        else if (actief == "Licht actief") {
            bmr *= 1.35;
        }
        else if (actief == "Gemiddeld actief") {
            bmr *= 1.55;
        }
        else if (actief == "Erg actief") {
            bmr *= 1.77;
        }
        else {
            bmr *= 2.05;
        }
    }
}
