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
import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.IncomeDao;
import josealencar.com.br.meuspilas.dao.OutcomeDao;
import josealencar.com.br.meuspilas.model.Outcome;

public class OutcomesActivity extends AppCompatActivity {

    private long userId;

    private Db4oHelper db4o;
    private OutcomeDao outcomeDao;

    private ArrayAdapter<Outcome> outcomeArrayAdapter;
    private List<Outcome> outcomes;

    private ListView lvOutcomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcomes);
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

        outcomeDao = new OutcomeDao(db4o);
    }

    private void findConstructor() {
        lvOutcomes = (ListView) findViewById(R.id.lvOutcomes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.openConnection();

        outcomes = outcomeDao.findByIdUser(userId);

        outcomeArrayAdapter = new ArrayAdapter<Outcome>(this, android.R.layout.simple_list_item_1, outcomes);

        lvOutcomes.setAdapter(outcomeArrayAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.closeConnection();
    }
}
