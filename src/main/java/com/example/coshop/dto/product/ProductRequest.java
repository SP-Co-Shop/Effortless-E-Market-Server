package com.example.coshop.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequest {

    @NotNull
    private Long sellerId;
    @NotBlank
    private String name;
    @NotNull
    private int price;

    private String description;

    @NotNull
    private int quantity;

    @NotNull
    private int isOpen;

}
