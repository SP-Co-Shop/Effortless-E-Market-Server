package com.example.coshop.service;

import com.example.coshop.Entity.Category;
import com.example.coshop.dto.category.CategoryRequest;
import com.example.coshop.dto.category.CategoryResult;
import com.example.coshop.repository.category.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
            /* 부모 카테고리 제일 최상위 카테고리의 경우 */
            Category category = new Category(request.getName());
            categoryRepository.save(category);
        }else {
            System.out.println("############################");
            /* 자식 카테고리 초기화 */
            Category childCategory = new Category(request.getName(),parentCategory.getDepth()+1L);
            childCategory.addParent(parentCategory);
            categoryRepository.save(childCategory);
        }

    }

    /* 카테고리 조회 */
    @Transactional
    public List<CategoryResult> findByAll(){

        List<CategoryResult> results = categoryRepository.customFindAll().stream().map(CategoryResult::of).collect(Collectors.toList());
        return results;
    }

}
