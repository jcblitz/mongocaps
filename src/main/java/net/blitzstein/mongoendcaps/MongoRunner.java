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

        Mongo m = new Mongo("localhost", 27017);
        DB db = m.getDB("test");

        List<Endcap> endcapsDisplay = new ArrayList();

        DBCollection dBCollection = db.getCollection("endcaps");

        BasicDBObject query = new BasicDBObject();
        query.put("categoryId", 2465428);
        DBCursor cur = dBCollection.find(query);
        DBObject record = null;
        while (cur.hasNext()) {
            record = cur.next();
        }


        JSONObject endcapRow = new JSONObject(record.toString());

        Endcap ec = new Endcap();

        JSONArray endcapData = endcapRow.getJSONArray("data");
        for (int i = 0; i < endcapData.length(); i++) {
//            System.out.println(endcaps.get(i).getClass());
            JSONObject endcap = endcapData.getJSONObject(i);
//            System.out.println(endcap);

            //There is only one key, which is the type of endcap it is
            //This should be restructured, I don't like it
            String endcapKey = endcap.keys().next().toString();
            System.out.println("Endcap:" + endcapKey);

            Set<Product> products = new HashSet();
            JSONArray data = endcap.getJSONArray(endcapKey);
            for (int j = 0; j < data.length(); j++) {
                products.add(productFactory.getProductFromJSONProduct(data.optJSONObject(j)));
            }

            ec.setProducts(products);

            endcapsDisplay.add(ec);

        }



        System.out.println("Endcaps: " + endcapsDisplay.size());

    }
}
