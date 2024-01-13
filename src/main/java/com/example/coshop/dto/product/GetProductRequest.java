package com.example.coshop.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
public class GetProductRequest {

    private long userId;
    private long productId;

}
