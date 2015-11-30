package josealencar.com.br.meuspilas.dao;

import com.db4o.query.Predicate;

import java.util.ArrayList;
import java.util.List;

import josealencar.com.br.meuspilas.model.FixedAccount;

/**
 * Created by José on 25/06/2015.
 */
public class FixedAccountDao {
    private Db4oHelper db4o;

    public FixedAccountDao(Db4oHelper db4o){
        this.db4o = db4o;
    }

    //TODO : CRUD FixedAccount
    public void save(final FixedAccount fixedAccount) {
        db4o.db().store(fixedAccount);
    }

    public FixedAccount findById(final long id){
        return db4o.db().ext().getByID(id);
    }

    public List<FixedAccount> findByUserId(final long userId){
        try {
            return db4o.db().query(new Predicate<FixedAccount>() {
                @Override
                public boolean match(FixedAccount fixedAccount) {
                    return fixedAccount.getIdUser() == userId;
                }
            });
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public List<FixedAccount> findBetweenDays(final long userId, final int firtDay, final int lastDay) {
        return db4o.db().ext().query(new Predicate<FixedAccount>() {
            @Override
            public boolean match(FixedAccount fixedAccount) {
                return fixedAccount.getIdUser() == userId && fixedAccount.getDayPayment() > firtDay && fixedAccount.getDayPayment() <= lastDay;
            }
        });
    }

    public long getId(FixedAccount fixedAccount) {
        return db4o.db().ext().getID(fixedAccount);
    }
}
