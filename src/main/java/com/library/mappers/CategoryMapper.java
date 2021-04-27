package com.library.mappers;

import com.library.dto.CategoryDTO;
import com.library.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);

    List<CategoryDTO> toListDTO(List<Category> categories);


}
