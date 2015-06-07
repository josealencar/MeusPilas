package josealencar.com.br.meuspilas.source;

/**
 * Created by José on 26/05/2015.
 */
public class FixedAccount {
    private int idUser;
    private double valueAccount;
    private int dayPayment;
    private String typeAccount;

    public FixedAccount(int idUser, double valueAccount, int dayPayment, String typeAccount) {
        this.idUser = idUser;
        this.valueAccount = valueAccount;
        this.dayPayment = dayPayment;
        this.typeAccount = typeAccount;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public double getValueAccount() {
        return this.valueAccount;
    }

    public int getDayPayment() {
        return this.dayPayment;
    }

    public String getTypeAccount() {
        return this.typeAccount;
    }
}
