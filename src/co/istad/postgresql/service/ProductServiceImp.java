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
    public void deleteByCode(String code) {
        try {
            if(!productDao.existsByCode(code))
                throw new RuntimeException("Product doesn't exist..!");
        } catch (SQLException e){
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateByCode(String code, Product product) {

        try {
            Product foundProduct = productDao.findBycode(code)
                    .orElseThrow(() -> new RuntimeException("Product doesn't exist..!"));
            if (!product.getName().isBlank())
                foundProduct.setName(product.getName());

            if (product.getPrice() != null)
                foundProduct.setPrice(product.getPrice());

            if (product.getQty() != null)
                foundProduct.setQty(product.getQty());

            int affectiveRow = productDao.updateByCode(code, foundProduct);
            if(affectiveRow < 1)
                throw new RuntimeException("Update operation failed..!");

        }catch (SQLException e){
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

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
