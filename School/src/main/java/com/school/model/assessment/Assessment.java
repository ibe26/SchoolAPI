package com.school.model.assessment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.model.course.Course;
import com.school.model.student.Student;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;

@Data
@Entity
@Table(name = "Assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private Short score;

    @Column(name = "weightage_percentage")
    private Short weightagePercentage;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

}
