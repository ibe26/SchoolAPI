package com.school.service.interfaces;

import com.school.model.student.Student;
import com.school.dto.student.StudentDto;
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
    Long addCourse(Long studentId,Long courseId);
}
