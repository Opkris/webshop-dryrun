package no.kristiania.jdbc;


import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;


public class WebShop {
private PGSimpleDataSource dataSource;
private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private ProductDao productDao;
    private OrderDao orderDao;

    public WebShop() throws IOException {
    Properties properties = new Properties();
    properties.load(new FileReader("webshop.properties"));

    dataSource = new PGSimpleDataSource();

    dataSource.setUrl("jdbc:postgresql://localhost:5433/mywebshopdb");
    dataSource.setUser("webshop");
    dataSource.setPassword(properties.getProperty("dataSource.password"));
    productDao = new ProductDao(dataSource);
    orderDao = new OrderDao(dataSource);

}


    public static void main(String[] args) throws IOException, SQLException {
    new WebShop().run();
    }

    private void run() throws IOException, SQLException {
        System.out.println("Choose action: create [product] | create [order]");
        String action = input.readLine();

        switch (action.toLowerCase()){
            case "product":
                insertProduct();
                break;
            case "order":
                insertOrder();
                break;
        }

        System.out.println("Current products " + productDao.listAll());

    }

    private void insertOrder() throws IOException, SQLException {
        System.out.println("Please type the name of a new order");
        Order order = new Order();

        OrderDao orderDao = new OrderDao(dataSource);
        order.setName(input.readLine());
        orderDao.insert(order);
    }

    private void insertProduct() throws IOException, SQLException {
        System.out.println("Please type the name of a new product");
        String productName = input.readLine();
        productDao.insert(productName);
    }

}
