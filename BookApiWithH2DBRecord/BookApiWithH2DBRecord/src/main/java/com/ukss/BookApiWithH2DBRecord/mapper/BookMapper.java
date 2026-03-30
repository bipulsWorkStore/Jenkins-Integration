package com.ukss.BookApiWithH2DBRecord.mapper;

import com.ukss.BookApiWithH2DBRecord.dto.BookRequest;
import com.ukss.BookApiWithH2DBRecord.dto.BookResponse;
import com.ukss.BookApiWithH2DBRecord.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toGetBookEntity(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.name());
        book.setPrice(bookRequest.price());
        return book;
    }

    public BookResponse toGetBookResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getName(),
                book.getPrice()
        );
    }

    public void updateBook(BookRequest bRequest, Book book) {
        if (bRequest.name() != null) book.setName(bRequest.name());
        if (bRequest.price() != 0) book.setPrice(bRequest.price());
    }
}
