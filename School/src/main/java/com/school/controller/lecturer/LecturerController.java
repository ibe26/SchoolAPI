package com.school.controller.lecturer;

import com.school.dto.assessment.AssessmentDto;
import com.school.dto.lecturer.LecturerDto;
import com.school.dto.student.SaveCourseDto;
import com.school.model.assessment.Assessment;
import com.school.model.lecturer.Lecturer;
import com.school.service.implementation.lecturer.LecturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lecturer")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public ResponseEntity<List<Lecturer>> getLecturers(){
        return ResponseEntity.ok(lecturerService.getAllLecturers());
    }
    @PostMapping
    public ResponseEntity<Lecturer> saveLecturer(@RequestBody LecturerDto lecturerDto){
        return ResponseEntity.ok(lecturerService.saveLecturer(lecturerDto));
    }

    @PostMapping("saveCourse/{lecturerId}")
    ResponseEntity<Long> saveCourseToLecturer(@RequestBody SaveCourseDto saveCourseDto ,@PathVariable Long lecturerId){
        return ResponseEntity.ok(lecturerService.saveCourse(lecturerId,saveCourseDto.courseId()));
    }

    @DeleteMapping("{lecturerId}")
    ResponseEntity<Long> deleteLecturer(@PathVariable Long lecturerId){
        return ResponseEntity.ok(lecturerService.deleteLecturer(lecturerId));
    }

    @PostMapping("/assignAssessment")
    public ResponseEntity<?> assignAssessment(@RequestBody AssessmentDto assessmentDto){
        return ResponseEntity.ok(lecturerService.assignAssessment(assessmentDto));
    }
}
