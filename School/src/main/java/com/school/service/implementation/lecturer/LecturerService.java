package com.school.service.implementation.lecturer;

import com.school.dto.lecturer.LecturerDto;
import com.school.model.course.Course;
import com.school.model.lecturer.Lecturer;
import com.school.repository.course.CourseRepository;
import com.school.repository.lecturer.LecturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;

    public LecturerService(LecturerRepository lecturerRepository, CourseRepository courseRepository) {
        this.lecturerRepository = lecturerRepository;
        this.courseRepository = courseRepository;
    }

    public List<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturerById(Long lecturerId){
        return lecturerRepository.findById(lecturerId).orElseThrow(IllegalArgumentException::new);
    }

    public Lecturer saveLecturer(LecturerDto lecturerDto){
        if(lecturerDto!=null){
            Lecturer lecturer=new Lecturer();
            lecturer.setName(lecturer.getName());
            return lecturer;
        }
        else throw new IllegalArgumentException();
    }

    public Long deleteLecturer(Long lecturerId){
        if(lecturerId!=null){
            lecturerRepository.deleteById(lecturerId);
            return lecturerId;
        }
        else throw new IllegalArgumentException();
    }

    public Lecturer addCourse(Long lecturerId,Long courseId){
        if(lecturerRepository.existsById(lecturerId) && courseRepository.existsById(courseId)){
            Lecturer lecturer=lecturerRepository.getReferenceById(lecturerId);
            List<Course> courseList=lecturer.getCourses();
            courseList.add(courseRepository.findById(courseId).orElseThrow(IllegalArgumentException::new));
            lecturer.setCourses(courseList);
            return lecturer;
        }
        else throw new IllegalArgumentException();
    }
}
