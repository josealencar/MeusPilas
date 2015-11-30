package josealencar.com.br.meuspilas.dao;

import com.db4o.query.Predicate;

import java.util.ArrayList;
import java.util.List;

import josealencar.com.br.meuspilas.model.VariableAccount;

/**
 * Created by José on 25/06/2015.
 */
public class VariableAccountDao {
    private Db4oHelper db4o;

    public VariableAccountDao(Db4oHelper db4o){
        this.db4o = db4o;
    }

    //TODO: CRUD VariableAccount
    public void save(VariableAccount variableAccount) {
        db4o.db().store(variableAccount);
    }

    public VariableAccount findById(long id){
        return db4o.db().ext().getByID(id);
    }

    public List<VariableAccount> findByUserId(final long userId){
        return db4o.db().query(new Predicate<VariableAccount>() {
            @Override
            public boolean match(VariableAccount variableAccount) {
                return variableAccount.getIdUser() == userId;
            }
        });
    }

    public long getId(VariableAccount variableAccount) {
        return db4o.db().ext().getID(variableAccount);
    }

    public List<VariableAccount> findBetweenDays(final long userId, final int day, final int thisDay) {
        return db4o.db().ext().query(new Predicate<VariableAccount>() {
            @Override
            public boolean match(VariableAccount variableAccount) {
                return variableAccount.getIdUser() == userId && variableAccount.getDayPayment() > day && variableAccount.getDayPayment() <= thisDay;
            }
        });
    }
}
