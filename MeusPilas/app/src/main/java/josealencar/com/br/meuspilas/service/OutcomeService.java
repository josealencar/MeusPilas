package josealencar.com.br.meuspilas.service;

import java.util.List;

import josealencar.com.br.meuspilas.model.Outcome;

/**
 * Created by jose on 07/11/15.
 */
public class OutcomeService {

    public double getSumOutcome(List<Outcome> outcomes) {
        double sum = 0;
        for (Outcome o : outcomes) {
            sum += o.getValueOutcome();
        }
        return sum;
    }
}
