package josealencar.com.br.meuspilas.dao;

import com.db4o.query.Predicate;

import java.util.List;

import josealencar.com.br.meuspilas.model.ServiceHistory;

/**
 * Created by jose on 25/11/15.
 */
public class ServiceHistoryDao {
    private Db4oHelper db4o;

    public ServiceHistoryDao(Db4oHelper db4o) {
        this.db4o = db4o;
    }

    //TODO: ServiceHistory CRUD
    public void save(ServiceHistory serviceHistory) {
        db4o.db().store(serviceHistory);
    }

    public ServiceHistory findById(long id) {
        return db4o.db().ext().getByID(id);
    }

    public List<ServiceHistory> findByUserId(final long userId) {
        return db4o.db().ext().query(new Predicate<ServiceHistory>() {
            @Override
            public boolean match(ServiceHistory serviceHistory) {
                return serviceHistory.getUserId() == userId;
            }
        });
    }

    public long getId(ServiceHistory serviceHistory) {
        return db4o.db().ext().getID(serviceHistory);
    }
}
