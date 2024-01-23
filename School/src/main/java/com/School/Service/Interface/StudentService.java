package com.School.Service.Interface;

import com.School.Model.Student.Student;
import com.School.Model.Student.StudentDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface StudentService {
    Collection<Student> getStudents();
    Optional<Student> getStudentById(Long id);
    Optional<Student> addStudent(StudentDto studentDto);
    Long deleteStudent(Long id);
    Optional<Student> updateStudent(StudentDto studentDto,Long id);
    Optional<Student>  addCourse(Long studentId,Long courseId);
}
