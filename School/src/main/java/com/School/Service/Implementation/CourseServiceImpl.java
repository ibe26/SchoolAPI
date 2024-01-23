package com.School.Service.Implementation;

import com.School.Model.Classroom.Classroom;
import com.School.Model.Course.Course;
import com.School.Model.Course.CourseDto;
import com.School.Repository.ClassroomRepository;
import com.School.Repository.CourseRepository;
import com.School.Service.Interface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ClassroomRepository classroomRepository) {
        this.courseRepository = courseRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Collection<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.existsById(id) ? courseRepository.findById(id) : null;

    }

    @Override
    public Optional<Course> addCourse(CourseDto courseDto) {
       if(courseDto!=null){
           if(classroomRepository.existsById(courseDto.classroomId()))
           {
               Course course=new Course();
               Classroom classroom=classroomRepository.findById(courseDto.classroomId()).get();
               course.setName(courseDto.name());
               course.setClassroom(classroom);
               courseRepository.save(course);
               return Optional.of(course);
           }

       }
       return null;
    }

    @Override
    public Long deleteCourse(Long id) {
        if(courseRepository.existsById(id)){
            courseRepository.deleteById(id);
            return id;
        }
        return null;
    }

    @Override
    public Optional<Course> updateCourse(CourseDto courseDto, Long id) {
        if(courseDto!=null && courseRepository.existsById(id) && classroomRepository.existsById(courseDto.classroomId())){
            Course course=courseRepository.findById(id).get();
            Classroom classroom=classroomRepository.findById(courseDto.classroomId()).get();
            course.setName(courseDto.name());
            course.setClassroom(classroom);
            return Optional.of(courseRepository.save(course));
        }
        return null;
    }
}
