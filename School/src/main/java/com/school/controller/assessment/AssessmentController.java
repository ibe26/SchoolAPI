package com.school.controller.assessment;

import com.school.dto.assessment.AssessmentDto;
import com.school.model.assessment.Assessment;
import com.school.service.implementation.assessment.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/assessment")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping()
    public ResponseEntity<Assessment> saveAssessment(@RequestBody AssessmentDto assessmentDto){
        return ResponseEntity.ok(assessmentService.saveAssessment(assessmentDto));
    }
}
