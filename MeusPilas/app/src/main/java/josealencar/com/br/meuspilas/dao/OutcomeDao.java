package josealencar.com.br.meuspilas.dao;

import com.db4o.query.Predicate;

import java.util.Calendar;
import java.util.List;

import josealencar.com.br.meuspilas.model.Outcome;

/**
 * Created by José on 25/06/2015.
 */
public class OutcomeDao {
    private Db4oHelper db4o;

    public OutcomeDao(Db4oHelper db4o) {
        this.db4o = db4o;
    }

    //TODO: CRUD Outcome
    public void save(Outcome outcome) {
        db4o.db().ext().store(outcome);
    }

    public Outcome findById(long id) {
        return db4o.db().ext().getByID(id);
    }

    public List<Outcome> findByIdUser(final long idUser) {
        return db4o.db().query(new Predicate<Outcome>() {
            @Override
            public boolean match(Outcome outcome) {
                return outcome.getIdUser() == idUser && outcome.getMonthOutcome() == Calendar.MONTH;
            }
        });
    }
}
