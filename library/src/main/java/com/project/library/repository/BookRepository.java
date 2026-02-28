package com.project.library.repository;

import com.project.library.model.Book;
import com.project.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface BookRepository extends JpaRepository<Book, Long>
{
    Optional<Book> findByISBN(String ISBN);
    List<Book> findByTitleContainingIgnoreCase (String title);
    List<Book> findByAuthorContainingIgnoreCase (String author);
    List<Book> findByGenre (Genre genre);
    List<Book> findByAvailableTrue();
}
