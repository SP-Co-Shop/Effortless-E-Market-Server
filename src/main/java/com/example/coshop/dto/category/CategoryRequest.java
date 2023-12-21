package com.example.coshop.dto.category;

import com.example.coshop.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryRequest {

    private String name;
    private Long parentId;
}
