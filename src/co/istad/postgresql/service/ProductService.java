package co.istad.postgresql.service;

import co.istad.postgresql.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    void save (Product product) throws SQLException;

    List<Product> findAll();

}
