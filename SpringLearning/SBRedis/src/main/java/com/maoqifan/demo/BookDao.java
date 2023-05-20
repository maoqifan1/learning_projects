package com.maoqifan.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookDao extends MongoRepository<Book,Integer> {

    List<Book> findByAuthorContains(String author);

    Book findBybookNameEquals(String name);

}
