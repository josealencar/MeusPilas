package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class Income {
    private long idUser;
    private double valueIncome;
    private int monthIncome;
    private String typeIncome;

    public Income(long idUser, double valueIncome, int monthIncome, String typeIncome) {
        this.idUser = idUser;
        this.valueIncome = valueIncome;
        this.monthIncome = monthIncome;
        this.typeIncome = typeIncome;
    }

    public long getIdUser() {
        return this.idUser;
    }

    public double getValueIncome() {
        return this.valueIncome;
    }

    public int getMonthIncome() {
        return this.monthIncome;
    }

    public String getTypeIncome() {
        return this.typeIncome;
    }

    @Override
    public String toString() {
        return typeIncome + " - R$" + valueIncome;
    }
}
