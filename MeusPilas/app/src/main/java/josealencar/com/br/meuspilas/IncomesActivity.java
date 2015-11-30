package josealencar.com.br.meuspilas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.IncomeDao;
import josealencar.com.br.meuspilas.model.Income;
import josealencar.com.br.meuspilas.service.IncomeService;

public class IncomesActivity extends AppCompatActivity {

    static final String USER_ID = "userId";

    private long userId;

    private Db4oHelper db4o;
    private IncomeService incomeService;

    private ArrayAdapter<Income> incomeArrayAdapter;
    private ArrayList<Income> incomes;

    private ListView lvIncomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);
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

        incomeService = new IncomeService(db4o);
    }

    private void findConstructor() {
        lvIncomes = (ListView) findViewById(R.id.lvIncomes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.openConnection();

        incomes = new ArrayList<>();
        incomes.addAll(incomeService.findByIdUser(userId));

        incomeArrayAdapter = new ArrayAdapter<Income>(getBaseContext(), android.R.layout.simple_list_item_1, incomes);

        lvIncomes.setAdapter(incomeArrayAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.closeConnection();
    }
}
