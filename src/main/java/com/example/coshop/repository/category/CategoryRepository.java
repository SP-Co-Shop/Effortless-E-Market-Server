package com.example.coshop.repository.category;

import com.example.coshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>, CategoryRepositoryCustom {


}
