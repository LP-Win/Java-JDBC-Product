package co.istad.postgresql.dao;

import co.istad.postgresql.model.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Boolean existsByCode(String code) throws SQLException;

    int deleteByCode(String code) throws SQLException;

    Optional<Product> findBycode (String code) throws SQLException;

    int updateByCode(String code, Product product) throws SQLException;

    int save (Product product) throws SQLException;

    // create

    // 1. Read all records
    // 2. Expected return value -> return type
    // 3. Parameters
    List<Product> findAll() throws SQLException;

}
