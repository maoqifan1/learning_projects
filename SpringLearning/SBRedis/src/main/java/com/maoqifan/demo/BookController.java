package com.maoqifan.demo;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    BookDao bookDao;



    @GetMapping("/test2")
    public void test2(){
        List<Book> bookList = new ArrayList<>();
        Book b1 = new Book();
        b1.setId(1);
        b1.setBookName("朝花夕拾");
        b1.setAuthor("鲁迅");

        Book b2 = new Book();
        b2.setId(2);
        b2.setAuthor("鲁迅");
        b2.setBookName("呐喊");

        bookList.add(b1);
        bookList.add(b2);

        bookDao.insert(bookList);

        List<Book> bookList1 = bookDao.findByAuthorContains("鲁迅");
        System.out.println(bookList1);
        Book book = bookDao.findBybookNameEquals("朝花夕拾");
        System.out.println(book.getBookName());
    }

    @GetMapping("/test1")
    public void test1(){
        ValueOperations<String,String> ops1 = stringRedisTemplate.opsForValue();

        ops1.set("bookName","局外人");
        String name = ops1.get("bookName");
        System.out.println(name);
    }
}
