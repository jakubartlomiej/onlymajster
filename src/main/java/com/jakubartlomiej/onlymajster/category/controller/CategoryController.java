package com.jakubartlomiej.onlymajster.category.controller;

import com.jakubartlomiej.onlymajster.category.exception.CategoryAlreadyExist;
import com.jakubartlomiej.onlymajster.category.model.Category;
import com.jakubartlomiej.onlymajster.category.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Iterable<Category>> findAll(@RequestParam(defaultValue = "0", name = "page") String pageNumber,
                                                      @RequestParam(defaultValue = "10", name = "size") String pageSize) {
        return ResponseEntity.ok(categoryService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category) {
        if (categoryService.checkIfCategoryExist(category.getName())) {
            return ResponseEntity.badRequest().body(new CategoryAlreadyExist(category.getName()).getMessage());
        } else {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/category").toUriString());
            return ResponseEntity.created(uri).body(categoryService.save(category));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/category/" + id).toUriString());
        return ResponseEntity.created(uri).body(categoryService.update(category, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
        if (categoryService.findById(id) != null) {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}