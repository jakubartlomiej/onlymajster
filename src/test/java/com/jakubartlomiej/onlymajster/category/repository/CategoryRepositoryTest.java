package com.jakubartlomiej.onlymajster.category.repository;

import com.jakubartlomiej.onlymajster.category.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void shouldReturnTrueIfCategoryExist() {
        //given
        Category category = new Category();
        category.setName("Budowlana");
        category.setDescription("Opis dla kategori budowlanej");
        categoryRepository.save(category);
        //when
        boolean expected = categoryRepository.findByName(category.getName()).isPresent();
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void shouldReturnFalseIfCategoryNotExist() {
        //given
        String categoryName = "TESTOWA";
        //when
        boolean expected = categoryRepository.findByName(categoryName).isPresent();
        //then
        assertThat(expected).isFalse();
    }
}