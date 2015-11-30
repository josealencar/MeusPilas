package josealencar.com.br.meuspilas.service;

import java.util.Calendar;
import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.FixedAccountDao;
import josealencar.com.br.meuspilas.dao.OutcomeDao;
import josealencar.com.br.meuspilas.model.FixedAccount;
import josealencar.com.br.meuspilas.model.Outcome;

/**
 * Created by jose on 25/11/15.
 */
public class FixedAccountService {
    private FixedAccountDao fixedAccountDao;
    private OutcomeService outcomeService;

    public FixedAccountService(final Db4oHelper db4o) {
        this.fixedAccountDao = new FixedAccountDao(db4o);
        this.outcomeService = new OutcomeService(db4o);
    }

    public void save(FixedAccount fixedAccount) {
        fixedAccountDao.save(fixedAccount);
    }

    public FixedAccount findById(final long id) {
        return fixedAccountDao.findById(id);
    }

    public List<FixedAccount> findByUserId(final long userId) {
        return fixedAccountDao.findByUserId(userId);
    }

    public List<FixedAccount> findBetweenDays(final long userId, final int day, final int thisDay) {
        return fixedAccountDao.findBetweenDays(userId, day, thisDay);
    }

    public long getId(FixedAccount fixedAccount) {
        return fixedAccountDao.getId(fixedAccount);
    }

    public void verifyNewEntry(long userId, int day, int thisDay) {
        List<FixedAccount> fixedAccounts = findBetweenDays(userId, day, thisDay);
        for (FixedAccount fixedAccount : fixedAccounts) {
            Outcome outcome = new Outcome(userId, fixedAccount.getValueAccount(), Calendar.MONTH, fixedAccount.getTypeAccount());
            outcomeService.save(outcome);
        }
    }
}
