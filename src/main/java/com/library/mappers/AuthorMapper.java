package com.library.mappers;

import com.library.dto.AuthorDTO;
import com.library.entities.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);

    List<AuthorDTO> toListDTO(List<Author> authors);
}
