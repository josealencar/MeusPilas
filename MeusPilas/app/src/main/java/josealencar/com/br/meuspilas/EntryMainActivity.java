package josealencar.com.br.meuspilas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.FixedAccountDao;
import josealencar.com.br.meuspilas.dao.LoanDao;
import josealencar.com.br.meuspilas.dao.SalaryDao;
import josealencar.com.br.meuspilas.dao.VariableAccountDao;
import josealencar.com.br.meuspilas.model.Salary;


public class EntryMainActivity extends ActionBarActivity {

    //TODO: Buttons Navigation, Spinners
    private Button btIncome;
    private Button btOutcome;
    private Button btTransfer;
    private Button btSalary;
    private Button btLoan;

    private Spinner spIncome;
    private Spinner spOutcome;
    private Spinner spTransfer;

    private LinearLayout llIncome;
    private LinearLayout llOutcome;
    private LinearLayout llTransfer;
    private LinearLayout llSalary;
    private LinearLayout llLoans;

    private EditText etValueSalary;
    private EditText etDayPaymentSalary;
    private EditText etValueLoan;
    private EditText etAmountOfInstallments;
    private EditText etInterestRates;
    private EditText etDayPaymentLoan;
    private EditText etTimeToPayment;
    private EditText etBeneficiaryName;

    private Db4oHelper db4o;
    private SalaryDao salaryDao;
    private LoanDao loanDao;
    private FixedAccountDao fixedAccountDao;
    private VariableAccountDao variableAccountDao;

    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_main);

        Intent i = getIntent();
        userId = i.getLongExtra(MainActivity.USER_ID, 0);

        findConstructor();
        configurarDb4o();

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

        btSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueSalary = etValueSalary.length() > 0 ? etValueSalary.getText().toString() : "";
                String dayPayment = etDayPaymentSalary.length() > 0 ? etDayPaymentSalary.getText().toString() : "";
                if (valueSalary.trim() != "" && dayPayment.trim() != "") {
                    Salary salary = new Salary(userId, Double.valueOf(valueSalary.replace(",", ".")), Integer.valueOf(dayPayment));
                    salaryDao.save(salary);
                    etValueSalary.setText(null);
                    etDayPaymentSalary.setText("");
                } else {
                    makeAToast(getString(R.string.incompleteSalary));
                }
            }
        });

        btLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                if(incomeSelecionado.equals(getString(R.string.salary))){
                    llSalary.setVisibility(View.VISIBLE);
                    llLoans.setVisibility(View.GONE);
                } else if(incomeSelecionado.equals(getString(R.string.loan))){
                    llSalary.setVisibility(View.GONE);
                    llLoans.setVisibility(View.VISIBLE);
                } else if(incomeSelecionado.equals(getString(R.string.gift))){
                    llSalary.setVisibility(View.GONE);
                    llLoans.setVisibility(View.GONE);
                } else if(incomeSelecionado.equals(getString(R.string.other))){
                    llSalary.setVisibility(View.GONE);
                    llLoans.setVisibility(View.GONE);
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

    private void makeAToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    private void findConstructor() {
        llIncome = (LinearLayout) findViewById(R.id.llIncome);
        llOutcome = (LinearLayout) findViewById(R.id.llOutcome);
        llTransfer = (LinearLayout) findViewById(R.id.llTransfer);
        llSalary = (LinearLayout) findViewById(R.id.llSalary);
        llLoans = (LinearLayout) findViewById(R.id.llLoans);

        btIncome = (Button) findViewById(R.id.btIncome);
        btOutcome = (Button) findViewById(R.id.btOutcome);
        btTransfer = (Button) findViewById(R.id.btTransfer);
        btSalary = (Button) findViewById(R.id.btSalary);
        btLoan = (Button) findViewById(R.id.btLoan);

        spIncome = (Spinner) findViewById(R.id.spIncome);
        spOutcome = (Spinner) findViewById(R.id.spOutcome);
        spTransfer = (Spinner) findViewById(R.id.spTransfer);

        etValueSalary = (EditText) findViewById(R.id.etValueSalary);
        etDayPaymentSalary = (EditText) findViewById(R.id.etDayPaymentSalary);
    }

    private void configurarDb4o() {
        // TODO: criar db4oHelper e clienteDao

        //pegar o diretorio
        String dir=getDir("data",0)+"/";

        //abre o db4o helper
        db4o = new Db4oHelper(dir);

        //abre o cliente dao
        //Cliente é a classe - DAO (Data Access Object) -> inserir, atualizar, excluir, listar, buscar por id.
        salaryDao = new SalaryDao(db4o);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: abrir conexão e carregar clientes
        db4o.openConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // TODO: fechar conexão
        db4o.closeConnection();
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
