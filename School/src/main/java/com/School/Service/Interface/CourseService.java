package com.School.Service.Interface;

import com.School.Model.Course.Course;
import com.School.Model.Course.CourseDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface CourseService {
    Collection<Course> getCourses();
    Optional<Course> getCourseById(Long id);
    Optional<Course> addCourse(CourseDto courseDto);
    Long deleteCourse(Long id);
    Optional<Course> updateCourse(CourseDto courseDto,Long id);
}
