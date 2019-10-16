package no.kristiania.jdbc;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao extends AbstractDao <String> {

    public ProductDao(DataSource dataSource){
        super(dataSource);
    }

    @Override
    protected void insertObject(String product, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, product);
    }

    @Override
    protected String readObject(ResultSet rs) throws SQLException {
        return rs.getString("name");
    }

    public void insert(String product) throws SQLException {
        insert(product, "insert into products (name) values(?)");
    }

    public List<String> listAll() throws SQLException {
        return listAll("select * from products");
    }

    /*
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
    */



}// ProductDao
