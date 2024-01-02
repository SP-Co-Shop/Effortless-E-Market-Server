package com.example.coshop.service;


import com.example.coshop.Entity.Product;
import com.example.coshop.Entity.Seller;
import com.example.coshop.dto.product.ProductRequest;
import com.example.coshop.repository.product.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerService sellerService;

    private static Logger log = LoggerFactory.getLogger("dc-logger");

    /* 상품 추가 */
    public ResponseEntity createProduct(ProductRequest request, String url){
        HttpServletRequest request1 = null;
        Seller seller = sellerService.findByIdToSeller(request.getSellerId());
        Product product = productBuilder(seller, request);
        productRepository.save(product);



        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private Product productBuilder(Seller seller, ProductRequest request) {
        return  new Product(
                seller,
                request.getName(),
                request.getPrice(),
                request.getDescription(),
                request.getQuantity(),
                request.getIsOpen()
        );
    }

}
