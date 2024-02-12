package com.school.model.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.model.classroom.Classroom;
import com.school.model.lecturer.Lecturer;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "Course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Formula(value = "(SELECT COUNT(*) FROM student_course as sc WHERE sc.course_id=course_id)")
    private Long studentCount;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    @JsonIgnore
    private Lecturer lecturer;

    @Formula(value = "(SELECT course_lecturer_id FROM Course as c WHERE c.course_id=course_id)")
    private Long lecturerId;


}
