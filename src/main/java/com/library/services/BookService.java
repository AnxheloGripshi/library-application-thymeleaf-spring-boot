package com.library.services;

import com.library.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBook(Long bookId);

    List<BookDTO> getAllBooks();

    BookDTO findById(Long bookId);

    byte[] getBookImage(Long bookId);

}
