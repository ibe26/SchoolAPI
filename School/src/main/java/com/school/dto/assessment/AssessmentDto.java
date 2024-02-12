package com.school.dto.assessment;

import lombok.Data;

@Data
public class AssessmentDto {
    private String name;
    private Long studentId;
    private Long courseId;
    private Short weightagePercentage;
    private Short score;
}
