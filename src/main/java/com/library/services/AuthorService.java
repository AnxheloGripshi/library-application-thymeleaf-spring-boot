package com.library.services;

import com.library.dto.AuthorDTO;
import com.library.entities.Author;

import java.util.List;

public interface AuthorService {

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAllAuthors();
}
