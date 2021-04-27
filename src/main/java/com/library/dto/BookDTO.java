package com.library.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {

    private Long bookId;
    private String title;
    private String description;
    private byte[] image;
    private AuthorDTO author;
    private List<CategoryDTO> categories;
}
