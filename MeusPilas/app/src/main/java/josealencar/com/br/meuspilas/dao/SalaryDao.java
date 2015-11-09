package josealencar.com.br.meuspilas.dao;

import com.db4o.query.Predicate;

import java.util.List;

import josealencar.com.br.meuspilas.model.Salary;

/**
 * Created by José on 25/06/2015.
 */
public class SalaryDao {
    private Db4oHelper db4o;

    public SalaryDao(Db4oHelper db4o){
        this.db4o = db4o;
    }

    public void save(Salary salary) {
        db4o.db().store(salary);
    }

    public Salary findById(long id) {
        return db4o.db().ext().getByID(id);
    }

    public List<Salary> findByIdUser(final long idUser) {
        return db4o.db().query(new Predicate<Salary>() {
            @Override
            public boolean match(Salary salary) {
                return salary.getIdUser() == idUser;
            }
        });
    }
}
