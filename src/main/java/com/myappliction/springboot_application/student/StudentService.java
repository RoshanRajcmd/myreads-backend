//This class is the server logic layer in that does validation/business logic in between the API(Controller) and DB service(Repository) layer
package com.myappliction.springboot_application.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student newStudent){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(newStudent.getEmail());
        //if there is any value in the result of JPQL Query then it means the email is already been registered by someone
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email is Taken");
        }
        studentRepository.save(newStudent);
    }

    public void deleteStudent(Long studentId){
        if(!studentRepository.existsById(studentId)){
            throw new IllegalStateException("No Student found by the given ID");
        }
        else{
            studentRepository.deleteById(studentId);
        }
    }

    public void updateStudentDetails(Long studentId, String newName, String newEmail){
        Student studentById = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("No Student found by the given ID"));
        if(newName != null &&
                !newName.isEmpty() &&
                !Objects.equals(studentById.getName(), newName)) {
            studentById.setName(newName);
        }
        if(newEmail != null &&
                !newEmail.isEmpty() &&
                !Objects.equals(studentById.getEmail(), newEmail)) {
            studentById.setEmail(newEmail);
        }
    }
}
