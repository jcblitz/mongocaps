package net.blitzstein.mongoendcaps.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.net.UnknownHostException;

/**
 *
 * @author Jared Blitzstein 
 */
public class EndcapDaoImpl {

    private Mongo connection;

    public EndcapDaoImpl(String host, int port) throws UnknownHostException {
        connection = new Mongo(host, port);
    }

    public DBCollection getEncapCollection() {
        DB db = connection.getDB("test");
        return db.getCollection("endcaps");
    }
}
