package vn.edu.hcmuaf.fit.webbansach.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.webbansach.model.dto.BookDto;
import vn.edu.hcmuaf.fit.webbansach.model.entity.Books;
import vn.edu.hcmuaf.fit.webbansach.service.BookService;

import java.util.Map;

// BookController.java
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody @Valid BookDto dto) {
        Books saved = bookService.createBook(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("id", saved.getId()));
    }
}

