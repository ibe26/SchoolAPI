package com.school.service.implementation;

import com.school.model.course.Course;
import com.school.model.student.Student;
import com.school.dto.student.StudentDto;
import com.school.repository.course.CourseRepository;
import com.school.repository.student.StudentRepository;
import com.school.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Collection<Student> getStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Optional<Student> getStudentById(Long id){
        return studentRepository.existsById(id) ? studentRepository.findById(id) : Optional.empty();
    }

    @Override
    public Optional<Student> addStudent(StudentDto studentDto) {
        if(studentDto!=null){
            Student student=new Student();
            student.setFirstName(studentDto.firstName());
            student.setLastName(studentDto.lastName());
           return Optional.of(studentRepository.save(student));
        }
        return Optional.empty();
    }

    @Override
    public Long deleteStudent(Long id) {
        if(id>0 && studentRepository.existsById(id))
        {
            studentRepository.deleteById(id);
            return id;
        }
        return null;
    }

    @Override
    public Optional<Student> updateStudent(StudentDto studentDto, Long id) {
        if(id>0 && studentRepository.findById(id).isPresent()){
            Student student=studentRepository.findById(id).get();
            student.setId(id);
            student.setFirstName(studentDto.firstName());
            student.setLastName(studentDto.lastName());
            studentRepository.save(student);
            return Optional.of(student);
        }
        return Optional.empty();
    }

    @Override
    public Long addCourse(Long studentId, Long courseId) {
        if(courseId>0 && courseRepository.findById(courseId).isPresent() && studentRepository.findById(studentId).isPresent())
        {
            Course course=courseRepository.findById(courseId).get();
            Student student=studentRepository.findById(studentId).get();
            if(!student.getCourses().contains(course))
            {
                student.addCourse(course);
                studentRepository.save(student);
                return courseId;
            }

        }
        return null;
    }
}
