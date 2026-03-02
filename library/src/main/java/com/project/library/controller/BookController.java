package com.project.library.controller;

import com.project.library.model.Book;
import com.project.library.model.Genre;
import com.project.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")

public class BookController
{
    private final BookService service;
    BookController(BookService service) {this.service = service;};

    @GetMapping
    public List<Book> getAllBooks()
    {
        return service.fetchBooks();
    }

    @GetMapping("/search")
    public List<Book> findBooks(@RequestParam String query)
    {
        return service.searchBook(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id)
    {
        Optional<Book> result = service.searchBookById(id);

        if(result.isPresent())
        {
            Book b = result.get();
            return ResponseEntity.ok(b);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks()
    {
        return service.fetchAvailableBooks();
    }

    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable Genre genre)
    {
        return service.fetchBooksByGenre(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updatedBook)
    {
        Optional<Book> result = service.updateBook(id, updatedBook);

        if(result.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result.get());
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Book> toggleAvailable(@PathVariable long id)
    {
        Optional<Book> result = service.switchAvailable(id);
        if(result.isPresent())
        {
            Book updatedBook = result.get();
            return ResponseEntity.ok(updatedBook);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id)
    {
        Optional<String> message = service.dropBook(id);
        if(message.isPresent())
        {
            return ResponseEntity.ok(message.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Book addBook(@RequestBody Book b)
    {
        return service.postBook(b);
    }

    @PostMapping("/add")
    public List<Book> addBookList(@RequestBody List<Book> books)
    {
        return service.postBookList(books);
    }
}
