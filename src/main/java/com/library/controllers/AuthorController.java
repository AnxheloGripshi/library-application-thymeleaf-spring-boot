package com.library.controllers;

import com.library.dto.AuthorDTO;
import com.library.dto.BookDTO;
import com.library.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAllAuthor() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping("/create-author")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody final AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.createAuthor(authorDTO));
    }

    @PutMapping("/update-author")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody final AuthorDTO authorDTO) {
        return ResponseEntity.ok(this.authorService.updateAuthor(authorDTO));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<AuthorDTO> findAuthor(@PathVariable final Long authorId) {
        return ResponseEntity.ok(this.authorService.findById(authorId));
    }

    @DeleteMapping("/delete-author/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") final Long authorId) {
        this.authorService.deleteAuthor(authorId);
        return ResponseEntity.ok().build();
    }


}
