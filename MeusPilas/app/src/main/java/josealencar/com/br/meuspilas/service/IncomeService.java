package josealencar.com.br.meuspilas.service;

import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.IncomeDao;
import josealencar.com.br.meuspilas.model.Income;

/**
 * Created by jose on 07/11/15.
 */
public class IncomeService {
    private IncomeDao incomeDao;

    public IncomeService(Db4oHelper db4o) {
        this.incomeDao = new IncomeDao(db4o);
    }

    public void save(Income income) {
        incomeDao.save(income);
    }

    public Income findById(final long id) {
        return incomeDao.findById(id);
    }

    public List<Income> findByIdUser(final long userId) {
        return incomeDao.findByIdUser(userId);
    }

    public long getId(Income income) {
        return incomeDao.getId(income);
    }

    public double getSumIncome(List<Income> incomes) {
        double sum = 0;
        for (Income i : incomes) {
            sum += i.getValueIncome();
        }
        return sum;
    }
}
