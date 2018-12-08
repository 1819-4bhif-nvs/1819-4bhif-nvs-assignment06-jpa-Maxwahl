package at.htl.Library.model;

import javax.persistence.Entity;

@Entity
public class Book extends Item{
    String author;
    int pages;

    //region constructors
    public Book(String name, double price,String genre, String author, int pages) {
        super(name,genre ,price);
        this.author = author;
        this.pages = pages;
    }

    public Book() {
    }
    //endregion

    //region getter and setter
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
    //endregion
}
