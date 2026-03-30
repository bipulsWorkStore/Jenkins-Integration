package com.ukss.BookApiWithH2DBRecord.repository;

import com.ukss.BookApiWithH2DBRecord.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
