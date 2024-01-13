package com.example.coshop.service;


import com.example.coshop.dto.product.GetProductRequest;
import com.example.coshop.entity.Product;
import com.example.coshop.entity.Seller;
import com.example.coshop.dto.product.CreateProductRequest;
import com.example.coshop.repository.product.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity createProduct(CreateProductRequest request, String url){
        HttpServletRequest request1 = null;
        Seller seller = sellerService.findByIdToSeller(request.getSellerId());
        Product product = productBuilder(seller, request);
        productRepository.save(product);



        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 상품 조회 */
    public ResponseEntity getProduct(GetProductRequest request){
        Product product;

        try {
             product =  productRepository.findById(request.getProductId()).get();

            if (product != null){
                return ResponseEntity.status(HttpStatus.OK).body(product);
            }
        } catch (Exception e) {
            log.error("HTTP Error : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return null;
    }

    private Product productBuilder(Seller seller, CreateProductRequest request) {
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
