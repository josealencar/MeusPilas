package josealencar.com.br.meuspilas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.UserDao;
import josealencar.com.br.meuspilas.model.User;

public class NewAccountActivity extends AppCompatActivity {

    private Db4oHelper db4o;
    private UserDao userDao;

    private boolean nameHasContent = false;
    private boolean emailHasContent = false;
    private boolean passwordHasContent = false;

    private Button newAccount;
    private Button cancel;
    private EditText accountName;
    private EditText accountEmail;
    private EditText accountPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findConstructor();
        configurarDb4o();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        accountName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (hasContent(accountName)) {
                    nameHasContent = true;
                    checkButton();
                } else {
                    nameHasContent = false;
                }
            }
        });

        accountEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (hasContent(accountEmail)) {
                    emailHasContent = true;
                    checkButton();
                } else {
                    emailHasContent = false;
                }
            }
        });

        accountPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (hasContent(accountPassword)) {
                    passwordHasContent = true;
                    checkButton();
                } else {
                    passwordHasContent = false;
                }
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewAccountActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
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

    private void checkButton() {
        if (nameHasContent && emailHasContent && passwordHasContent) {
            newAccount.setEnabled(true);
        }
    }

    private boolean hasContent(EditText generalEditText) {
        return generalEditText != null && generalEditText.getText().toString().trim() != "";
    }

    private void createUser() {
        User user = new User(null, accountName.getText().toString(), accountEmail.getText().toString(), accountPassword.getText().toString());
        userDao.save(user);
        Intent i = new Intent(NewAccountActivity.this, LoginActivity.class);
        startActivity(i);
    }

    private void findConstructor() {
        newAccount = (Button) findViewById(R.id.newAccount);
        cancel = (Button) findViewById(R.id.btCancel);
        accountName = (EditText) findViewById(R.id.accountName);
        accountEmail = (EditText) findViewById(R.id.accountEmail);
        accountPassword = (EditText) findViewById(R.id.accountPassword);
    }

    private void configurarDb4o() {
        // pegar o diretório do banco
        String dir = getDir("data", 0) + "/" ;

        // abre o dab4o helper
        db4o = new Db4oHelper(dir);

        // abre o user dao
        userDao = new UserDao(db4o);
    }
}
