package com.school.service.implementation;

import com.school.model.classroom.Classroom;
import com.school.model.course.Course;
import com.school.dto.course.CourseDto;
import com.school.repository.classroom.ClassroomRepository;
import com.school.repository.course.CourseRepository;
import com.school.repository.student.StudentRepository;
import com.school.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ClassroomRepository classroomRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @CacheEvict(cacheNames = "course")
    public Collection<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "course",key = "#id")
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.existsById(id) ? courseRepository.findById(id) : Optional.empty();

    }

    @Override
    @CacheEvict(cacheNames = "course",allEntries = true)
    public Optional<Course> addCourse(CourseDto courseDto) {
       if(courseDto!=null){
           if(classroomRepository.findById(courseDto.classroomId()).isPresent())
           {
               Course course=new Course();
               Classroom classroom=classroomRepository.findById(courseDto.classroomId()).get();
               course.setName(courseDto.name());
               course.setClassroom(classroom);
               courseRepository.save(course);
               return Optional.of(course);
           }

       }
       return Optional.empty();
    }

    @Override
    @CacheEvict(cacheNames = "course",key = "#id")
    public Long deleteCourse(Long id) {
        if(courseRepository.existsById(id)){
            courseRepository.deleteById(id);
            return id;
        }
        return null;
    }

    @Override
    @CachePut(cacheNames = "course",key = "#id")
    public Optional<Course> updateCourse(CourseDto courseDto, Long id) {
        if(courseDto!=null && courseRepository.findById(id).isPresent() && classroomRepository.findById(courseDto.classroomId()).isPresent()){
            Course course=courseRepository.findById(id).get();
            Classroom classroom=classroomRepository.findById(courseDto.classroomId()).get();
            course.setName(courseDto.name());
            course.setClassroom(classroom);
            return Optional.of(courseRepository.save(course));
        }
        return Optional.empty();
    }
}
