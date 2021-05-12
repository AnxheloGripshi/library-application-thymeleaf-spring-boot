package com.library.services.impl;

import com.library.dto.AuthorDTO;
import com.library.entities.Author;
import com.library.errors.BadRequestException;
import com.library.errors.ErrorMessage;
import com.library.mappers.AuthorMapper;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;
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

    private final BookRepository bookRepository;

    private final AuthorMapper authorMapper;

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = this.authorMapper.toEntity(authorDTO);
        Author createdAuthor = this.authorRepository.save(author);
        return this.authorMapper.toDTO(createdAuthor);

    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) {
        AuthorDTO existingAuthorDTO = findById(authorDTO.getAuthorId());
        AuthorDTO updatedAuthorDTO = this.authorMapper.update(authorDTO, existingAuthorDTO);
        Author updatedAuthor = this.authorRepository.save(this.authorMapper.toEntity(updatedAuthorDTO));
        return this.authorMapper.toDTO(updatedAuthor);
    }

    @Override
    public AuthorDTO findById(Long authorId) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new BadRequestException(ErrorMessage.BOOK_NOT_FOUND));
        return this.authorMapper.toDTO(author);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        return this.authorMapper.toListDTO(authors);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        this.bookRepository.findByAuthorId(authorId).forEach(book -> {
            book.setAuthor(null);
            this.bookRepository.save(book);
        });
        AuthorDTO existingAuthor = findById(authorId);
        this.authorRepository.delete(this.authorMapper.toEntity(existingAuthor));
    }
}
