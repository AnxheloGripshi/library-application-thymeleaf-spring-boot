package com.library.services;

import com.library.dto.AuthorDTO;
import com.library.entities.Author;

import java.util.List;

public interface AuthorService {

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    AuthorDTO updateAuthor(AuthorDTO authorDTO);

    AuthorDTO findById(Long authorId);

    void deleteAuthor(Long bookId);

    List<AuthorDTO> getAllAuthors();
}
