package com.yuilns.pojo;

public class Book {
    private Integer Book_id;
    private String bookname;
    private String writer;
    private String publisher;
    private Integer quantity;
    public Book() {}

    public Integer getBook_id() {
        return Book_id;
    }

    public Book(String name, String writer, String publisher) {
        this.bookname = name;
        this.writer = writer;
        this.publisher = publisher;
        this.quantity = 20; // 默认库存
    }

    public void setBook_id(Integer book_id) {
        Book_id = book_id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        System.out.println("MyBatis正在设置quantity: " + quantity);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Book_id=" + Book_id +
                ", bookname='" + bookname + '\'' +
                ", writer='" + writer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
