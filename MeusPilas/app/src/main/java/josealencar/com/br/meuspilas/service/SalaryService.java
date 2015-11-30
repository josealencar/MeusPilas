package josealencar.com.br.meuspilas.service;

import java.util.Calendar;
import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.IncomeDao;
import josealencar.com.br.meuspilas.dao.SalaryDao;
import josealencar.com.br.meuspilas.model.Income;
import josealencar.com.br.meuspilas.model.Salary;

/**
 * Created by jose on 25/11/15.
 */
public class SalaryService {
    private static final String SALARY = "Salário";
    private SalaryDao salaryDao;
    private IncomeService incomeService;

    public SalaryService(Db4oHelper db4o) {
        this.salaryDao = new SalaryDao(db4o);
        this.incomeService = new IncomeService(db4o);
    }

    public void save(final Salary salary) {
        salaryDao.save(salary);
    }

    public Salary findById(final long id) {
        return salaryDao.findById(id);
    }

    public List<Salary> findByIdUser(final long userId) {
        return salaryDao.findByIdUser(userId);
    }

    public List<Salary> findBetweenDays(final long userId, final int day, final int thisDay) {
        return salaryDao.findBetweenDays(userId, day, thisDay);
    }

    public long getId(Salary salary) {
        return salaryDao.getId(salary);
    }

    public void verifyNewEntry(final long userId, final int day, final int thisDay) {
        List<Salary> salaries = findBetweenDays(userId, day, thisDay);
        for (Salary salary : salaries) {
            Income income = new Income(salary.getIdUser(), salary.getValueSalary(), Calendar.getInstance().get(Calendar.MONTH), SALARY);
            incomeService.save(income);
        }
    }
}
