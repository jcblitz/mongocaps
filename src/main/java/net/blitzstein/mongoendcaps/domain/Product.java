package net.blitzstein.mongoendcaps.domain;

import net.blitzstein.mongoendcaps.domain.Pricing;

/**
 *
 * @author Jared Blitzstein <blitzsteinj@gsicommerce.com>
 */
public class Product implements Comparable {

    private long productId;
    private String image;
    private String title;
    private Pricing pricing;
    private double reviewRating;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product(long productId, String image, String title, Pricing pricing, double reviewRating) {
        this.productId = productId;
        this.image = image;
        this.title = title;
        this.pricing = pricing;
        this.reviewRating = reviewRating;
    }

    public int compareTo(Object o) {
        Product comp = (Product) o;
        if (this.productId == comp.getProductId()) {
            return 0;
        } else if (this.productId > comp.getProductId()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return this.productId + ", " + this.image + ", " + this.title + ", " + this.pricing + ", " + this.reviewRating;
    }
}
