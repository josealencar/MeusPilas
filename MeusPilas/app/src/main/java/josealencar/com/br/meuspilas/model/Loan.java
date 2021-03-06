package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class Loan {
    private long idUser;
    private double valueLoan;
    private int amountOfInstallments;
    private double interestRates;
    private int dayPayment;
    private int timeToPayment;
    private String beneficiaryName;
    private String typeBeneficiary;
    private int monthReceived;

    public Loan(long idUser, double valueLoan, int amountOfInstallments,
                double interestRates, int dayPayment, int timeToPayment, String beneficiaryName, String typeBeneficiary, int monthReceived) {
        this.idUser = idUser;
        this.valueLoan = valueLoan;
        this.amountOfInstallments = amountOfInstallments;
        this.interestRates = interestRates;
        this.dayPayment = dayPayment;
        this.timeToPayment = timeToPayment;
        this.beneficiaryName = beneficiaryName;
        this.typeBeneficiary = typeBeneficiary;
        this.monthReceived = monthReceived;
    }

    public long getIdUser() {
        return this.idUser;
    }

    public double getValueLoan() {
        return this.valueLoan;
    }

    public int getAmountOfInstallments() {
        return this.amountOfInstallments;
    }

    public double getInterestRates() {
        return this.interestRates;
    }

    public int getDayPayment() {
        return this.dayPayment;
    }

    public int getTimeToPayment() {
        return this.timeToPayment;
    }

    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public String getTypeBeneficiary() {
        return this.typeBeneficiary;
    }

    public int getMonthReceived() {
        return monthReceived;
    }
}
