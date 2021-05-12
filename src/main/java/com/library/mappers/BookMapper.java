package com.library.mappers;

import com.library.dto.BookDTO;
import com.library.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, AuthorMapper.class})
public interface BookMapper {

    @Mapping(source = "author.authorId",target = "author.id")
    Book toEntity(BookDTO bookDTO);

    @Mapping(source = "author.id",target = "author.authorId")
    BookDTO toDTO(Book book);

    List<BookDTO> toListDTO(List<Book> books);

    BookDTO update(BookDTO sourceBook, @MappingTarget BookDTO targetBook);

}
