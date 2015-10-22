package josealencar.com.br.meuspilas.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Created by José on 08/06/2015.
 */
public class Db4oHelper {

    private final String DB4O_FILE = "bd.db4o";

    private String dir;
    private ObjectContainer db;

    public Db4oHelper(String dir) {
        this.dir = dir;
    }

    public ObjectContainer db() {
        return db;
    }

    public void openConnection() {
        String dbFile = dir + DB4O_FILE;
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), dbFile);
    }

    public void closeConnection() {
        if (db != null) {
            db.close();
        }
    }
}
