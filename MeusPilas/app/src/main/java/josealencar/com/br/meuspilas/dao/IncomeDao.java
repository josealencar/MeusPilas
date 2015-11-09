package josealencar.com.br.meuspilas.dao;

import com.db4o.query.Predicate;

import java.util.Calendar;
import java.util.List;

import josealencar.com.br.meuspilas.model.Income;

/**
 * Created by José on 25/06/2015.
 */
public class IncomeDao {
    private Db4oHelper db4o;

    public IncomeDao(Db4oHelper db4o){
        this.db4o = db4o;
    }

    //TODO : CRUD Income
    public void save(Income income) {
        db4o.db().ext().store(income);
    }

    public Income findById(Long id) {
        return db4o.db().ext().getByID(id);
    }

    public List<Income> findByIdUser(final Long idUser) {
        return db4o.db().query(new Predicate<Income>() {
            @Override
            public boolean match(Income income) {
                return income.getIdUser() == idUser && income.getMonthIncome() == Calendar.MONTH;
            }
        });
    }
}
