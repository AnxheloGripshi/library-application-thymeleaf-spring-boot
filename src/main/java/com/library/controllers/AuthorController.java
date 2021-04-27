package com.library.controllers;

import com.library.dto.AuthorDTO;
import com.library.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.createAuthor(authorDTO));
    }


}
