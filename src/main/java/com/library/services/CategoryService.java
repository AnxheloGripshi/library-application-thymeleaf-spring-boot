package com.library.services;

import com.library.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();
}
