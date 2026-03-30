package com.ukss.BookApiWithH2DBRecord.controller;

import com.ukss.BookApiWithH2DBRecord.dto.BookRequest;
import com.ukss.BookApiWithH2DBRecord.dto.BookResponse;
import com.ukss.BookApiWithH2DBRecord.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBookList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.addBook(bookRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookRequest bookRequest,
                                                   @PathVariable Long id) {
        return ResponseEntity.ok(bookService.updateBook(bookRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
