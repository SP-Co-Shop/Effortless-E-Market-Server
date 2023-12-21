package com.example.coshop.repository.category;

import com.example.coshop.Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {


}
