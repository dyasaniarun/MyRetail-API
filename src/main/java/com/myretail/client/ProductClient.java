
package com.myretail.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductClient {

    @SerializedName("product")
    @Expose
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
