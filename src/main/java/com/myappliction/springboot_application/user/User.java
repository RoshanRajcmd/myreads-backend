package com.myappliction.springboot_application.user;
import com.myappliction.springboot_application.book.Book;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;
    private String name;
    private LocalDate dob;
    //Transient is to tell the Hibernate API that this column will be handled in the runtime and does not need to be
    //stored in the DB. If you don't use this annotation the age will get calculated and shown in the web but that null
    // value will be show in DB, if this annotation used it will not display this age column in DB.
    @Transient
    private Integer age;
    private String email;
    private String password;
    @OneToMany
    private Set<User> friends = new HashSet<User>();
    //Cascade All to initialize the child object in the DB while initializing Part object and its columns
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> booksList = new HashSet<Book>();

    public User() {
    }

    public User(Long userId, String name, LocalDate dob, String email, String password,
                Set<Book> booksList, Set<User> friends) {
        this.userId = userId;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.booksList = booksList;
        this.friends = friends;
    }

    public User(String name, LocalDate dob, String email, String password,
                Set<Book> booksList, Set<User> friends){
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.booksList = booksList;
        this.friends = friends;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(Set<Book> booksList) {
        this.booksList = booksList;
    }
}
