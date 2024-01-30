package com.school.controller;

import com.school.controller.RequestMappingHelper.Routes;
import com.school.dto.student.SaveCourseDto;
import com.school.model.student.Student;
import com.school.dto.student.StudentDto;
import com.school.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(cacheNames = "student")
    @GetMapping(Routes.getAll)
    public ResponseEntity<Collection<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
    @Cacheable(cacheNames = "student",key = "#id")
    @GetMapping(Routes.get)
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        final Optional<Student> studentOptional=studentService.getStudentById(id);
        return studentOptional.isPresent() ? new ResponseEntity<>(studentOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CacheEvict(cacheNames = "student",allEntries = true)
    @PostMapping(Routes.save)
    public ResponseEntity<?> saveStudent(@RequestBody() StudentDto studentDto){
        Optional<Student> studentOptional=studentService.addStudent(studentDto);
        return studentOptional.isPresent() ? new ResponseEntity<>(studentOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @CacheEvict(cacheNames = "student",key = "#id")
    @DeleteMapping(Routes.delete)
    public ResponseEntity<?> deleteStudent(@PathVariable("id")Long id){
        return  studentService.deleteStudent(id)!=null ? new ResponseEntity<>(id,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @CachePut(cacheNames = "student",key = "#id")
    @PutMapping(Routes.update)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto){
        Optional<Student> studentOptional=studentService.updateStudent(studentDto,id);
        return studentOptional.isPresent() ? new ResponseEntity<>(studentOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CacheEvict(cacheNames = "student",allEntries = true)
    @PostMapping("/saveCourse/{id}")
    public ResponseEntity<?> saveCourse(@PathVariable("id") Long studentId, @RequestBody SaveCourseDto saveCourseDto){

        return studentService.addCourse(studentId,saveCourseDto.courseId())!=null ? new ResponseEntity<>(saveCourseDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}