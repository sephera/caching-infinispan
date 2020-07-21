package com.example.cachinginfinispan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Cacheable(cacheNames = "books", key = "#id")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookName(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.byId(id));
    }
}
