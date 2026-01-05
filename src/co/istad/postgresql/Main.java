package co.istad.postgresql;

import co.istad.postgresql.config.DatabaseConfig;
import co.istad.postgresql.model.Product;
import co.istad.postgresql.service.ProductService;
import co.istad.postgresql.service.ProductServiceImp;
import co.istad.postgresql.util.InputUtil;
import co.istad.postgresql.view.View;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig.init();
        ProductService productService = new ProductServiceImp();


        do {
            View.printMenu();

            int menu = InputUtil.getInteger("Enter menu option:");
            switch (menu){
                case 0 -> System.exit(0);
                case 1 -> {
                    try {
                        List<Product> productList = productService.findAll();
                        View.printProductList(productList);
                    } catch (RuntimeException e) {
                        View.printHeader(e.getMessage());
                    }
                }
                case 2 -> {}
                case 3 -> {
                    String code = InputUtil.getText("Enter product code: ");
                    String name = InputUtil.getText("Enter product name: ");
                    BigDecimal price = InputUtil.getMoney("Enter product price: ");
                    Integer qty = InputUtil.getInteger("Enter product qty: ");
                    Product product = new Product(code,name,price,qty,true);
                    try {
                        productService.save(product);
                        System.out.println("Product created successed");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                }
                default -> System.out.println("Invalid Option!!");
            }
        }while (true);

    }
}
