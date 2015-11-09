package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class Salary {
    private long idUser;
    private double valueSalary;
    private int dayPayment;

    public Salary(long idUser, double valueSalary, int dayPayment) {
        this.idUser = idUser;
        this.valueSalary = valueSalary;
        this.dayPayment = dayPayment;
    }

    public long getIdUser() {
        return this.idUser;
    }

    public double getValueSalary() {
        return this.valueSalary;
    }

    public int getDayPayment() {
        return this.dayPayment;
    }
}
