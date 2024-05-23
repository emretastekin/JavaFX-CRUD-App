package com.cruddapp.crudapp;

public class Student {
    private int id;
    private String bookName;
    private  String author;
    private String pagenumber;
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(String  pagenumber) {
        this.pagenumber = pagenumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
