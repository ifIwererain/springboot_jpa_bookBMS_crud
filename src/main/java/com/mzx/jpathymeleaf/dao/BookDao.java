package com.mzx.jpathymeleaf.dao;

import com.mzx.jpathymeleaf.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}
