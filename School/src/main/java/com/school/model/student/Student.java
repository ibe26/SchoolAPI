package com.school.model.student;

import com.school.model.assessment.Assessment;
import com.school.model.course.Course;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Collection<Course> courses;

    @OneToMany(targetEntity = Assessment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "assessment_student_id", referencedColumnName = "student_id")
    private List<Assessment> assessments;
    public void addCourse(Course course){
        this.courses.add(course);
    }

    public void assignAssessment(Assessment assessment){
        this.assessments.add(assessment);
    }
}
