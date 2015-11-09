package josealencar.com.br.meuspilas;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.UserDao;
import josealencar.com.br.meuspilas.model.User;


public class LoginActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int SIGN_IN_CODE = 9000;
    private GoogleApiClient googleApiClient;
    private ConnectionResult connectionResult;

    static final String USER_ID = "userId";

    private Db4oHelper db4o;
    private UserDao userDao;

    private boolean isConcentScreenOpened;
    private boolean isSignInButtonClicked;

    private Button signIn;
    private Button newAccount;
    private SignInButton sigInPlus;
    private EditText emailUser;
    private EditText passwordUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findConstructor();
        configurarDb4o();

        googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .addConnectionCallbacks(LoginActivity.this)
                .addOnConnectionFailedListener(LoginActivity.this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

        while (googleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(googleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(googleApiClient);
            googleApiClient.disconnect();
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, NewAccountActivity.class);
                startActivity(i);
            }
        });

        sigInPlus.setEnabled(false);
        sigInPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!googleApiClient.isConnected()) {
                    isConcentScreenOpened = true;
                    isSignInButtonClicked = true;
                    resolveSignIn();
                }
            }
        });
    }

    private void tryLogin() {
        String txtEmail = emailUser.getText().toString();
        String txtPassword = passwordUser.getText().toString();

        List<User> users = userDao.findByEmail(txtEmail);

        for(User u : users) {
            if (u.getPassword().equals(txtPassword)) {
                final long userId = userDao.getId(u);
                Intent i =new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra(USER_ID, userId);
                startActivity(i);
                return;
            }
        }

        if (users.isEmpty()) {
            Toast.makeText(LoginActivity.this, R.string.userNotFound, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, R.string.passwordIncorrect, Toast.LENGTH_LONG).show();
        }
    }

    private void findConstructor() {
        signIn = (Button) findViewById(R.id.button_login);
        newAccount = (Button) findViewById(R.id.button_create_account);
        sigInPlus = (SignInButton) findViewById(R.id.btSignInDefault);
        emailUser = (EditText) findViewById(R.id.user);
        passwordUser = (EditText) findViewById(R.id.password);
    }

    public void resolveSignIn(){
        if(connectionResult != null && connectionResult.hasResolution()){
            try {
                connectionResult.startResolutionForResult(LoginActivity.this, SIGN_IN_CODE);
            } catch (IntentSender.SendIntentException e) {
                googleApiClient.connect();
            }
        }
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

        // abre o user dao
        userDao = new UserDao(db4o);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(googleApiClient != null){
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(googleApiClient !=null && googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_CODE){
            if(resultCode != RESULT_OK){

            }

            if(!googleApiClient.isConnecting()){
                googleApiClient.connect();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onConnected(Bundle bundle) {
        getDataProfile();
    }

    private void getDataProfile() {
        Person p = Plus.PeopleApi.getCurrentPerson(googleApiClient);

        if(p != null){
            String id = p.getId();
            String email = Plus.AccountApi.getAccountName(googleApiClient);
            String nome = p.getDisplayName();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(LoginActivity.this, R.string.messageUserPlusFail, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if(!result.hasResolution()){
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), LoginActivity.this, 0).show();
            return;
        }

        if(!isConcentScreenOpened){
            connectionResult = result;
            if(isSignInButtonClicked){
                resolveSignIn();
            }
        }
    }
}
