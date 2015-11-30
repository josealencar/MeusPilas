package josealencar.com.br.meuspilas.service;

import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.LoanDao;
import josealencar.com.br.meuspilas.model.Income;
import josealencar.com.br.meuspilas.model.Loan;

/**
 * Created by jose on 25/11/15.
 */
public class LoanService {
    private static final String LOAN = "Empréstimo";
    private LoanDao loanDao;
    private IncomeService incomeService;
    private OutcomeService outcomeService;

    public LoanService(Db4oHelper db4o) {
        this.loanDao = new LoanDao(db4o);
        this.incomeService = new IncomeService(db4o);
        this.outcomeService = new OutcomeService(db4o);
    }

    public void save(Loan loan) {
        loanDao.save(loan);
        incomeService.save(new Income(loan.getIdUser(), loan.getValueLoan(), loan.getMonthReceived(), LOAN));
    }

    public Loan findById(final long id) {
        return loanDao.findById(id);
    }

    public List<Loan> findByIdUser(final long userId) {
        return loanDao.findByIdUser(userId);
    }

    public long getId(Loan loan) {
        return loanDao.getId(loan);
    }

    public void verifyNewEntry(final long userId, final int day, final int thisDay) {
        //TODO: Outcomes rotine for Loan
    }
}
