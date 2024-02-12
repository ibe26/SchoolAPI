package com.school.service.implementation.lecturer;

import com.school.dto.assessment.AssessmentDto;
import com.school.dto.lecturer.LecturerDto;
import com.school.model.assessment.Assessment;
import com.school.model.course.Course;
import com.school.model.lecturer.Lecturer;
import com.school.model.student.Student;
import com.school.repository.course.CourseRepository;
import com.school.repository.lecturer.LecturerRepository;
import com.school.repository.student.StudentRepository;
import com.school.service.implementation.assessment.AssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final AssessmentService assessmentService;

    public LecturerService(LecturerRepository lecturerRepository, CourseRepository courseRepository, StudentRepository studentRepository, AssessmentService assessmentService) {
        this.lecturerRepository = lecturerRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.assessmentService = assessmentService;
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
            lecturer.setName(lecturerDto.getName());
            return lecturerRepository.save(lecturer);
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

    public Long saveCourse(Long lecturerId, Long courseId){
        if(courseId>0 && courseRepository.findById(courseId).isPresent() && lecturerRepository.findById(lecturerId).isPresent())
        {
            Course course=courseRepository.findById(courseId).get();
            Lecturer lecturer=lecturerRepository.findById(lecturerId).get();
            if(!lecturer.getCourses().contains(course))
            {
                lecturer.saveCourse(course);
                lecturerRepository.save(lecturer);
                return courseId;
            }
        }
        return null;
    }

    public AssessmentDto assignAssessment(AssessmentDto assessmentDto){
        Student student=studentRepository.findById(assessmentDto.getStudentId()).orElseThrow(IllegalArgumentException::new);
        for (Course course: student.getCourses()) {
            if(Objects.equals(course.getId(), assessmentDto.getCourseId())){


                student.assignAssessment(assessmentService.saveAssessment(assessmentDto));
                studentRepository.save(student);
                return assessmentDto;
            }
        }
        throw new IllegalArgumentException();
    }
}
