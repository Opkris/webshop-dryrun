package no.kristiania.jdbc;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private List<String> products = new ArrayList<>();

    public void insertProduct(String productName) {
        products.add(productName);
    }


    public List<String>listAll() {
        return products;
    }
}// ProductDao
