package com.library.services.impl;

import com.library.dto.AuthorDTO;
import com.library.entities.Author;
import com.library.mappers.AuthorMapper;
import com.library.repositories.AuthorRepository;
import com.library.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = this.authorMapper.toEntity(authorDTO);
        Author createdAuthor = this.authorRepository.save(author);
        return this.authorMapper.toDTO(createdAuthor);

    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        return this.authorMapper.toListDTO(authors);
    }
}
