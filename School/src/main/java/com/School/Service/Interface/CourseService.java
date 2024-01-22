package com.School.Service.Interface;

import com.School.Model.Course.Course;
import com.School.Model.Course.CourseDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CourseService {
    Collection<Course> getCourses();
    Course addCourse(CourseDto CourseDto);
    Long deleteCourse(Long id);
    Course updateCourse(CourseDto CourseDto,Long id);
}
