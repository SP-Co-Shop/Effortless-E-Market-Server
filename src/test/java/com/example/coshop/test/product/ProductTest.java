package com.example.coshop.test.product;

import com.example.coshop.dto.product.ProductRequest;
import com.example.coshop.dto.seller.SellerRequest;
import com.example.coshop.service.ProductService;
import com.example.coshop.service.SellerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class ProductTest {

    private final SellerService sellerService;
    private final ProductService productService;

    @PersistenceContext
    EntityManager em;

    @Autowired
    public ProductTest(SellerService sellerService, ProductService productService) {
        this.sellerService = sellerService;
        this.productService = productService;
    }


    @Test
    @Rollback(value = false)
    @Transactional
    public void createProduct(){

        /* Seller 생성 */
        SellerRequest sellerRequest = new SellerRequest(
                "seller@gmail.com",
                "password",
                "seller",
                "010-0000-0000",
                "local"
        );
        sellerService.createSeller(sellerRequest);
        em.clear();

        ProductRequest productRequest = new ProductRequest(1L,"ItemA", 2000,"TEST ITEM",50,0);

        productService.createProduct(productRequest,"/create/product");

    }
}
