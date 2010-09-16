package net.blitzstein.mongoendcaps;

import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.blitzstein.mongoendcaps.dao.EndcapDaoImpl;
import net.blitzstein.mongoendcaps.factory.ProductFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jared Blitzstein <blitzsteinj@gsicommerce.com>
 */
public class MongoRunner {

    public static void main(String[] args) throws UnknownHostException, JSONException {
        ProductFactory productFactory = new ProductFactory();
        List<Endcap> endcapsDisplay = new ArrayList();
        EndcapDaoImpl daoImpl = new EndcapDaoImpl("localhost", 27017);

        DBCollection dBCollection = daoImpl.getEncapCollection();

        DBObject record = getEndcapsForCategoryId(dBCollection);
        JSONObject endcapRow = new JSONObject(record.toString());

        Endcap ec = new Endcap();

        JSONArray endcapData = endcapRow.getJSONArray("data");
        for (int i = 0; i < endcapData.length(); i++) {
            JSONObject endcap = endcapData.getJSONObject(i);

            //There is only one key, which is the type of endcap it is
            //This should be restructured, I don't like it
            String endcapKey = endcap.keys().next().toString();
            System.out.println("Endcap:" + endcapKey);

            Set<Product> products = new HashSet();
            JSONArray productRecordData = endcap.getJSONArray(endcapKey);
            for (int j = 0; j < productRecordData.length(); j++) {
                products.add(productFactory.getProductFromJSONProduct(productRecordData.getJSONObject(j)));
            }

            ec.setProducts(products);

            endcapsDisplay.add(ec);

        }

        System.out.println("Endcaps: " + endcapsDisplay.size());

    }

    private static DBObject getEndcapsForCategoryId(DBCollection dBCollection) {
        BasicDBObject query = new BasicDBObject();
        query.put("categoryId", 2465428);
        DBCursor cur = dBCollection.find(query);
        DBObject record = null;
        while (cur.hasNext()) {
            record = cur.next();
        }
        return record;
    }
}
