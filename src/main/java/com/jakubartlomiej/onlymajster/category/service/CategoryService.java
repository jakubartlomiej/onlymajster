package com.jakubartlomiej.onlymajster.category.service;

import com.jakubartlomiej.onlymajster.category.exception.CategoryNotFoundException;
import com.jakubartlomiej.onlymajster.category.model.Category;
import com.jakubartlomiej.onlymajster.category.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Iterable<Category> findAll(String pageNumber, String pageSize) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        return categoryRepository.findAll(pageable);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category newCategory, Long id) {
        Category category = findById(id);
        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());
        return categoryRepository.save(category);
    }

    public boolean checkIfCategoryExist(String categoryName) {
        return categoryRepository.findByName(categoryName).isPresent();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}