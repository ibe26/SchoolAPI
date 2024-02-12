package com.school.model.lecturer;

import com.school.model.course.Course;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecturer_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_lecturer_id", referencedColumnName = "lecturer_id")
    private List<Course> courses;

    public void saveCourse(Course course) {
        if (this.courses == null) {
            this.courses = new ArrayList<>();
        }
        this.courses.add(course);
    }
}
