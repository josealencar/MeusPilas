package josealencar.com.br.meuspilas.dao;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import java.util.ArrayList;
import java.util.List;

import josealencar.com.br.meuspilas.model.User;

/**
 * Created by José on 25/06/2015.
 */
public class UserDao {
    private Db4oHelper db4o;

    public UserDao(Db4oHelper db4o){
        this.db4o = db4o;
    }

    // TODO : CRUD User
    public void save(User u) {
        db4o.db().store(u);
    }

    public long getId(User u) {
        return db4o.db().ext().getID(u);
    }

    public List<User> findByEmail(final String email) {
        ObjectSet<User> users = db4o.db().query(new Predicate<User>() {
            @Override
            public boolean match(User user) {
                return user.getEmailUser().toLowerCase().contains(email.toLowerCase());
            }
        });
        return new ArrayList<>(users);
    }

    public User findById(Long id) {
        return db4o.db().ext().getByID(id);
    }
}
