package com.mzx.jpathymeleaf;

import com.mzx.jpathymeleaf.dao.BookDao;
import com.mzx.jpathymeleaf.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class JpathymeleafApplicationTests {

    @Autowired
    BookDao bookDao;

    @Test
    void contextLoads() {
        System.out.println(bookDao.findAll());
        System.out.println(bookDao.count());
        Optional<Book> book = bookDao.findById(1);
        System.out.println(book.get());
    }

}
