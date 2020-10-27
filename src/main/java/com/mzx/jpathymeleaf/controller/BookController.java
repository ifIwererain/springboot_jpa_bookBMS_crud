package com.mzx.jpathymeleaf.controller;

import com.mzx.jpathymeleaf.dao.BookDao;
import com.mzx.jpathymeleaf.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    BookDao bookDao;

    // 首页控制器
    @RequestMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("booklist",bookDao.findAll());
        return "index";
    }

    // 删除书籍控制器
    @RequestMapping("/delbook/{id}")
    public String delbook(@PathVariable("id")Integer id,Model model){
        bookDao.deleteById(id);
        model.addAttribute("booklist",bookDao.findAll());
        return "redirect:/index";
    }


    // 添加书籍控制器
    @RequestMapping("/add")
    public String addbook(Model model){

        // 查询数据库书籍数量作为ID值
        int count = (int) bookDao.count();
        // 创建一个空书籍
        Book book = new Book(count+1,"", (float) 0,"");
        model.addAttribute("book",book);
        model.addAttribute("gourl","addMes");
        return "addBook";
    }


    // 修改书籍控制器
    @RequestMapping("/updbook/{id}")
    public String updbook(@PathVariable("id")Integer id,Model model){
        Optional<Book> book = bookDao.findById(id);
        model.addAttribute("book",book.get());
        model.addAttribute("gourl","updateMes");
        return "addBook";
    }

    // 添加书籍接口
    @RequestMapping("/addMes")
    public String addMes(Book book){
        // 查询数据库书籍数量作为ID值
        int count = (int) bookDao.count();
        book.setId(count+1);
        bookDao.save(book);
        return "redirect:/index";
    }

    // 修改书籍接口
    @RequestMapping("/updateMes")
    public String updateMes(Book book){
        bookDao.saveAndFlush(book);
        return "redirect:/index";
    }
}
