package josealencar.com.br.meuspilas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.IncomeDao;
import josealencar.com.br.meuspilas.dao.OutcomeDao;
import josealencar.com.br.meuspilas.dao.UserDao;
import josealencar.com.br.meuspilas.model.Income;
import josealencar.com.br.meuspilas.model.Outcome;
import josealencar.com.br.meuspilas.model.User;
import josealencar.com.br.meuspilas.service.IncomeService;
import josealencar.com.br.meuspilas.service.OutcomeService;


public class MainActivity extends ActionBarActivity {

    static final String USER_ID = "userId";

    //TODO: Menu, Settings, NewEntry...
    private Db4oHelper db4o;
    private UserDao userDao;
    private IncomeDao incomeDao;
    private OutcomeDao outcomeDao;

    private IncomeService incomeService;
    private OutcomeService outcomeService;

    private User user;

    private long userId;

    private TextView lblBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        userId = i.getLongExtra(LoginActivity.USER_ID, 0);

        findConstructor();
        configurarDb4o();
        
        updateBalance();
    }

    private void updateBalance() {
        double balance;
        List<Income> incomes = incomeDao.findByIdUser(userDao.getId(user));
        List<Outcome> outcomes = outcomeDao.findByIdUser(userDao.getId(user));
        balance = getIncomeService().getSumIncome(incomes) - getOutcomeService().getSumOutcome(outcomes);
        lblBalance.setText(String.valueOf(balance));
    }

    private void findConstructor() {
        lblBalance = (TextView) findViewById(R.id.labelValue);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.openConnection();
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
        userDao = new UserDao(db4o);
        incomeDao = new IncomeDao(db4o);
        outcomeDao = new OutcomeDao(db4o);

        //recupera objetos
        user = userDao.findById(userId);
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
            return new IncomeService();
        } else {
            return incomeService;
        }
    }

    private OutcomeService getOutcomeService() {
        if (outcomeService == null) {
            return new OutcomeService();
        } else {
            return outcomeService;
        }
    }
}
