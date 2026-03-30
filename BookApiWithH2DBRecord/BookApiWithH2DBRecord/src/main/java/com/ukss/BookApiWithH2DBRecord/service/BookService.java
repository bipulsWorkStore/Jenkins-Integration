package com.ukss.BookApiWithH2DBRecord.service;

import com.ukss.BookApiWithH2DBRecord.dto.BookRequest;
import com.ukss.BookApiWithH2DBRecord.dto.BookResponse;
import com.ukss.BookApiWithH2DBRecord.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBookList();
    BookResponse getBook(Long id);
    BookResponse addBook(BookRequest book);
    BookResponse updateBook(BookRequest book, Long id);
    void deleteBook(Long id);
}
