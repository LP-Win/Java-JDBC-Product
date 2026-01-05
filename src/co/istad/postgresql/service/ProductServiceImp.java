package co.istad.postgresql.service;

import co.istad.postgresql.dao.ProductDao;
import co.istad.postgresql.dao.ProductDaoImp;
import co.istad.postgresql.model.Product;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImp implements ProductService{
    private final ProductDao productDao;

    public ProductServiceImp() {
        productDao = new ProductDaoImp();
    }

    @Override
    public void save(Product product) throws SQLException {
        try {
            int affectedRow = productDao.save(product);
            if (affectedRow < 1){
                throw new RuntimeException("Inser new record failed");
            }
        }catch (SQLException e){
            System.out.println("SQL errored: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<Product> findAll() {
        try {
            return productDao.findAll();
        } catch (SQLException e) {
            System.out.println("SQL errored: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
