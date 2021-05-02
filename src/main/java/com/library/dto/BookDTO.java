package com.library.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BookDTO {

    private Long bookId;
    private String title;
    private String description;
    private MultipartFile bookImage;
    private AuthorDTO author;
    private List<CategoryDTO> categories;
}
