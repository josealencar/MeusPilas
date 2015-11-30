package josealencar.com.br.meuspilas.service;

import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.UserDao;
import josealencar.com.br.meuspilas.model.User;

/**
 * Created by jose on 26/11/15.
 */
public class UserService {
    private UserDao userDao;

    public UserService(Db4oHelper db4o) {
        this.userDao = new UserDao(db4o);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User findById(final long id) {
        return userDao.findById(id);
    }

    public List<User> findByEmail(final String email) {
        return userDao.findByEmail(email);
    }

    public long getId(final User user) {
        return userDao.getId(user);
    }
}
