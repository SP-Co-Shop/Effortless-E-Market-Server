package com.example.coshop.test.category;

import com.example.coshop.dto.category.CategoryRequest;
import com.example.coshop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class CategoryTest {

    private final CategoryService categoryService;

    @Autowired
    public CategoryTest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /* 카테고리 조회 */

    @Test
    public void findByAll(){
        createCategory();
        System.out.println(categoryService.findByAll());
    }

    @Test
    @Rollback(value = false)
    /* 카테고리 생성 */
    public void createCategory(){

        CategoryRequest request = new CategoryRequest("도서", null);
        categoryService.createCategory(request);

        CategoryRequest request2 = new CategoryRequest("전공 서적", 1L);
        categoryService.createCategory(request2);






    }
}
