package com.library.controllers;

import com.library.dto.BookDTO;
import com.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookListController {

    private final BookService bookService;

    @GetMapping("/books")
    public String showBooksList(Model model) {
        List<BookDTO> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "test";
    }

    @GetMapping("image-book/{bookId}")
    public void getBookImage(@PathVariable final Long bookId, HttpServletResponse response) throws IOException {
        byte[] bookImage = bookService.getBookImage(bookId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.setHeader("Cache-Control", "max-age=2628000");
        try (OutputStream out = response.getOutputStream()) {
            out.write(bookImage);
        }
    }
}
