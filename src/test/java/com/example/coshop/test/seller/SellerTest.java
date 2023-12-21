package com.example.coshop.test.seller;

import com.example.coshop.dto.seller.SellerRequest;
import com.example.coshop.service.SellerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SellerTest {

    private final SellerService sellerService;


    @Autowired
    public SellerTest(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Test
    @DisplayName("판매자 생성")
    public void createSeller(){

        SellerRequest request = new SellerRequest(
                "seller@gmail.com",
                "password",
                "seller",
                "010-0000-0000",
                "local"
        );
        sellerService.createSeller(request);
    }


}
