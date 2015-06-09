package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class CreditCards {
    private int idUser;
    private String nameCreditCard;
    private double limitCreditCard;
    private int dayPayment;

    public CreditCards(int idUser, String nameCreditCard, double limitCreditCard, int dayPayment) {
        this.idUser = idUser;
        this.nameCreditCard = nameCreditCard;
        this.limitCreditCard = limitCreditCard;
        this.dayPayment = dayPayment;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public String getNameCreditCard() {
        return this.nameCreditCard;
    }

    public double getLimitCreditCard() {
        return this.limitCreditCard;
    }

    public int getDayPayment() {
        return this.dayPayment;
    }
}
