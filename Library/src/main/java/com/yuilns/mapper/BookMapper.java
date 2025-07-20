package com.yuilns.mapper;

import com.yuilns.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    List<Book> selectAll();
    Book selectById();
    int countByName(@Param("bookname") String bookname);
    int addBook(Book book);
}
