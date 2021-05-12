package com.library.mappers;

import com.library.dto.AuthorDTO;
import com.library.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "authorId", target = "id")
    Author toEntity(AuthorDTO authorDTO);

    @Mapping(source = "id", target = "authorId")
    AuthorDTO toDTO(Author author);

    AuthorDTO update(AuthorDTO sourceAuthor, @MappingTarget AuthorDTO targetAuthor);

    List<AuthorDTO> toListDTO(List<Author> authors);
}
