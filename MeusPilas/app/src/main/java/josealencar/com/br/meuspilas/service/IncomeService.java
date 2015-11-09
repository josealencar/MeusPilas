package josealencar.com.br.meuspilas.service;

import java.util.List;

import josealencar.com.br.meuspilas.model.Income;

/**
 * Created by jose on 07/11/15.
 */
public class IncomeService {

    public double getSumIncome(List<Income> incomes) {
        double sum = 0;
        for (Income i : incomes) {
            sum += i.getValueIncome();
        }
        return sum;
    }
}
