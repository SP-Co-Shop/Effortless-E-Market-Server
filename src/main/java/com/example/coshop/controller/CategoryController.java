package com.example.coshop.controller;

import com.example.coshop.dto.category.CategoryRequest;
import com.example.coshop.dto.category.CategoryResult;
import com.example.coshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity createCategory(@RequestBody CategoryRequest request){
        categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/get/all")
    public ResponseEntity getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByAll());
    }
}
