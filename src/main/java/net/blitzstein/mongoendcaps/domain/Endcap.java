
package net.blitzstein.mongoendcaps.domain;

import java.util.Set;

/**
 *
 * @author Jared Blitzstein 
 */
public class Endcap {
    private long categoryId;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    

}
