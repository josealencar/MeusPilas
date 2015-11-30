package josealencar.com.br.meuspilas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.FixedAccountDao;
import josealencar.com.br.meuspilas.dao.IncomeDao;
import josealencar.com.br.meuspilas.dao.OutcomeDao;
import josealencar.com.br.meuspilas.dao.SalaryDao;
import josealencar.com.br.meuspilas.dao.UserDao;
import josealencar.com.br.meuspilas.dao.VariableAccountDao;
import josealencar.com.br.meuspilas.model.FixedAccount;
import josealencar.com.br.meuspilas.model.Income;
import josealencar.com.br.meuspilas.model.Outcome;
import josealencar.com.br.meuspilas.model.Salary;
import josealencar.com.br.meuspilas.model.User;
import josealencar.com.br.meuspilas.model.VariableAccount;
import josealencar.com.br.meuspilas.service.FixedAccountService;
import josealencar.com.br.meuspilas.service.IncomeService;
import josealencar.com.br.meuspilas.service.MainService;
import josealencar.com.br.meuspilas.service.OutcomeService;
import josealencar.com.br.meuspilas.service.SalaryService;
import josealencar.com.br.meuspilas.service.UserService;
import josealencar.com.br.meuspilas.service.VariableAccountService;


public class MainActivity extends ActionBarActivity {

    static final String USER_ID = "userId";

    //TODO: Menu, Settings, NewEntry...
    private Db4oHelper db4o;
    private UserService userService;
    private IncomeService incomeService;
    private OutcomeService outcomeService;
    private SalaryService salaryService;
    private FixedAccountService fixedAccountService;
    private VariableAccountService variableAccountService;
    private MainService mainService;

    private User user;

    private Calendar dateDay;
    private int firstDay;
    private int today;
    private int lastDay;
    private long userId;

    private TextView lblBalance;
    private TextView lblIncome;
    private TextView lblOutcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateDay = Calendar.getInstance();
        firstDay = dateDay.getActualMinimum(Calendar.DAY_OF_MONTH);
        today = dateDay.get(Calendar.DAY_OF_MONTH);
        lastDay = dateDay.getActualMaximum(Calendar.DAY_OF_MONTH);

        Intent i = getIntent();
        userId = i.getLongExtra(LoginActivity.USER_ID, 0);

        findConstructor();
        configurarDb4o();

    }

    private void retrieveObjects() {
        //recupera objetos
        user = userService.findById(userId);
    }

    private void updateBalance() {
        double balance;
        List<Salary> salaries = salaryService.findByIdUser(userId);
        double plusIncomes = 0;
        for (Salary s : salaries) {
            if (s.getDayPayment() > today) {
                plusIncomes += s.getValueSalary();
            }
        }
        List<FixedAccount> fixedAccounts = fixedAccountService.findByUserId(userId);
        List<VariableAccount> variableAccounts = variableAccountService.findByUserId(userId);
        double plusOutcomes = 0;
        for (FixedAccount f : fixedAccounts) {
            if (f.getDayPayment() > today) {
                plusOutcomes += f.getValueAccount();
            }
        }
        for (VariableAccount v : variableAccounts) {
            if (v.getDayPayment() > today) {
                plusOutcomes += v.getValueAccount();
            }
        }
        List<Income> incomes = incomeService.findByIdUser(userId);
        List<Outcome> outcomes = outcomeService.findByIdUser(userId);
        double valueIncomes = plusIncomes + getIncomeService().getSumIncome(incomes);
        double valueOutcomes = plusOutcomes + getOutcomeService().getSumOutcome(outcomes);
        balance = valueIncomes - valueOutcomes;
        lblBalance.setText(String.valueOf(balance));
        lblIncome.setText(String.valueOf(valueIncomes));
        lblOutcome.setText(String.valueOf(valueOutcomes));
    }

    private void findConstructor() {
        lblBalance = (TextView) findViewById(R.id.labelValue);
        lblIncome = (TextView) findViewById(R.id.labelValueIncome);
        lblOutcome = (TextView) findViewById(R.id.labelValueOutcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.openConnection();

        //após abrir conexão
        retrieveObjects();
        getMainService().doCheckRoutine(userId);
        updateBalance();

        lblBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BalanceActivity.class);
                i.putExtra(USER_ID, userId);
                startActivity(i);
            }
        });

        lblIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, IncomesActivity.class);
                i.putExtra(USER_ID, userId);
                startActivity(i);
            }
        });

        lblOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, OutcomesActivity.class);
                i.putExtra(USER_ID, userId);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.closeConnection();
    }

    private void configurarDb4o() {
        // pegar o diretório do banco
        String dir = getDir("data", 0) + "/" ;

        // abre o dab4o helper
        db4o = new Db4oHelper(dir);

        // abre respectivos dao
        userService = new UserService(db4o);
        incomeService = new IncomeService(db4o);
        outcomeService = new OutcomeService(db4o);
        salaryService = new SalaryService(db4o);
        fixedAccountService = new FixedAccountService(db4o);
        variableAccountService = new VariableAccountService(db4o);
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
        switch (id){
            case R.id.action_settings:
                return true;

            case R.id.new_entry:
                Intent i = new Intent(MainActivity.this, EntryMainActivity.class);
                i.putExtra(USER_ID, userId);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private IncomeService getIncomeService() {
        if (incomeService == null) {
            return new IncomeService(db4o);
        } else {
            return incomeService;
        }
    }

    private OutcomeService getOutcomeService() {
        if (outcomeService == null) {
            return new OutcomeService(db4o);
        } else {
            return outcomeService;
        }
    }

    private MainService getMainService(){
        if (mainService == null) {
            return new MainService(db4o);
        } else {
            return mainService;
        }
    }
}
