package josealencar.com.br.meuspilas.dao;

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
    public void inserir(User u) {
        db4o.db().store(u);
    }
}
