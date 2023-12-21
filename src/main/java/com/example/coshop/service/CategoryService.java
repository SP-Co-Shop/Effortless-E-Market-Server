package com.example.coshop.service;

import com.example.coshop.Entity.Category;
import com.example.coshop.dto.category.CategoryRequest;
import com.example.coshop.dto.category.CategoryResult;
import com.example.coshop.repository.category.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    /* 카테고리 생성 */
    @Transactional
    public void createCategory(CategoryRequest request){

        /* 카테고리 조회 */
        Category parentCategory = Optional.ofNullable(request.getParentId())
                .map(id -> categoryRepository.findById(id).orElseThrow())
                .orElse(null);

        if (parentCategory == null){
            categoryRepository.save(new Category(request.getName()));
        }else {
            categoryRepository.save(new Category(request.getName(),parentCategory));
        }




    }

    /* 카테고리 조회 */
    public List<CategoryResult> findByAll(){
        List<CategoryResult> results = categoryRepository.findAll().stream().map(CategoryResult::of).collect(Collectors.toList());
        return results;
    }

}
