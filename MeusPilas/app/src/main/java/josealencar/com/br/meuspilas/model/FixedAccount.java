package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class FixedAccount {
    private long idUser;
    private double valueAccount;
    private int dayPayment;
    private String typeAccount;

    public FixedAccount(long idUser, double valueAccount, int dayPayment, String typeAccount) {
        this.idUser = idUser;
        this.valueAccount = valueAccount;
        this.dayPayment = dayPayment;
        this.typeAccount = typeAccount;
    }

    public long getIdUser() {
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
