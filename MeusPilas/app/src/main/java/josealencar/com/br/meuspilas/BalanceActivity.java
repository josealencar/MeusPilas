package josealencar.com.br.meuspilas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.model.Balance;
import josealencar.com.br.meuspilas.model.Income;
import josealencar.com.br.meuspilas.model.Outcome;
import josealencar.com.br.meuspilas.service.IncomeService;
import josealencar.com.br.meuspilas.service.OutcomeService;

public class BalanceActivity extends AppCompatActivity {

    private long userId;

    private Db4oHelper db4o;
    private IncomeService incomeService;
    private OutcomeService outcomeService;

    private ListView lvBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        userId = i.getLongExtra(MainActivity.USER_ID, 0);

        findConstructor();
        configurarDb4o();
    }

    private void configurarDb4o() {
        // pegar o diretório do banco
        String dir = getDir("data", 0) + "/" ;

        // abre o dab4o helper
        db4o = new Db4oHelper(dir);

        outcomeService = new OutcomeService(db4o);
        incomeService = new IncomeService(db4o);
    }

    private void findConstructor() {
        lvBalance = (ListView) findViewById(R.id.lvBalance);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.openConnection();

        List<Income> incomes = incomeService.findByIdUser(userId);
        List<Outcome> outcomes = outcomeService.findByIdUser(userId);
        List<Balance> balances = new ArrayList<>();
        for (Income income : incomes) {
            balances.add(new Balance("+", income.getValueIncome(), income.getTypeIncome()));
        }
        for (Outcome o : outcomes) {
            balances.add(new Balance("-", o.getValueOutcome(), o.getTypeOutcome()));
        }

        ArrayAdapter<Balance> balanceArrayAdapter = new ArrayAdapter<Balance>(getBaseContext(), android.R.layout.simple_list_item_1, balances);

        lvBalance.setAdapter(balanceArrayAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.closeConnection();
    }
}
