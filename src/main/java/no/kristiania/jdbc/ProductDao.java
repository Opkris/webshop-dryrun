package no.kristiania.jdbc;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ProductDao {

    private DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertProduct(String productName) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement(
                    "insert into products (name) values (?)"
            );
            statement.setString(1, productName);
            statement.executeUpdate();
        }
    }



    public List<String>listAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select * from products"
            )) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<String> result = new ArrayList<>();

                    while(rs.next()){
                        result.add(rs.getString("name"));
                    }
                    return result;
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Enter a Product name to insert: ");
        String productName = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("webshop.properties"));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5433/webshop");
        dataSource.setUser("webshop");
        dataSource.setPassword("_t=GW4&*V4=~Sp42EW>M");
        ProductDao productDao = new ProductDao(dataSource);
        productDao.insertProduct(productName);

        System.out.println(productDao.listAll());

    }// end main
}// ProductDao
