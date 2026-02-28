package com.project.library.service;

import com.project.library.model.Book;
import com.project.library.model.Genre;
import com.project.library.model.Issue;
import com.project.library.model.Member;
import com.project.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService
{
    private final BookRepository repository;

    BookService(BookRepository repository)
    {
        this.repository = repository;
    }

    public List<Book> fetchBooks()
    {
        return repository.findAll();
    }

    public List<Book> fetchAvailableBooks()
    {
        return repository.findByAvailableTrue();
    }

    public List<Book> fetchBooksByGenre(Genre genre)
    {
        return repository.findByGenre(genre);
    }

    public List<Book> searchBook (String query)
    {
        Optional<Book> bookByIsbn = repository.findByIsbn(query);

        if(bookByIsbn.isPresent())
        {
            return List.of(bookByIsbn.get());
        }

        List<Book> booksByTitle = repository.findByTitleContainingIgnoreCase(query);
        List<Book> booksByAuthor = repository.findByAuthorContainingIgnoreCase(query);
        Set<Book> result = new HashSet<>();     // Using set so no duplicates appear

        // addAll is used for adding elements present in a data structure
        result.addAll(booksByTitle);
        result.addAll(booksByAuthor);

        return new ArrayList<>(result);     // ArrayList is the implementation of the List Interface
    }

    public Optional<Book> searchBookById(long id)
    {
        return repository.findById(id);
    }

    public Optional<Book> switchAvailable(long id)
    {
        Optional<Book> result = repository.findById(id);

        if(result.isPresent())
        {
            Book b = result.get();
            b.setAvailable(!b.isAvailable());
            return Optional.of(repository.save(b));
        }

        return result;
    }

    public Optional<String> dropBook(long id)
    {
        Optional<Book> result = repository.findById(id);
        if(result.isPresent())
        {
            repository.delete(result.get());
            return Optional.of("Book Deleted Successfully! (ID: " + id + ")");
        }

        return Optional.empty();
    }

    public Book postBook(Book book)
    {
        return repository.save(book);
    }

    public List<Book> postBookList(List<Book> books)
    {
        return repository.saveAll(books);
    }

    public Optional<Book> updateBook(long id, Book updatedBook) // ID for searching the book in db, Book for the updated attributes
    {
        Optional<Book> result = repository.findById(id);

        if(result.isEmpty())
        {
            return result;
        }

        Book existingBook = result.get();

        if(updatedBook.getTitle() != null)
            existingBook.setTitle(updatedBook.getTitle());

        if(updatedBook.getAuthor() != null)
            existingBook.setAuthor(updatedBook.getAuthor());

        if(updatedBook.getGenre() != null)
            existingBook.setGenre(updatedBook.getGenre());

        if(updatedBook.getIsbn() != null)
            existingBook.setIsbn(updatedBook.getIsbn());

        if(updatedBook.getCopies() != -1)
            existingBook.setCopies(updatedBook.getCopies());

        return Optional.of(repository.save(existingBook));
    }

}
