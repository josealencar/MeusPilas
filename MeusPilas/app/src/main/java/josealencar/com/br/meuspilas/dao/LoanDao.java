package josealencar.com.br.meuspilas.dao;

import com.db4o.query.Predicate;

import java.util.List;

import josealencar.com.br.meuspilas.model.Loan;

/**
 * Created by José on 25/06/2015.
 */
public class LoanDao {
    private Db4oHelper db4o;

    public LoanDao(Db4oHelper db4o){
        this.db4o = db4o;
    }

    //TODO: CRUD Loan
    public void save(Loan loan) {
        db4o.db().store(loan);
    }

    public Loan findById(long id) {
        return db4o.db().ext().getByID(id);
    }

    public List<Loan> findByIdUser(final long idUser) {
        return db4o.db().query(new Predicate<Loan>() {
            @Override
            public boolean match(Loan loan) {
                return loan.getIdUser() == idUser;
            }
        });
    }

    public long getId(Loan loan) {
        return db4o.db().ext().getID(loan);
    }
}
