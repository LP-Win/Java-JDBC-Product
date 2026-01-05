package co.istad.postgresql.dao;

import co.istad.postgresql.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    int save (Product product) throws SQLException;

    // create
    List<Product> findAll() throws SQLException;

}
