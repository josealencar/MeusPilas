package josealencar.com.br.meuspilas.model;

/**
 * Created by José on 26/05/2015.
 */
public class Outcome {
    private int idUser;
    private double valueOutcome;
    private int monthOutcome;
    private String typeOutcome;

    public Outcome(int idUser, double valueOutcome, int monthOutcome, String typeOutcome) {
        this.idUser = idUser;
        this.valueOutcome = valueOutcome;
        this.monthOutcome = monthOutcome;
        this.typeOutcome = typeOutcome;
    }

    public int getIdUser() {
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
}
