package com.school.service.implementation.assessment;

import com.school.dto.assessment.AssessmentDto;
import com.school.model.assessment.Assessment;
import com.school.repository.assessment.AssessmentRepository;
import com.school.repository.course.CourseRepository;
import com.school.repository.student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public AssessmentService(AssessmentRepository assessmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.assessmentRepository = assessmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Assessment saveAssessment(AssessmentDto assessmentDto){
        if(assessmentDto!=null){
            var assessment=new Assessment();
            assessment.setName(assessmentDto.getName());
            assessment.setStudent(studentRepository.findById(assessmentDto.getStudentId()).orElseThrow(EntityNotFoundException::new));
            assessment.setCourse(courseRepository.findById(assessmentDto.getCourseId()).orElseThrow(EntityNotFoundException::new));
            assessment.setWeightagePercentage(assessmentDto.getWeightagePercentage());
            assessment.setName(assessmentDto.getName());
            assessment.setScore(assessmentDto.getScore());
            return assessmentRepository.save(assessment);
        }
        else throw new IllegalArgumentException();
    }
}
