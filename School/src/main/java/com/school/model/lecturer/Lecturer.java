package com.school.model.lecturer;

import com.school.model.course.Course;
import jakarta.persistence.*;
import lombok.Data;

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

    @OneToMany(mappedBy = "lecturer")
    private List<Course> courses;
}
