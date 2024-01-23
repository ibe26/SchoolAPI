package com.School.Controller;

import com.School.Controller.RequestMappingHelper.Routes;
import com.School.Model.Student.Student;
import com.School.Model.Student.StudentDto;
import com.School.Service.Interface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping(Routes.getAll)
    public ResponseEntity<Collection<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
    @GetMapping(Routes.get)
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        final Optional<Student> studentOptional=studentService.getStudentById(id);
        return studentOptional.isPresent() ? new ResponseEntity<>(studentOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(Routes.save)
    public ResponseEntity<?> saveStudent(@RequestBody() StudentDto studentDto){
        Optional<Student> studentOptional=studentService.addStudent(studentDto);
        return studentOptional.isPresent() ? new ResponseEntity<>(studentOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(Routes.delete)
    public ResponseEntity<?> deleteStudent(@PathVariable("id")Long id){
        return  studentService.deleteStudent(id)!=null ? new ResponseEntity<>(id,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping(Routes.update)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto){
        Optional<Student> studentOptional=studentService.updateStudent(studentDto,id);
        return studentOptional.isPresent() ? new ResponseEntity<>(studentOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/saveCourse/{id}")
    public ResponseEntity<?> saveCourse(@PathVariable("id") Long studentId, @RequestBody Long courseId){
        Optional<Student> studentOptional=studentService.addCourse(studentId,courseId);
        return studentOptional.isPresent() ? new ResponseEntity<>(studentOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}