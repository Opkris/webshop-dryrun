package no.kristiania.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}// ProductDao
