package josealencar.com.br.meuspilas.service;

import java.util.Calendar;
import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.VariableAccountDao;
import josealencar.com.br.meuspilas.model.Outcome;
import josealencar.com.br.meuspilas.model.VariableAccount;

/**
 * Created by jose on 26/11/15.
 */
public class VariableAccountService {
    private VariableAccountDao variableAccountDao;
    private OutcomeService outcomeService;

    public VariableAccountService(Db4oHelper db4o) {
        this.variableAccountDao = new VariableAccountDao(db4o);
        this.outcomeService = new OutcomeService(db4o);
    }

    public void save(VariableAccount variableAccount) {
        variableAccountDao.save(variableAccount);
    }

    public VariableAccount findById(final long id) {
        return variableAccountDao.findById(id);
    }

    public List<VariableAccount> findByUserId(final long userId) {
        return variableAccountDao.findByUserId(userId);
    }

    public long getId(VariableAccount variableAccount) {
        return variableAccountDao.getId(variableAccount);
    }

    public List<VariableAccount> findBetweenDays(final long userId, final int day, final int thisDay) {
        return variableAccountDao.findBetweenDays(userId, day, thisDay);
    }

    public void verifyNewEntry(final long userId, final int day, final int thisDay) {
        List<VariableAccount> variableAccounts = findBetweenDays(userId, day, thisDay);
        for (VariableAccount variableAccount: variableAccounts) {
            Outcome outcome = new Outcome(variableAccount.getIdUser(), variableAccount.getValueAccount(), Calendar.getInstance().get(Calendar.MONTH), variableAccount.getTypeAccount());
            outcomeService.save(outcome);
        }
    }
}
