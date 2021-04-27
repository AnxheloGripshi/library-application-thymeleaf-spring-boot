package com.library.controllers;

import com.library.dto.BookDTO;
import com.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

    @PostMapping("/create-book")
    public ResponseEntity<BookDTO> createBook(@RequestBody final BookDTO bookDTO) {
        return ResponseEntity.ok(this.bookService.createBook(bookDTO));
    }

    @PostMapping("/update-book/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("bookId") final Long bookId, @RequestBody final BookDTO bookDTO) {
        return ResponseEntity.ok(this.bookService.updateBook(bookId, bookDTO));
    }

    @DeleteMapping("/delete-book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") final Long bookId) {
        this.bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

}
