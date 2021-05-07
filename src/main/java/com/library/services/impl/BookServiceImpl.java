package com.library.services.impl;

import com.library.dto.BookDTO;
import com.library.entities.Book;
import com.library.errors.ErrorMessage;
import com.library.errors.BadRequestException;
import com.library.mappers.BookMapper;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;
import com.library.repositories.CategoryRepository;
import com.library.services.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;


    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        this.authorRepository.findById(bookDTO.getAuthor().getAuthorId()).orElseThrow(() -> {
            log.error(ErrorMessage.AUTHOR_NOT_FOUND);
            return new BadRequestException(ErrorMessage.AUTHOR_NOT_FOUND);
        });
        bookDTO.getCategories().forEach((categoryDTO -> {
            this.categoryRepository.findById(categoryDTO.getCategoryId()).orElseThrow(() -> {
                log.error(ErrorMessage.BOOK_CATEGORY_NOT_FOUND);
                return new BadRequestException(ErrorMessage.BOOK_CATEGORY_NOT_FOUND);
            });
        }));

        Book book = this.bookMapper.toEntity(bookDTO);
        Book createdBook = this.bookRepository.save(book);
        return this.bookMapper.toDTO(createdBook);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        BookDTO existingBookDTO = findById(bookDTO.getBookId());
        BookDTO updatedBookDTO = this.bookMapper.update(bookDTO, existingBookDTO);
        Book updatedBook = this.bookRepository.save(this.bookMapper.toEntity(updatedBookDTO));
        return this.bookMapper.toDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        BookDTO existingBook = findById(bookId);
        this.bookRepository.delete(this.bookMapper.toEntity(existingBook));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return this.bookMapper.toListDTO(books);
    }

    @Override
    public BookDTO findById(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BadRequestException(ErrorMessage.BOOK_NOT_FOUND));
        return this.bookMapper.toDTO(book);
    }

    @Override
    public void uploadBookImage(Long bookId, MultipartFile image) {
        BookDTO bookDTO = findById(bookId);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            IOUtils.copy(image.getInputStream(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Book book = this.bookMapper.toEntity(bookDTO);
        book.setImage(outputStream.toByteArray());
        this.bookRepository.save(book);
    }

    @Override
    public byte[] getBookImage(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BadRequestException(ErrorMessage.BOOK_NOT_FOUND));
        byte[] bookImage = book.getImage();
        return bookImage;
    }


}
