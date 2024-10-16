package com.myappliction.springboot_application.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//The Student and Long (ID) passed into the JpaRepository making Jpa provide function to CURD operations with the table
//(repository) in this case the Student is the table and ID is its primary key
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //The below declaration is a JPQL Query when ?1 will have the value of email we send in to this method. This query
    //will query out all the Students with the given email
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
