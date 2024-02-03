package com.school.controller.course;

import com.school.controller.RequestMappingHelper.Routes;
import com.school.model.course.Course;
import com.school.dto.course.CourseDto;
import com.school.service.interfaces.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping(Routes.getAll)
    public ResponseEntity<Collection<Course>> getCourses(){
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }
    @GetMapping(Routes.get)
    public ResponseEntity<?> getCourseById(@PathVariable("id") Long id){
        final Optional<Course> courseOptional=courseService.getCourseById(id);
        return courseOptional.isPresent() ? new ResponseEntity<>(courseOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(Routes.save)
    public ResponseEntity<?> saveCourse(@RequestBody() CourseDto courseDto){
        Optional<Course> courseOptional=courseService.addCourse(courseDto);
        return courseOptional.isPresent() ? new ResponseEntity<>(courseOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(Routes.delete)
    public ResponseEntity<?> deleteCourse(@PathVariable("id")Long id){
        return  courseService.deleteCourse(id)!=null ? new ResponseEntity<>(id,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping(Routes.update)
    public ResponseEntity<?> updateCourse(@PathVariable("id") Long id, @RequestBody CourseDto courseDto){
        Optional<Course> courseOptional=courseService.updateCourse(courseDto,id);
        return courseOptional.isPresent() ? new ResponseEntity<>(courseOptional.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
