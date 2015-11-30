package josealencar.com.br.meuspilas.service;

import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.OutcomeDao;
import josealencar.com.br.meuspilas.model.Outcome;

/**
 * Created by jose on 07/11/15.
 */
public class OutcomeService {
    private OutcomeDao outcomeDao;

    public OutcomeService(Db4oHelper db4o) {
        this.outcomeDao = new OutcomeDao(db4o);
    }

    public void save(Outcome outcome) {
        outcomeDao.save(outcome);
    }

    public Outcome findById(final long id) {
        return outcomeDao.findById(id);
    }

    public List<Outcome> findByIdUser(final long userId) {
        return outcomeDao.findByIdUser(userId);
    }

    public long getId(Outcome outcome) {
        return outcomeDao.getId(outcome);
    }

    public double getSumOutcome(List<Outcome> outcomes) {
        double sum = 0;
        for (Outcome o : outcomes) {
            sum += o.getValueOutcome();
        }
        return sum;
    }
}
