package josealencar.com.br.meuspilas.source;

/**
 * Created by José on 26/05/2015.
 */
public class User {
    private int idUser;
    private String nameUser;
    private String emailUser;
    private String password;

    public User(int idUser, String nameUser, String emailUser, String password){
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.password = password;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public String getNameUser() {
        return this.nameUser;
    }

    public String getEmailUser() {
        return this.emailUser;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return this.getNameUser();
    }
}
