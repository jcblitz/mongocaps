package net.blitzstein.mongoendcaps.domain;

/**
 *
 * @author Jared Blitzstein <blitzsteinj@gsicommerce.com>
 */
public class Pricing {

    private double ourPrice;
    private double listPrice;

    public Pricing(double ourPrice, double listPrice) {
        this.ourPrice = ourPrice;
        this.listPrice = listPrice;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    @Override
    public String toString() {
        return "Our price: " + ourPrice + ", List price: " + listPrice;
    }
}
