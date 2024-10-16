//This class acts as the API layer, that have all the REST APIs that the Client side can make use of.
package com.myappliction.springboot_application.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //API to get all the Students in Students table along with its details
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    //API to add a new Student into the table
    @PostMapping
    public void registerNewStudent(@RequestBody Student newStudent){
        studentService.addNewStudent(newStudent);
    }

    //API to delete a Student from the table
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    //API to update name and email of a Student from the table
    @PutMapping(path = "{studentId}")
    public void updateStudentDetails(@PathVariable("studentId") Long studentId,
                                     @RequestBody(required = false) String newName,
                                     @RequestBody(required = false) String newEmail){
        studentService.updateStudentDetails(studentId, newName, newEmail);
    }
}
