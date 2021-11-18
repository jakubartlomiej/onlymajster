package com.jakubartlomiej.onlymajster.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jakubartlomiej.onlymajster.category.model.Category;
import com.jakubartlomiej.onlymajster.category.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.awt.print.Pageable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WithMockUser
@SpringBootTest
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnAllCategories() throws Exception {
        //given
        Gson gson = new Gson();
        String url = "/api/v1/category";
        Category category1 = new Category();
        category1.setName("IT");
        category1.setDescription("IT category");
        Category category2 = new Category();
        category2.setName("Building");
        category2.setDescription("Building category");
        Category category3 = new Category();
        category3.setName("Car");
        category3.setDescription("Car category");
        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
        //when
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        //then
        JsonObject jsonObject = gson.fromJson(mvcResult.getResponse().getContentAsString(), JsonObject.class);
        List<Category> list = Arrays.asList(objectMapper.readValue(jsonObject.getAsJsonArray("content").toString(), Category[].class));
        assertEquals(list.size(), 3);
    }

    @Test
    void shouldGetSingleCategory() throws Exception {
        //given
        String url = "/api/v1/category";
        Category insertCategory = new Category();
        insertCategory.setName("IT");
        insertCategory.setDescription("IT category");
        categoryService.save(insertCategory);
        //when
        MvcResult mvcResult = mockMvc.perform(get(url + "/" + insertCategory.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //then
        Category readCategory = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals(readCategory.getName(), "IT");
    }
}