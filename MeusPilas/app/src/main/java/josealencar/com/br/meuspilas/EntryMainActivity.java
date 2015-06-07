package josealencar.com.br.meuspilas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class EntryMainActivity extends ActionBarActivity {

    //TODO: Buttons Navigation, Spinners
    Button btIncome;
    Button btOutcome;
    Button btTransfer;

    Spinner spIncome;
    Spinner spOutcome;
    Spinner spTransfer;

    LinearLayout llIncome;
    LinearLayout llOutcome;
    LinearLayout llTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_main);

        llIncome = (LinearLayout) findViewById(R.id.llIncome);
        llOutcome = (LinearLayout) findViewById(R.id.llOutcome);
        llTransfer = (LinearLayout) findViewById(R.id.llTransfer);

        btIncome = (Button) findViewById(R.id.btIncome);
        btOutcome = (Button) findViewById(R.id.btOutcome);
        btTransfer = (Button) findViewById(R.id.btTransfer);

        spIncome = (Spinner) findViewById(R.id.spIncome);
        spOutcome = (Spinner) findViewById(R.id.spOutcome);
        spTransfer = (Spinner) findViewById(R.id.spTransfer);

        btIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llIncome.setVisibility(View.VISIBLE);
                llOutcome.setVisibility(View.GONE);
                llTransfer.setVisibility(View.GONE);
            }
        });

        btOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llIncome.setVisibility(View.GONE);
                llOutcome.setVisibility(View.VISIBLE);
                llTransfer.setVisibility(View.GONE);
            }
        });

        btTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llIncome.setVisibility(View.GONE);
                llOutcome.setVisibility(View.GONE);
                llTransfer.setVisibility(View.VISIBLE);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(getBaseContext(), R.array.spIncome, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spIncome.setAdapter(adapter);
        spIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence incomeSelecionado = (CharSequence) parent.getItemAtPosition(position);
                if(incomeSelecionado.equals(R.string.salary)){

                } else if(incomeSelecionado.equals(R.string.loan)){

                } else if(incomeSelecionado.equals(R.string.gift)){

                } else if(incomeSelecionado.equals(R.string.other)){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter
                .createFromResource(getBaseContext(), R.array.spOutcome, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOutcome.setAdapter(adapter1);
        spOutcome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
                .createFromResource(getBaseContext(), R.array.spTransfer, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTransfer.setAdapter(adapter2);
        spTransfer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_main, menu);
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
}
