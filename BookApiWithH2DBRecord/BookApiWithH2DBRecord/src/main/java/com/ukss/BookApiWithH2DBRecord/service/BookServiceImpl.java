package com.ukss.BookApiWithH2DBRecord.service;

import com.ukss.BookApiWithH2DBRecord.dto.BookRequest;
import com.ukss.BookApiWithH2DBRecord.dto.BookResponse;
import com.ukss.BookApiWithH2DBRecord.entity.Book;
import com.ukss.BookApiWithH2DBRecord.mapper.BookMapper;
import com.ukss.BookApiWithH2DBRecord.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookResponse> getAllBookList() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toGetBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getBook(Long id) {
        return bookMapper.toGetBookResponse(bookRepository.findById(id).orElse(null));
    }

    @Override
    public BookResponse addBook(BookRequest book) {
        return bookMapper.toGetBookResponse(bookRepository.save(bookMapper.toGetBookEntity(book)));
    }

    @Override
    public BookResponse updateBook(BookRequest bRequest, Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        bookMapper.updateBook(bRequest, book);
        return bookMapper.toGetBookResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
