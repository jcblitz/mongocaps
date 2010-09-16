package net.blitzstein.mongoendcaps;

import net.blitzstein.mongoendcaps.domain.Product;
import net.blitzstein.mongoendcaps.domain.Endcap;
import com.mongodb.BasicDBObject;
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
 * A simple proof of concept using MongoDB to store and pull back some product info and convert it to a Java object
 * @author Jared Blitzstein 
 */
public class MongoRunner {

    public static void main(String[] args) throws UnknownHostException, JSONException {
        ProductFactory productFactory = new ProductFactory();
        List<Endcap> endcapsDisplay = new ArrayList();

        JSONObject endcapRow = getEndcapsForCategoryId(2465428);

        JSONArray endcapData = endcapRow.getJSONArray("data");
        for (int i = 0; i < endcapData.length(); i++) {
            Endcap ec = new Endcap();
            JSONObject endcap = endcapData.getJSONObject(i);

            Set<Product> products = new HashSet();
            System.out.println("Getting data for: " + endcap.getString("name"));
            JSONArray productRecordData = endcap.getJSONArray("products");
            for (int j = 0; j < productRecordData.length(); j++) {
                products.add(productFactory.getProductFromJSONProduct(productRecordData.getJSONObject(j)));
            }

            ec.setProducts(products);

            endcapsDisplay.add(ec);

        }

        //Display the results
        for (Endcap endcap : endcapsDisplay) {
            Set<Product> products = endcap.getProducts();
            System.out.println("---");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private static JSONObject getEndcapsForCategoryId(long categoryId) throws UnknownHostException, JSONException {
        EndcapDaoImpl daoImpl = new EndcapDaoImpl("localhost", 27017);
        DBCollection dBCollection = daoImpl.getEncapCollection();
        BasicDBObject query = new BasicDBObject();
        query.put("categoryId", categoryId);
        DBCursor cur = dBCollection.find(query);
        DBObject record = null;

        //I don't like this, but Hibernate works the same way
        while (cur.hasNext()) {
            record = cur.next();
        }

        return new JSONObject(record.toString());
    }
}
