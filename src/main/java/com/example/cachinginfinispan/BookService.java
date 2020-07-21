package com.example.cachinginfinispan;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class BookService {
    public Book byId(Integer id) {
        return new Book(UUID.randomUUID(), Stream.of("NoName", "Game of throne", "Vikings").findFirst().get());
    }
}
