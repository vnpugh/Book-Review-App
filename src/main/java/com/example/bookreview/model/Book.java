package com.example.bookreview.model;

import jdk.internal.icu.text.UnicodeSet;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String genre;
    @Column
    private Integer yearPublished;
    @Column
    private String isbn;
    @Column
    private double rating;


    /**
     *one-to-many relationship between the book class and the review class.
     *one book can have many reviews associated with it -> book entity will have a list of reviews.
     *mappedBy = "book": an inverse relationship -> the name of the book attribute maps back to the reviewList.
     *orphanRemoval = true: delete a review from the database if it is removed from a book's reviewList.
     *@LazyCollection(LazyCollectionOption.FALSE): load the review list when the book is retrieved from the database.
     */
    @OneToMany(mappedBy = "book", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviewList;

    /**
     *many-to-many relationship between the book class and the bestseller class.
     *many books can be on the bestsellers list.
     *relationship is mapped by the books field in the bestseller class.
     */
    @ManyToMany(mappedBy = "books")
    private List<BestSeller> bestsellers;



    public Book(Long id, String title, String author, String genre,
                Integer yearPublished, String isbn, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        this.rating = rating;
    }
    public Book() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public void addReview(Review review) {this.reviewList.add(review);}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", yearPublished=" + yearPublished +
                ", isbn='" + isbn + '\'' +
                ", rating=" + rating +
                ", reviewList=" + reviewList +
                ", bestsellers=" + bestsellers +
                '}';
    }


}
