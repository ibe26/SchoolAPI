package com.School.Service.Interface;

import com.School.Model.Student.Student;
import com.School.Model.Student.StudentDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface StudentService {
    Collection<Student> getStudents();
    Student addStudent(StudentDto studentDto);
    Long deleteStudent(Long id);
    Student updateStudent(StudentDto studentDto,Long id);
    Student  addCourse(Long studentId,Long courseId);
}
