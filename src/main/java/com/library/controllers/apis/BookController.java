package com.library.controllers.apis;

import com.library.dto.BookDTO;
import com.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDTO> findBook(@PathVariable final Long bookId) {
        return ResponseEntity.ok(this.bookService.findById(bookId));
    }

    @PostMapping(value = "/create-book")
    public ResponseEntity<BookDTO> createBook(@RequestBody final BookDTO bookDTO) {
        return ResponseEntity.ok(this.bookService.createBook(bookDTO));
    }

    @PutMapping("/update-book")
    public ResponseEntity<BookDTO> updateBook(@RequestBody final BookDTO bookDTO) {
        return ResponseEntity.ok(this.bookService.updateBook(bookDTO));
    }

    @DeleteMapping("/delete-book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") final Long bookId) {
        this.bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload-image/{bookId}")
    public ResponseEntity<Void> uploadImage(@PathVariable("bookId") final Long bookId, @RequestParam("image") final MultipartFile image){
        this.bookService.uploadBookImage(bookId,image);
        return ResponseEntity.ok().build();
    }

}
