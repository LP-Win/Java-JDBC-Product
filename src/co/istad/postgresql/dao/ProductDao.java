package co.istad.postgresql.dao;

import co.istad.postgresql.model.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    int save (Product product) throws SQLException;

    // create

    // 1. Read all records
    // 2. Expected return value -> return type
    // 3. Parameters
    List<Product> findAll() throws SQLException;

}
