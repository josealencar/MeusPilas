package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class User {
    private String idGooglePlus;
    private String nameUser;
    private String emailUser;
    private String password;

    public User(String idGooglePlus, String nameUser, String emailUser, String password){
        this.idGooglePlus = idGooglePlus;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.password = password;
    }

    public User() {}

    public String getNameUser() {
        return this.nameUser;
    }

    public String getEmailUser() {
        return this.emailUser;
    }

    public String getPassword() {
        return this.password;
    }

    public String getIdGooglePlus() {
        return idGooglePlus;
    }

    @Override
    public String toString() {
        return this.getNameUser();
    }
}
