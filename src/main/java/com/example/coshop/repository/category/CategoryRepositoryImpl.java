package com.example.coshop.repository.category;

import com.example.coshop.entity.Category;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Category> customFindAll() {
        return em.createQuery("select c from Category c where c.parent is Null ",Category.class).getResultList();
    }
}
