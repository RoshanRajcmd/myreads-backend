package com.myappliction.springboot_application.user;
import com.myappliction.springboot_application.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private LocalDate dob;
    //Transient is to tell the Hibernate API that this column will be handled in the runtime and does not need to be
    //stored in the DB. If you don't use this annotation the age will get calculated and shown in the web but that null
    // value will be show in DB, if this annotation used it will not display this age column in DB.
    @Transient
    private Integer age;
    @Column(unique = true)
    private String email;
    private String password;
    private Set<Long> friendsIds;
    //Cascade All to initialize the child object in the DB while initializing Part object and its columns
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> booksList = new HashSet<Book>();


    public User(String name, LocalDate dob, String email, String password,
                Set<Book> booksList, Set<Long> friendsIds){
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.booksList = booksList;
        this.friendsIds = friendsIds;
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

    public Set<Long> getFriendsIds() {
        return friendsIds;
    }

    public void setFriendsIds(Set<Long> friendsIds) {
        this.friendsIds = friendsIds;
    }

    public Set<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(Set<Book> booksList) {
        this.booksList = booksList;
    }
}
