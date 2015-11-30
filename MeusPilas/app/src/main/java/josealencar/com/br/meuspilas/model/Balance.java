package josealencar.com.br.meuspilas.model;

/**
 * Created by jose on 22/11/15.
 */
public class Balance {
    private String plusLess;
    private double value;
    private String description;

    public Balance(String plusLess, double value, String description) {
        this.plusLess = plusLess;
        this.value = value;
        this.description = description;
    }

    public String getPlusLess() {
        return plusLess;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description + " " + plusLess + " R$" + value;
    }
}
