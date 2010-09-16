package net.blitzstein.mongoendcaps.factory;

import net.blitzstein.mongoendcaps.Pricing;
import net.blitzstein.mongoendcaps.Product;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jared Blitzstein <blitzsteinj@gsicommerce.com>
 */
public class ProductFactory {

    public Product getProductFromJSONProduct(JSONObject product) throws JSONException {

        Pricing pricing = new Pricing(product.optDouble("ourPrice"), product.getDouble("listPrice"));
        Product prod = new Product(product.getLong("productId"), product.getString("productImage"), product.getString("productTitle"), pricing, product.getDouble("powerReviewRating"));

        return prod;
    }
}
