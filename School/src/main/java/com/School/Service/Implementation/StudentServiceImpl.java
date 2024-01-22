package com.School.Service.Implementation;

import com.School.Model.Course.Course;
import com.School.Model.Student.Student;
import com.School.Model.Student.StudentDto;
import com.School.Repository.CourseRepository;
import com.School.Repository.StudentRepository;
import com.School.Service.Interface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    public Student addStudent(StudentDto studentDto) {
        if(studentDto!=null){
            Student student=new Student();
            student.setFirstName(studentDto.firstName());
            student.setLastName(studentDto.lastName());
            student.setCourses(studentDto.courses());
           return studentRepository.save(student);
        }
        return null;
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
    public Student updateStudent(StudentDto studentDto, Long id) {
        if(id>0 && studentRepository.existsById(id)){
            Student student=studentRepository.findById(id).get();
            student.setFirstName(studentDto.firstName());
            student.setLastName(studentDto.lastName());
            student.setCourses(studentDto.courses());
            studentRepository.save(student);
            return student;
        }
        return null;
    }

    @Override
    public Student addCourse(Long studentId, Long courseId) {
        if(courseId>0 && courseRepository.existsById(courseId) && studentRepository.existsById(studentId))
        {
            Course course=courseRepository.findById(courseId).get();
            Student student=studentRepository.findById(studentId).get();
            student.addCourse(course);
            return  studentRepository.save(student);
        }
        return null;
    }
}
