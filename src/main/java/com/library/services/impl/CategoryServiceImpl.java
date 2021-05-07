package com.library.services.impl;

import com.library.dto.BookDTO;
import com.library.dto.CategoryDTO;
import com.library.entities.Book;
import com.library.entities.Category;
import com.library.errors.BadRequestException;
import com.library.errors.ErrorMessage;
import com.library.mappers.CategoryMapper;
import com.library.repositories.CategoryRepository;
import com.library.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category createdCategory = this.categoryRepository.save(category);
        return this.categoryMapper.toDTO(createdCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return this.categoryMapper.toListDTO(categories);
    }

    @Override
    public CategoryDTO findById(Long bookId) {
        Category category = this.categoryRepository.findById(bookId).orElseThrow(() -> new BadRequestException(ErrorMessage.BOOK_NOT_FOUND));
        return this.categoryMapper.toDTO(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryDTO existingBook = findById(categoryId);
        this.categoryRepository.delete(this.categoryMapper.toEntity(existingBook));
    }

}
