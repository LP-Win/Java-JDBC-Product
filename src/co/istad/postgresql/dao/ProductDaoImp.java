package co.istad.postgresql.dao;

import co.istad.postgresql.config.DatabaseConfig;
import co.istad.postgresql.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImp implements ProductDao{
    private final Connection conn;

    public ProductDaoImp(){
        conn = DatabaseConfig.getConn();
    }

    @Override
    public Boolean existsByCode(String code) throws SQLException {
        String sql = """
                SELECT EXISTS(  SELETE *
                                FROM product
                                WHERE code = ? )
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, code);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
            return rs.getBoolean("exists");

        return false;
    }

    @Override
    public int deleteByCode(String code) throws SQLException {

        String sql = """
                DELETE
                FROM product
                WHERE code = ?
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,code);

        return pstmt.executeUpdate();
    }

    @Override
    public Optional<Product> findBycode(String code) throws SQLException {

        String sql = """
                SELECT *
                FROM product
                WHERE code = ?
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, code);

        ResultSet rs  = pstmt.executeQuery();
        Product product;

        if(rs.next()){
            product = new Product();
            product.setId(rs.getInt("id"));
            product.setCode(rs.getString("code"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQty(rs.getInt("qty"));
            product.setStatus(rs.getBoolean("status"));
            return Optional.of(product);
        }

        return Optional.empty();
    }

    @Override
    public int updateByCode(String code, Product product) throws SQLException {

        String sql = """
                UPDATE product
                SET name = ?, price = ?, qty = ?
                WHERE code = ?
                """;

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, product.getName());
                pstmt.setBigDecimal(2, product.getPrice());
                pstmt.setInt(3, product.getQty());
                pstmt.setString(4, product.getCode());

        return pstmt.executeUpdate();
    }

    @Override
    public int save(Product product) throws SQLException{

        String sql = """
                INSERT INTO product (code, name, price, qty, status)
                VALUES (?,?,?,?,?)
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, product.getCode());
        pstmt.setString(2, product.getName());
        pstmt.setBigDecimal(3, product.getPrice());
        pstmt.setInt(4, product.getQty());
        pstmt.setBoolean(5, product.getStatus());

        return pstmt.executeUpdate();
    }

    @Override
public List<Product> findAll() throws SQLException {

        Statement stmt = conn.createStatement();

        String sql = """
                SELECT *
                FROM product
                """;

        ResultSet rs = stmt.executeQuery(sql);
        List<Product> products = new ArrayList<>();

        while(rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setCode(rs.getString("code"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQty(rs.getInt("qty"));
            product.setStatus(rs.getBoolean("status"));

            products.add(product);
        }
        return products;
    }

}
