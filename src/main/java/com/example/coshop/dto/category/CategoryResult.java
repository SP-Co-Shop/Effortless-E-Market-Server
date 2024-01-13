package com.example.coshop.dto.category;

import com.example.coshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResult {

    private Long id;
    private String name;
    private Long depth;
    private List<CategoryResult> children;

    public static CategoryResult of(Category category){
        return new CategoryResult(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getChild().stream().map(CategoryResult::of).collect(Collectors.toList())
        );
    }
}
