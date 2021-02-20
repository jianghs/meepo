package me.jianghs.meepo.并发包;

import java.io.Serializable;

/**
 * @className: ProductPrice
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/20 14:19
 * @version: 1.0
 */
public class ProductPrice implements Serializable {
    private int prodId;

    private double price;

    public ProductPrice(int prodId) {
        this(prodId, -1);
    }

    public ProductPrice(int prodId, double price) {
        this.prodId = prodId;
        this.price = price;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "prodId=" + prodId +
                ", price=" + price +
                '}';
    }
}
