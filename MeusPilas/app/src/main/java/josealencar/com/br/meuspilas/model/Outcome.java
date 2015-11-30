package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class Outcome {
    private long idUser;
    private double valueOutcome;
    private int monthOutcome;
    private String typeOutcome;

    public Outcome(long idUser, double valueOutcome, int monthOutcome, String typeOutcome) {
        this.idUser = idUser;
        this.valueOutcome = valueOutcome;
        this.monthOutcome = monthOutcome;
        this.typeOutcome = typeOutcome;
    }

    public long getIdUser() {
        return this.idUser;
    }

    public double getValueOutcome() {
        return this.valueOutcome;
    }

    public int getMonthOutcome() {
        return this.monthOutcome;
    }

    public String getTypeOutcome() {
        return this.typeOutcome;
    }

    @Override
    public String toString() {
        return typeOutcome + " - R$" + valueOutcome;
    }
}
